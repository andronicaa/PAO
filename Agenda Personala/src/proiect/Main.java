package proiect;
import proiect.activitati.Activitate;
import proiect.activitati.ActivitatiExtrascolare;
import proiect.activitati.Intalniri;
import proiect.activitati.Examene;
import proiect.lista_cumparaturi.ListaCumparaturi;
import proiect.persoana.Persoana;
import proiect.plati.Plata;
import java.util.concurrent.TimeUnit;
import javax.sound.midi.SysexMessage;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Test obiect = new Test();
//        --------Liste ce retin diferite tipuri de obiecte----------------
        ArrayList<Activitate> activitati = new ArrayList<Activitate>();
        ArrayList<ListaCumparaturi> listaGenerala = new ArrayList<ListaCumparaturi>();
        ArrayList<Intalniri> intalniri = new ArrayList<Intalniri>();
        ArrayList<Examene> examene = new ArrayList<Examene>();
        ArrayList<ActivitatiExtrascolare> hobby = new ArrayList<ActivitatiExtrascolare>();
        ArrayList<Plata> plati = new ArrayList<Plata>();
//        folosesc un TreeMap pentru numerele de telefon pe care le sortez alfabetic dupa nume respectiv prenume
        TreeMap<Persoana, String> agendaTelefon = new TreeMap<Persoana, String>(new SortByName());
//        urmeaza sa fac o baza de date cu diferiti utilizatori

//        ------------Date despre detinatorul agendei-----------------
        System.out.println("Detinator agenda:");
        TitularAgenda titular = TitularAgenda.TitularAgenda(); //clasa de tip singleton se poate instantia doar un obiect din cadrul acesteia
        titular.nume = "Andronic"; titular.prenume = "Alexandra"; titular.email = "alexandraandronic368@gmail.com"; titular.nrTelefon = "0740159113"; titular.salariu = 3000;
        System.out.println(titular.toString());
        ListaCumparaturi produs1 = new ListaCumparaturi("rosii", "3kg");
        ListaCumparaturi produs2 = new ListaCumparaturi("masline", "1kg");
        ListaCumparaturi produs3 = new ListaCumparaturi("ciocolata", "3");
        ListaCumparaturi produs4 = new ListaCumparaturi("paine", "5");
        listaGenerala.add(produs1);
        listaGenerala.add(produs2);
        listaGenerala.add(produs3);
        listaGenerala.add(produs4);
        Intalniri intalnire1 = new Intalniri("intalnire", "20/12/2020", "2", "bucuresti", "Andronic", "Alexandra");
        Intalniri intalnire2 = new Intalniri("intalnire", "20/03/2020", "20", "bucuresti", "Popescu", "Ion");
        Intalniri intalnire3 = new Intalniri("intalnire", "15/01/2020", "2", "bucuresti", "Andronic", "Cosmin");
        Intalniri intalnire4 = new Intalniri("intalnire", "15/01/2020", "1", "bucuresti", "Andronic", "Silvia");

        Examene examen1 = new Examene("examen", "20/03/2020", "1", "amf1", "matematica");
        Examene examen2 = new Examene("examen", "20/06/2020", "2", "amf1", "matematica");
        Examene examen3 = new Examene("examen", "20/03/2020", "3", "amf1", "romana");
        Examene examen4 = new Examene("examen", "20/05/2020", "4", "amf1", "engleza");

        ActivitatiExtrascolare hobby1 = new ActivitatiExtrascolare("hobby", "20/12/2020", "2", "acasa", "fotbal");
        ActivitatiExtrascolare hobby2 = new ActivitatiExtrascolare("hobby", "04/04/2020", "2", "facultate", "concurs_programare");
        ActivitatiExtrascolare hobby3 = new ActivitatiExtrascolare("hobby", "03/02/2020", "2", "mall", "escape_room");
        ActivitatiExtrascolare hobby4 = new ActivitatiExtrascolare("hobby", "12/05/2020", "2", "acasa", "film");
        intalniri.add(intalnire1); activitati.add(intalnire1);
        intalniri.add(intalnire2); activitati.add(intalnire2);
        intalniri.add(intalnire3); activitati.add(intalnire3);
        intalniri.add(intalnire4); activitati.add(intalnire4);
        examene.add(examen1); activitati.add(examen1);
        examene.add(examen2); activitati.add(examen2);
        examene.add(examen3); activitati.add(examen3);
        examene.add(examen4); activitati.add(examen4);
        hobby.add(hobby1); activitati.add(hobby1);
        hobby.add(hobby2); activitati.add(hobby2);
        hobby.add(hobby3); activitati.add(hobby3);
        hobby.add(hobby4); activitati.add(hobby4);
        Plata factura1 = new Plata("lumina", "12/02/2020", "12/03/2020", 200);
        Plata factura2 = new Plata("internet", "12/02/2020", "12/04/2020", 200);
        Plata factura3 = new Plata("gaze", "12/04/2020", "12/05/2020", 200);
        Plata factura4 = new Plata("securitate", "12/02/2020", "12/03/2020", 200);
        plati.add(factura1);
        plati.add(factura2);
        plati.add(factura3);
        plati.add(factura4);

//        ------------------Actiunile posibile ce se pot efectua-------------------
        System.out.println("Ce actiunde doriti sa efectuati: ");
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
                    String produsCantitate = siruri.nextLine();
                    System.out.println();
                    int spatiu = produsCantitate.indexOf(" ");
                    String produs = produsCantitate.substring(0, spatiu);
                    String cantitate = produsCantitate.substring(spatiu + 1, produsCantitate.length());
                    obiect.adaugaProdus(produs, cantitate, listaGenerala);
//                    obiect.display(listaGenerala);
                }

                if (actiune == 2)
                {
                    System.out.println("Ce produs vrei sa stergi: ");
                    for (int i = 0; i < listaGenerala.size(); i++)
                    {
                        System.out.println((i+1) + "." + listaGenerala.get(i).getProdus() + " " + listaGenerala.get(i).getCantitate());
                    }
                    System.out.print("Ce produs vrei sa stergi: ");
                    int nrProdus = numere.nextInt();
                    System.out.println();
                    obiect.stergeProdus(nrProdus, listaGenerala);

                }
                if (actiune == 3)
                {

                    obiect.display(listaGenerala);
                }


            }

            if (agenda == 2) {

                System.out.println("Numere de telefon: ");
                obiect.displayTelefon(agendaTelefon);
                System.out.println("1.Adauga numar de telefon(nume prenume numar de telefon): ");
                System.out.println("2.Sterge numar de telefon(nume prenume): ");
                System.out.println("3.Afiseaza agenda ");
                System.out.print("Alegere: ");
                int actiune = numere.nextInt();
                if (actiune == 1) {
//                    adauga in agenda(TreeMap), numerele se adauga ca in telefon, sortate alfabetic
                    System.out.print("Nume, prenume, numar de telefon: ");
                    String informatie = siruri.nextLine();
                    System.out.println();
                    String nume = informatie.substring(0, informatie.indexOf(" "));
                    String inf = informatie.substring(informatie.indexOf(" ") + 1, informatie.length());
                    String prenume = inf.substring(0, inf.indexOf(" "));
                    String nrTelefon = inf.substring(inf.indexOf(" ") + 1, inf.length());
                    obiect.adaugaNrTelefon(nume, prenume, nrTelefon, agendaTelefon);
                    obiect.displayTelefon(agendaTelefon);


                }

                if(actiune == 2)
                {
                    System.out.print("Sterge nume prenume: ");
                    String informatie = siruri.nextLine();
                    System.out.println();
                    String nume = informatie.substring(0, informatie.indexOf(" "));
                    String prenume = informatie.substring(informatie.indexOf(" ") + 1, informatie.length());
                    obiect.stergeNumarTelefon(nume, prenume, agendaTelefon);
                    obiect.displayTelefon(agendaTelefon);

                }
                if (actiune == 3)
                {
                    obiect.displayTelefon(agendaTelefon);
                }

            }

            if (agenda == 3) {

//                -----------------adaug cateva atribute si din cod pentru a arata direct functionalitatea


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
                        String s = siruri.nextLine();
                        System.out.println();
                        String[] cuvinte = s.split(" ");
                        Intalniri intalnire = new Intalniri("intalnire", cuvinte[0], cuvinte[1], cuvinte[2], cuvinte[3], cuvinte[4]);
                        activitati.add(intalnire);
                        intalniri.add(intalnire);
                    }

                    if (tipActivitate.equals("2") == true)
                    {
                        System.out.print("Data Prioritate Sala Materie: ");
                        String s = siruri.nextLine();
                        System.out.println();
                        String[] cuvinte = s.split(" ");
                        Examene examen = new Examene("examen", cuvinte[0], cuvinte[1], cuvinte[2], cuvinte[3]);
                        activitati.add(examen);
                        examene.add(examen);
                    }

                    if (tipActivitate.equals("3") ==  true)
                    {
                        System.out.print("Data Prioritate Locatie NumeActivitate: ");
                        String s = siruri.nextLine();
                        System.out.println();
                        String[] cuvinte = s.split(" ");
                        ActivitatiExtrascolare hobb = new ActivitatiExtrascolare("hobby", cuvinte[0], cuvinte[1], cuvinte[2], cuvinte[3]);
                        activitati.add(hobb);
                        hobby.add(hobb);
                    }




                }

                if (actiune == 2)
                {// Listeaza Activitatile
                    System.out.println("Lista activitati: ");
//                    obiect.afisareIntalniri(intalniri);
//                    obiect.afisareExamene(examene);
//                    obiect.afisareHobby(hobby);
                    obiect.displayActivitati(activitati);

                }
                if (actiune == 3){
                    System.out.println("1.Arata Intalnirile in functie de data si prioritate");
                    System.out.println("2.Arata ce examene am intr-o luna precizata:");
                    System.out.print("Alege: ");
                    int nr = numere.nextInt();
                    System.out.println();
                    if (nr == 1){
                        System.out.println("Sortam intalnirile: ");
                        obiect.sortIntalniri(intalniri);

                        for (int i = 0; i < intalniri.size(); i++)
                        {
                            System.out.println("Data: " + intalniri.get(i).getData() + " locatie: " + intalniri.get(i).getLocatie() + " cu " + intalniri.get(i).getNume() + " " + intalniri.get(i).getPrenume());

                        }
                    }
                    if (nr == 2) {
                        System.out.print("Pentru ce luna doresti sa afli ce examene ai?(ex: martie) ");
                        String info = siruri.nextLine();
                        String luna = info.toLowerCase();
                        System.out.println();
                        System.out.println("In luna " + info + " ai urmatoarele examene: ");
                        obiect.afisareExamenLuna(examene, luna);

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
                {
                    System.out.println("Facturile ce trebuie achitate pe luna in curs");
                    obiect.afisareFacturiLunaCurenta(plati);

                }

                if (actiune == 2)
                {

                    System.out.print("Bugetul care ar ramane pe aceasta luna daca s-ar plati facturile: ");
                    int baniCheltuiti = obiect.bugetRamas(plati, titular.salariu);
                    System.out.println(baniCheltuiti);
                    titular.salariu -= baniCheltuiti;
                }
                if (actiune == 3)
                {
                    System.out.println("Facturile ce au trecut de data scadenta");
//                    aici trebuie sa vad in ce data curenta si sa o compar cu data scadenta a fiecarei facturi
                    obiect.facturiScadente(plati);
                }
                if (actiune == 4)
                {
                    System.out.print("Ce factura doriti sa adaugati(tipFactura dataFacturare dataScadenta pret): ");
                    String s = siruri.nextLine();
                    System.out.println();
                    String[] cuvinte = s.split(" ");
                    int pret = Integer.parseInt(cuvinte[3]);
                    obiect.adaugaFactura(plati, new Plata(cuvinte[0], cuvinte[1], cuvinte[2], pret));



                }
                if (actiune == 5)
                {
                    obiect.listeazaFacturi(plati);
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




    }
}
