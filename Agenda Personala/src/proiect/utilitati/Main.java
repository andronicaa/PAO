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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
//        instantiem obiecte din clasele de serviciu

//        Activitati
        ActivitatiService activitatiService = new ActivitatiService();
        IntalniriService intalniriService = new IntalniriService();
        HobbyService hobbyService = new HobbyService();

//        --------Liste ce retin diferite tipuri de obiecte----------------
        ArrayList<Activitate> activitati = new ArrayList<Activitate>();
        ArrayList<ListaCumparaturi> listaGeneralaCumparaturi = new ArrayList<ListaCumparaturi>();
        ArrayList<Intalniri> intalniri = new ArrayList<Intalniri>();
        ArrayList<ActivitatiExtrascolare> hobby = new ArrayList<ActivitatiExtrascolare>();


// ------------------facem conexiunea la baza de date pentru toate serviciile + setup-urile-----------------------
        CumparaturiRepo cumparaturiRepo = new CumparaturiRepo();
        UserRepository userAgenda = new UserRepository();
        PlataRepo platiUser = new PlataRepo();
        AuditRepo audit = new AuditRepo();
        TelefonRepo agTelefon = new TelefonRepo();
        ExamenRepo examRepo = new ExamenRepo();
        examRepo.creatExamTable();
// --------------------------------------------------------------------------------------------------------------------------------------

//        Trebuie sa "conectam" fiecare user la agenda sa - fiecare user se va conecta la contul sau printr-un username
//        Daca username-ul nu exista, inseamna ca se va adauga unul nou in baza da date
        System.out.println("Alegeti optiunea dorita");
        System.out.println("1. LogIn");
        System.out.println("2. Register");
        System.out.print("Ce actiune doriti sa realizati: ");
        Scanner readUserName = new Scanner(System.in);
        Scanner readNumber = new Scanner(System.in);
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
//            TESTEZ DACA SE EXTRAGE BINE ZIUA DIN LUNA
        userAgenda.updateSalariuUser();
//        -----------------------------------------------------------------------------------------------------------------------




//        Trebuie sa extragem toate datele din fisere pentru a putea realiza operatii pe ele, dupa care, la iesirea din program, vor fi incarcate din nou in fisier
//        Incarcam datele pentru lista de cumparaturi
        ReadFromCSVFile citeste = ReadFromCSVFile.ReadFromCSVFile();

//        Incarcam datele pentru Intalniri
        String intalniriFile = "src/proiect/utilitati/Fisiere/intalniri.csv";
        for (String intalnire : citeste.ReadCSV(intalniriFile)) {
            intalniri.add(intalniriService.process(intalnire));
            activitati.add(intalniriService.process(intalnire));
        }



//        Incarcam datele pentru Hobbyuri
        String hobbyFile = "src/proiect/utilitati/Fisiere/hobby.csv";
        for (String objHobby : citeste.ReadCSV(hobbyFile)) {
            hobby.add(hobbyService.process(objHobby));
            activitati.add(hobbyService.process(objHobby));
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


        boolean validInput = false;
        while (agenda != 5)
        {
            if (agenda == 1) {


                System.out.println("1.Adauga produs: ");
                System.out.println("2.Sterge produs(numarul produsului): ");
                System.out.println("3.Listeaza produsele: ");
                System.out.print("Alegere: ");
                int actiune = numere.nextInt();
                if (actiune == 1) {
                    int cantitate = 0;
                    String produs = "";
                    validInput = false;

                    while (!validInput) {
                        try {
                            System.out.print("Ce produs vrei sa adaugi: ");
                            produs = siruri.nextLine();
                            if (Pattern.compile( "[0-9]" ).matcher(produs).find())
                                throw new IllegalArgumentException("Produsul trebuie sa fie un cuvant, nu trebuie sa contina numere!");
                            validInput = true;
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }
                    validInput = false;

                    while (!validInput) {
                        try {
                            System.out.print("Cantitatea dorita: ");
                            cantitate = numere.nextInt();
                            if (cantitate < 0 || cantitate % 1 != 0)
                                throw new IllegalArgumentException("Numarul trebuie sa fie pozitiv!!");
                            validInput = true;
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }

                    }

//                    adaugam in tabelul audit
                    audit.addAuditService(userId, "Adaugare produs");
                    cumparaturiRepo.addProduct(userId, produs, cantitate);

                }

                if (actiune == 2)
                {
                    System.out.println("Ce produs vrei sa stergi: ");
                    cumparaturiRepo.displayLista(userId);
                    System.out.print("Ce produs vrei sa stergi: ");
                    String produs = "";
                    validInput = false;
                    while (!validInput) {
                        try {
                            produs = siruri.nextLine();
                            if (Pattern.compile( "[0-9]" ).matcher(produs).find())
                                throw new IllegalArgumentException("Produsul trebuie sa fie un cuvant, nu trebuie sa contina numere!");
                            validInput = true;

                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    cumparaturiRepo.deleteProduct(userId, produs);
                    audit.addAuditService(userId, "Stergere Produs");

                }
                if (actiune == 3)
                {
                    cumparaturiRepo.displayLista(userId);
                    audit.addAuditService(userId, "Display Lista");
                }


            }

            if (agenda == 2) {

                System.out.println("Numere de telefon: ");
                agTelefon.displayAgenda(userId);
                System.out.println("1.Adauga numar de telefon(nume prenume numar de telefon): ");
                System.out.println("2.Sterge numar de telefon(nume prenume): ");
                System.out.println("3.Afiseaza agenda ");
                System.out.print("Alegere: ");
                int actiune = numere.nextInt();
                if (actiune == 1) {
                    audit.addAuditService(userId, "Adaugare numar telefon");
                    System.out.print("Nume: "); String lastName = siruri.nextLine();
                    System.out.print("Prenume: "); String firstName = siruri.nextLine();
                    String nrTelefon = "";
                    validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Nr Telefon: "); nrTelefon = siruri.nextLine();
                            if (Pattern.compile( "[0-9]" ).matcher(nrTelefon).find() == false || nrTelefon.length() != 10)
                                throw new IllegalArgumentException("Numarul de telefon trebuie sa contina doar cifre si sa fie de 10 cifre");
                            validInput = true;
                        } catch (IllegalArgumentException ex)  {
                            System.out.println(ex.getMessage());
                        }
                    }

                    agTelefon.adaugaNrTelefon(userId, lastName, firstName, nrTelefon);

                }

                if(actiune == 2)
                {
                    System.out.print("Ce contact doriti sa eliminati: ");
                    audit.addAuditService(userId, "Stergere numar telefon");
                    int idContact = 0;
                    validInput = false;
                    while (!validInput) {
                        try {
                            idContact = numere.nextInt();
                            if (idContact < 1)
                                throw new IllegalArgumentException("Numarul trebuie sa fie mai mare decat 0.");
                            validInput  = true;
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    agTelefon.stergeNrTelefon(idContact);


                }
                if (actiune == 3)
                {   audit.addAuditService(userId, "Listeaza agenda telefon");
                    agTelefon.displayAgenda(userId);
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
                    if (tipActivitate.equals("1"))

                    {

                        System.out.print("Data(dd/MM/yyyy) Prioritate Locatie Nume Prenume: ");
                        audit.addAuditService(userId, "Adaugare intalnire");
                        String s = siruri.nextLine();
                        System.out.println();
                        String[] cuvinte = s.split(" ");
                        Intalniri intalnire = new Intalniri("Intalnire", cuvinte[0], cuvinte[1], cuvinte[2], cuvinte[3], cuvinte[4]);
                        activitati.add(intalnire);
                        intalniri.add(intalnire);
                    }

                    if (tipActivitate.equals("2"))
                    {
                        validInput = false;
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        audit.addAuditService(userId, "Adaugare examen: ");
                        System.out.print("Materie: "); String materie = siruri.nextLine();
                        System.out.print("Locatie: "); String locatie = siruri.nextLine();
                        String dataExamen = "";
                        while (!validInput){
                            try {
                                System.out.print("Data(yyyy-mm-dd): "); dataExamen = siruri.nextLine();
                                format.parse(dataExamen);
                                validInput = true;


                            } catch (ParseException ex)  {
                                System.out.println("Data nu este valida. Trebuie sa fie de forma yyyy-mm-dd");
                            }
                        }

                        examRepo.insertExamen(userId, materie, locatie, dataExamen);
                    }

                    if (tipActivitate.equals("3") ==  true)
                    {   audit.addAuditService(userId, "Adaugare hobby");
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
                    audit.addAuditService(userId, "Listeaza activitatile");
                    System.out.println("Lista activitati: ");
                    activitatiService.listeazaActivitati(activitati);

                }
                if (actiune == 3){
                    System.out.println("1.Arata Intalnirile in functie de data si prioritate");
                    System.out.println("2.Arata ce examene am intr-o luna precizata:");
                    System.out.print("Alege: ");
                    int nr = numere.nextInt();
                    System.out.println();
                    if (nr == 1){
                        audit.addAuditService(userId, "Sortarea intalnirilor in functie de prioritate");
                        System.out.println("Sortam intalnirile: ");
                        intalniriService.sortIntalniri(intalniri);

                        for (Intalniri value : intalniri) {
                            System.out.println("Data: " + value.getData() + " locatie: " + value.getLocatie() + " cu " + value.getNume() + " " + value.getPrenume());

                        }
                    }
                    if (nr == 2) {
                        audit.addAuditService(userId, "Afisare examen");
                        System.out.print("Pentru ce luna doresti sa afli ce examene ai?(ex: 12) ");
                        validInput = false;
                        int luna = 0;
                        while (!validInput) {
                            try {
                                System.out.print("Luna: "); luna = numere.nextInt();
                                if (luna < 1 || luna > 12)
                                    throw new IllegalArgumentException("Numarul trebuie sa fie corespunzator unei luni(intre 1 si 12)");
                                validInput = true;
                            } catch (IllegalArgumentException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }

                        examRepo.exameneLunaPrec(userId, luna);

                    }

                }

            }

            if (agenda == 4)
            {
                System.out.println("1.Facturile ce trebuie platite in aceasta luna: ");
                System.out.println("2.Plateste Factura");
                System.out.println("3.Facturile ce au trecut de data scadenta");
                System.out.println("4.Adauga factura");
                System.out.println("5.Listeaza facturile");
                System.out.print("Alege: ");
                int actiune = numere.nextInt();
                System.out.println();
                if (actiune == 1)
                {

                    System.out.println("Facturile ce trebuie achitate pe luna in curs");
                    platiUser.facturiLunaCurenta(userId);
                    audit.addAuditService(userId, "Afisare facturi pe luna curenta");

                }

                if (actiune == 2)
                {

//                    Listez toate facturile care sunt de platit pe luna in curs pentru un UserID
                    System.out.println("Facturile pe care trebuie sa le platiti sunt: ");
                    platiUser.listeazaFacturi(userId);
                    System.out.print("Ce factura doriti sa platiti: ");
                    int idFactura = 0;
                    while (!validInput) {
                        try {
                            System.out.print("Luna: "); idFactura = numere.nextInt();
                            if (idFactura < 0)
                                throw new IllegalArgumentException("Numarul trebuie sa fie natural");
                            validInput = true;
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    platiUser.platesteFactura(userId, idFactura);
//                    Interogare sold
                    System.out.println("Dupa achitarea facturilor mai aveti " + userAgenda.getSalary(userId));
                    audit.addAuditService(userId, "Achitare Factura");


                }
                if (actiune == 3)
                {
                    System.out.println("Facturile ce au trecut de data scadenta");
//                    aici trebuie sa vad in ce data curenta si sa o compar cu data scadenta a fiecarei facturi
                    platiUser.facturiScadenta(userId);
                    audit.addAuditService(userId, "Afisare Facturi Scadente");
                }
                if (actiune == 4)
                {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.print("Ce factura doriti sa adaugati(tipFactura dataFacturare dataScadenta pret): ");
                    System.out.print("Tip factura: ");
                    String tipFactura = siruri.nextLine();
                    String dataFacturare = "";
                    String dataScadenta = "";
                    validInput = false;
                    while (!validInput){
                        try {
                            System.out.print("Data facturare: ");
                            dataFacturare = siruri.nextLine();
                            System.out.print("Data scadenta: ");
                            dataScadenta = siruri.nextLine();
                            format.parse(dataFacturare);
                            format.parse(dataScadenta);
                            validInput = true;


                        } catch (ParseException ex)  {
                            System.out.println("Data nu este valida. Trebuie sa fie de forma yyyy-mm-dd");
                        }
                    }

                    validInput = false;
                    int pret = 0;
                    while (!validInput) {
                        try {
                            System.out.print("Pretul este: ");
                            pret = numere.nextInt();
                            if (pret < 0 || pret % 1 != 0)
                                throw new IllegalArgumentException("Argumentul trebuie sa fie un numar pozitiv");
                            validInput = true;
                        } catch (IllegalArgumentException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    platiUser.adaugaFactura(userId, tipFactura, dataFacturare, dataScadenta, pret);
                    audit.addAuditService(userId, "Adaugare factura");




                }
                if (actiune == 5)
                {

                    System.out.println("Facturile sunt: ");
                    platiUser.listeazaFacturi(userId);
                    audit.addAuditService(userId, "Listare Facturi");

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

//            Cand iesim din aplicatie trebuie sa actualizam fisierele si sa le inchidem
        hobbyService.updateFileHobby("src/proiect/utilitati/Fisiere/hobby.csv", hobby);
        intalniriService.updateFileIntalniri("src/proiect/utilitati/Fisiere/intalniri.csv", intalniri);


    }
}
