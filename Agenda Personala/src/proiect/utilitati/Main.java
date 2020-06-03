package proiect.utilitati;
import proiect.activitati.Activitate;
import proiect.activitati.ActivitatiExtrascolare;
import proiect.activitati.Intalniri;
import proiect.activitati.Examene;
import proiect.lista_cumparaturi.ListaCumparaturi;
import proiect.persoana.Persoana;
import proiect.plati.Plata;
import proiect.utilitati.Fisiere.ReadFromCSVFile;
import proiect.utilitati.Fisiere.WriteInCSVFile;
import proiect.utilitati.serviceClass.*;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
//        instantiem obiecte din clasele de serviciu
//        Agenda Telefon
        AgendaTelefonService agendaService = new AgendaTelefonService();
//        Lista de cumparaturi
        ListaCumparaturiService listaService = new ListaCumparaturiService();
//        Activitati
        ActivitatiService activitatiService = new ActivitatiService();
        IntalniriService intalniriService = new IntalniriService();
        ExameneService exameneService = new ExameneService();
        HobbyService hobbyService = new HobbyService();
        PlatiService platiService = new PlatiService();

//        --------Liste ce retin diferite tipuri de obiecte----------------
        ArrayList<Activitate> activitati = new ArrayList<Activitate>();
        ArrayList<ListaCumparaturi> listaGeneralaCumparaturi = new ArrayList<ListaCumparaturi>();
        ArrayList<Intalniri> intalniri = new ArrayList<Intalniri>();
        ArrayList<Examene> examene = new ArrayList<Examene>();
        ArrayList<ActivitatiExtrascolare> hobby = new ArrayList<ActivitatiExtrascolare>();
        ArrayList<Plata> plati = new ArrayList<Plata>();

//        folosesc un TreeMap pentru numerele de telefon pe care le sortez alfabetic dupa nume respectiv prenume
        TreeMap<Persoana, String> agendaTelefon = new TreeMap<Persoana, String>(new SortByName());

        //        ------------Date despre detinatorul agendei-----------------
        System.out.println("Detinator agenda:");
        TitularAgenda titular = TitularAgenda.TitularAgenda(); //clasa de tip singleton se poate instantia doar un obiect din cadrul acesteia
        titular.nume = "Andronic"; titular.prenume = "Alexandra"; titular.email = "alexandraandronic368@gmail.com"; titular.nrTelefon = "0740159113"; titular.salariu = 3000;
        System.out.println(titular.toString());
// ------------------facem conexiunea la baza de date pentru toate serviciile
        CumparaturiRepo cumparaturiRepo = new CumparaturiRepo();
// --------------------------------------------------------------------------------------------------------------------------------------

//        Trebuie sa "conectam" fiecare user la agenda sa - fiecare user se va conecta la contul sau printr-un username
//        Daca username-ul nu exista, inseamna ca se va adauga unul nou in baza da date
        System.out.println("Alegeti optiunea dorita");
        System.out.println("1. LogIn");
        System.out.println("2. Register");
        System.out.print("Ce actiune doriti sa realizati: ");
        Scanner readUserName = new Scanner(System.in);
        Scanner readNumber = new Scanner(System.in);
        UserRepository userAgenda = new UserRepository();
        int userId = 0;
        int option = readNumber.nextInt();
        if (option == 1) {
//             se va face autentificarea(gasim idPerson corespunzator)
            System.out.print("Dati username-ul: ");
            String userName = readUserName.nextLine();
            userId = userAgenda.cautaUser(userName);
            while (userId == 0) {
                System.out.println("Ati gresit username-ul. Reincercati");
                userName = readUserName.nextLine();
                userId = userAgenda.cautaUser(userName);

            }
        }
        else
            if (option == 2)
        {
//            daca optiunea a fost 2, atunci i se va face un nou cont utilizatorului
            System.out.print("Ce username doriti sa aveti? ");
            String userName = readUserName.nextLine();
            while (userAgenda.cautaUser(userName) != 0)
            {   System.out.println("Acest username nu este valid, incercati altul");
                userName = readUserName.nextLine();
            }
//                dupa ce s-a ales un username valid, se va adauga utilizatorul in baza de date
//                trebuie sa adauge cateva date despre el
            System.out.print("Numele de familie: ");
            String lastName = readUserName.nextLine();
            System.out.println();
            System.out.print("Prenumele: ");
            String firstName = readUserName.nextLine();
            System.out.println();
            System.out.print("Email-ul: ");
            String email = readUserName.nextLine();
            System.out.println();
            System.out.print("Salariul dumneavoastra este: ");
            int salary = readUserName.nextInt();
            System.out.println();
            userId = userAgenda.addUserToDB(lastName, firstName, email, salary, userName);
            System.out.print("Utilizatorul nou adaugat este ");
            System.out.print(userId);


        }
//        -----------------------------------------------------------------------------------------------------------------------

//        ----------Facem un obiect de tipul AuditService, clasa contine o metoda LogActionInAuditFile ce scrie in audit.csv numele actiunii ce se face ------------
        AuditService scriereAudit = AuditService.AuditService();
        FileWriter writer = scriereAudit.OpenFile();

//        Trebuie sa extragem toate datele din fisere pentru a putea realiza operatii pe ele, dupa care, la iesirea din program, vor fi incarcate din nou in fisier
//        Incarcam datele pentru lista de cumparaturi
        ReadFromCSVFile citeste = ReadFromCSVFile.ReadFromCSVFile();
        String cumparaturiFile = "src/proiect/utilitati/Fisiere/cumparaturi.csv";
        for (String produs : citeste.ReadCSV(cumparaturiFile)) {
//            trebuie sa mapam fiecare linie
            listaGeneralaCumparaturi.add(listaService.process(produs));
        }
//      Incarcam datele pentru agenda de Telefon
        String agendaFile = "src/proiect/utilitati/Fisiere/telefon.csv";
        for (String telefon : citeste.ReadCSV(agendaFile)) {
//            trebuie sa mapam fiecare linie
            agendaService.adaugaNrTelefon(agendaService.process(telefon)[0], agendaService.process(telefon)[1], agendaService.process(telefon)[2], agendaTelefon);
        }
//        Incarcam datele pentru Intalniri
        String intalniriFile = "src/proiect/utilitati/Fisiere/intalniri.csv";
        for (String intalnire : citeste.ReadCSV(intalniriFile)) {
            intalniri.add(intalniriService.process(intalnire));
            activitati.add(intalniriService.process(intalnire));
        }


//        Incarcam datele pentru examene
        String exameneFile = "src/proiect/utilitati/Fisiere/examen.csv";
        for (String examen : citeste.ReadCSV(exameneFile)) {
            examene.add(exameneService.process(examen));
            activitati.add(exameneService.process(examen));
        }

//        Incarcam datele pentru Hobbyuri
        String hobbyFile = "src/proiect/utilitati/Fisiere/hobby.csv";
        for (String objHobby : citeste.ReadCSV(hobbyFile)) {
            hobby.add(hobbyService.process(objHobby));
            activitati.add(hobbyService.process(objHobby));
        }


        //        Incarcam datele pentru Plati
        String platiFile = "src/proiect/utilitati/Fisiere/plati.csv";
        for (String objPlata : citeste.ReadCSV(platiFile)) {
            plati.add(platiService.process(objPlata));

        }


//        ------------------Actiunile posibile ce se pot efectua-------------------
        System.out.println("Ce actiune doriti sa efectuati: ");
        System.out.println("1.Lista de cumparaturi");
        System.out.println("2.Numere de telefon:");
        System.out.println("3.Activitati: ");
        System.out.println("4.Plati facturi/asigurari");
        System.out.println("5.Iesire: ");
        System.out.print("Alegere: ");

        Scanner numere = new Scanner(System.in);
        Scanner siruri = new Scanner(System.in);
        int agenda = numere.nextInt();


        while (agenda != 5)
        {
            if (agenda == 1) {


                System.out.println("1.Adauga produs(produs cantitate): ");
                System.out.println("2.Sterge produs(numarul produsului): ");
                System.out.println("3.Listeaza produsele: ");
                System.out.print("Alegere: ");
                int actiune = numere.nextInt();
                if (actiune == 1) {
                    System.out.print("Ce produs vrei sa adaugi: ");
                    System.out.print("Produsul: ");
                    String produs = siruri.nextLine();
                    System.out.print("Cantitate: ");
                    int cantitate = numere.nextInt();
                    System.out.println();
//                    adaugam in fisierul audit
                    scriereAudit.LogActionInAuditFile("Adaugare produs in lista de cumparaturi", writer);
                    cumparaturiRepo.addProduct(userId, produs, cantitate);

                }

                if (actiune == 2)
                {
                    System.out.println("Ce produs vrei sa stergi: ");
                    scriereAudit.LogActionInAuditFile("Stergere produs din lista de cumparaturi", writer);
                    cumparaturiRepo.displayLista(userId);
                    System.out.print("Ce produs vrei sa stergi: ");
                    String produs = siruri.nextLine();
                    System.out.println();
                    cumparaturiRepo.deleteProduct(userId, produs);

                }
                if (actiune == 3)
                {
                    scriereAudit.LogActionInAuditFile("Lista de Cumparaturi", writer);
                    cumparaturiRepo.displayLista(userId);
                }


            }

            if (agenda == 2) {

                System.out.println("Numere de telefon: ");
                agendaService.displayTelefon(agendaTelefon);
                System.out.println("1.Adauga numar de telefon(nume prenume numar de telefon): ");
                System.out.println("2.Sterge numar de telefon(nume prenume): ");
                System.out.println("3.Afiseaza agenda ");
                System.out.print("Alegere: ");
                int actiune = numere.nextInt();
                if (actiune == 1) {
//                    adauga in agenda(TreeMap), numerele se adauga ca in telefon, sortate alfabetic
                    scriereAudit.LogActionInAuditFile("Adaugare nr telefon in agenda", writer);
                    System.out.print("Nume, prenume, numar de telefon: ");
                    String informatie = siruri.nextLine();
                    System.out.println();
                    String nume = informatie.substring(0, informatie.indexOf(" "));
                    String inf = informatie.substring(informatie.indexOf(" ") + 1, informatie.length());
                    String prenume = inf.substring(0, inf.indexOf(" "));
                    String nrTelefon = inf.substring(inf.indexOf(" ") + 1, inf.length());
                    agendaService.adaugaNrTelefon(nume, prenume, nrTelefon, agendaTelefon);
                    agendaService.displayTelefon(agendaTelefon);


                }

                if(actiune == 2)
                {
                    System.out.print("Sterge nume prenume: ");
                    scriereAudit.LogActionInAuditFile("Stergere nr telefon din agenda", writer);
                    String informatie = siruri.nextLine();
                    System.out.println();
                    String nume = informatie.substring(0, informatie.indexOf(" "));
                    String prenume = informatie.substring(informatie.indexOf(" ") + 1, informatie.length());
                    agendaService.stergeNumarTelefon(nume, prenume, agendaTelefon);
                    agendaService.displayTelefon(agendaTelefon);

                }
                if (actiune == 3)
                {   scriereAudit.LogActionInAuditFile("Listeaza numerele de telefon", writer);
                    agendaService.displayTelefon(agendaTelefon);
                }

            }

            if (agenda == 3) {

                System.out.println("Activitati: ");
                System.out.println("1.Adauga activitate: ");
                System.out.println("2.Listeaza activitati: ");
                System.out.println("3.Extrage informatii:");
                System.out.print("Alegere: ");
                int actiune = numere.nextInt();

                if (actiune == 1)
                {//                trebuie sa vedem ce tip de activitate este
                    System.out.print("Intalnire -> 1, Examen -> 2, Hobby -> 3: ");
                    String tipActivitate = siruri.nextLine();
                    System.out.println();
                    if (tipActivitate.equals("1") == true)

                    {   System.out.print("Data(dd/MM/yyyy) Prioritate Locatie Nume Prenume: ");
                        scriereAudit.LogActionInAuditFile("Adauga Intalnire", writer);
                        String s = siruri.nextLine();
                        System.out.println();
                        String[] cuvinte = s.split(" ");
                        Intalniri intalnire = new Intalniri("Intalnire", cuvinte[0], cuvinte[1], cuvinte[2], cuvinte[3], cuvinte[4]);
                        activitati.add(intalnire);
                        intalniri.add(intalnire);
                    }

                    if (tipActivitate.equals("2") == true)
                    {   scriereAudit.LogActionInAuditFile("Adauga Examen", writer);
                        System.out.print("Data Prioritate Sala Materie: ");
                        String s = siruri.nextLine();
                        System.out.println();
                        String[] cuvinte = s.split(" ");
                        Examene examen = new Examene("Examen", cuvinte[0], cuvinte[1], cuvinte[2], cuvinte[3]);
                        activitati.add(examen);
                        examene.add(examen);
                    }

                    if (tipActivitate.equals("3") ==  true)
                    {   scriereAudit.LogActionInAuditFile("Adauga Hobby", writer);
                        System.out.print("Data Prioritate Locatie NumeActivitate: ");
                        String s = siruri.nextLine();
                        System.out.println();
                        String[] cuvinte = s.split(" ");
                        ActivitatiExtrascolare hobb = new ActivitatiExtrascolare("Hobby", cuvinte[0], cuvinte[1], cuvinte[2], cuvinte[3]);
                        activitati.add(hobb);
                        hobby.add(hobb);
                    }




                }

                if (actiune == 2)
                {// Listeaza Activitatile
                    scriereAudit.LogActionInAuditFile("Listeaza toate activitatile", writer);
                    System.out.println("Lista activitati: ");
//                    obiect.afisareIntalniri(intalniri);
//                    obiect.afisareExamene(examene);
//                    obiect.afisareHobby(hobby);
                    activitatiService.listeazaActivitati(activitati);

                }
                if (actiune == 3){
                    System.out.println("1.Arata Intalnirile in functie de data si prioritate");
                    System.out.println("2.Arata ce examene am intr-o luna precizata:");
                    System.out.print("Alege: ");
                    int nr = numere.nextInt();
                    System.out.println();
                    if (nr == 1){
                        scriereAudit.LogActionInAuditFile("Sortarea Intalnirilor in functie de prioritate", writer);
                        System.out.println("Sortam intalnirile: ");
                        intalniriService.sortIntalniri(intalniri);

                        for (int i = 0; i < intalniri.size(); i++)
                        {
                            System.out.println("Data: " + intalniri.get(i).getData() + " locatie: " + intalniri.get(i).getLocatie() + " cu " + intalniri.get(i).getNume() + " " + intalniri.get(i).getPrenume());

                        }
                    }
                    if (nr == 2) {
                        scriereAudit.LogActionInAuditFile("Afisare Examen", writer);
                        System.out.print("Pentru ce luna doresti sa afli ce examene ai?(ex: martie) ");
                        String info = siruri.nextLine();
                        String luna = info.toLowerCase();
                        System.out.println();
                        System.out.println("In luna " + info + " ai urmatoarele examene: ");
                        exameneService.afisareExamenLuna(examene, luna);

                    }

                }

            }

            if (agenda == 4)
            {
                System.out.println("1.Facturile ce trebuie platite in aceasta luna: ");
                System.out.println("2.Bugetul ramas in aceasta luna dupa achitarea facturilor");
                System.out.println("3.Facturile ce au trecut de data scadenta");
                System.out.println("4.Adauga factura");
                System.out.println("5.Listeaza facturile");
                System.out.print("Alege: ");
                int actiune = numere.nextInt();
                System.out.println();
                if (actiune == 1)
                {   scriereAudit.LogActionInAuditFile("Afisare Facturi", writer);
                    System.out.println("Facturile ce trebuie achitate pe luna in curs");
                    platiService.afisareFacturiLunaCurenta(plati);

                }

                if (actiune == 2)
                {
                    scriereAudit.LogActionInAuditFile("Afisare buget", writer);
                    System.out.print("Bugetul care ar ramane pe aceasta luna daca s-ar plati facturile: ");
                    int baniCheltuiti = platiService.bugetRamas(plati, titular.salariu);
                    System.out.println(baniCheltuiti);
                    titular.salariu -= baniCheltuiti;
                }
                if (actiune == 3)
                {   scriereAudit.LogActionInAuditFile("Facturi Scadente", writer);
                    System.out.println("Facturile ce au trecut de data scadenta");
//                    aici trebuie sa vad in ce data curenta si sa o compar cu data scadenta a fiecarei facturi
                    platiService.facturiScadente(plati);
                }
                if (actiune == 4)
                {   scriereAudit.LogActionInAuditFile("Adaugare Factura", writer);
                    System.out.print("Ce factura doriti sa adaugati(tipFactura dataFacturare dataScadenta pret): ");
                    String s = siruri.nextLine();
                    System.out.println();
                    String[] cuvinte = s.split(" ");
                    int pret = Integer.parseInt(cuvinte[3]);
                    platiService.adaugaFactura(plati, new Plata(cuvinte[0], cuvinte[1], cuvinte[2], pret));



                }
                if (actiune == 5)
                {   scriereAudit.LogActionInAuditFile("Listarea Facturilor", writer);
                    platiService.listeazaFacturi(plati);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ce actiunde doriti sa efectuati: ");
            System.out.println("1.Lista de cumparaturi");
            System.out.println("2.Numere de telefon:");
            System.out.println("3.Activitati: ");
            System.out.println("4.Plati facturi/asigurari");
            System.out.println("5.Iesire: ");
            System.out.print("Alegere: ");
            agenda = numere.nextInt();

        }
        if (agenda == 5) {

//            Cand iesim din aplicatie trebuie sa actualizam fisierele si sa le inchidem
            listaService.updateFile("src/proiect/utilitati/Fisiere/cumparaturi.csv", listaGeneralaCumparaturi);
            exameneService.updateFileExamene("src/proiect/utilitati/Fisiere/examen.csv", examene);
            hobbyService.updateFileHobby("src/proiect/utilitati/Fisiere/hobby.csv", hobby);
            intalniriService.updateFileIntalniri("src/proiect/utilitati/Fisiere/intalniri.csv", intalniri);
            platiService.updateFilePlati("src/proiect/utilitati/Fisiere/plati.csv", plati);

            writer.close();
        }




    }
}
