package proiect;
import com.sun.source.tree.Tree;
import proiect.activitati.ActivitatiExtrascolare;
import proiect.activitati.Examene;
import proiect.activitati.Intalniri;
import proiect.persoana.Persoana;
import proiect.activitati.Activitate;
import proiect.plati.Plata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.*;

import proiect.lista_cumparaturi.ListaCumparaturi;

import javax.swing.*;
public class Test {
//-----metoda care adauga un nou produs la lista daca acesta nu mai exista deja
    public ArrayList<ListaCumparaturi> adaugaProdus(String produs, String cantitate, ArrayList<ListaCumparaturi> listaGenerala) {
//        facem un nou obiect
        ListaCumparaturi produsNou = new ListaCumparaturi(produs, cantitate);
        boolean contine = false;
        for (ListaCumparaturi i : listaGenerala) {
            if (i.equals(produsNou))
            {   contine = true;
                System.out.println("Contine deja acest produs");
            }
        }


        if (contine == false) {
            listaGenerala.add(produsNou);
        }
//        returnez lista actualizata de cumparaturi
        return listaGenerala;
    }

//    ---------metoda care sterge produs din lista de cumparaturi
    public ArrayList<ListaCumparaturi> stergeProdus(int numarProdus, ArrayList<ListaCumparaturi> listaGenerala) {
        listaGenerala.remove(numarProdus - 1 );
        return listaGenerala;

    }
//--------------afiseaza lista de cumparaturi
    public void display(ArrayList<ListaCumparaturi> listaGenerala) {
        for (int i = 0; i < listaGenerala.size(); i++) {
            System.out.println((i + 1) + "."+ listaGenerala.get(i).getProdus() + " " + listaGenerala.get(i).getCantitate());
        }
    }
//-------se adauga numere de telefon in agenda de telefon, cheia este reprezentata de nume si prenume(dupa care este si sortata lista alfabetic) si valoare este numarul de telefon
    public TreeMap<Persoana, String> adaugaNrTelefon(String nume, String prenume, String numarTelefon, TreeMap<Persoana, String> agendaTelefon){
        agendaTelefon.put(new Persoana(nume, prenume), numarTelefon);
        return agendaTelefon;

    }

    public void stergeNumarTelefon(String nume, String prenume, TreeMap<Persoana, String> agendaTelefon) {
        Persoana stergePers = new Persoana(nume, prenume);
        agendaTelefon.remove(stergePers);

    }

    public void displayTelefon(TreeMap<Persoana, String> agendaTelefon) {
        System.out.println("Agenda dumneavoastra este: ");
        for (Map.Entry<Persoana, String> entry : agendaTelefon.entrySet()) {
            System.out.println(entry.getKey().getNume() + " " + entry.getKey().getPrenume() + " "+ entry.getValue());
        }
    }

    public void displayActivitati(ArrayList<Activitate> activitati)
    {System.out.println("Activitatile dumneavoastra sunt: ");
        for (Object o : activitati)
        {
            System.out.println(o.toString());
        }
    }
// -----sorteaza intalnirile in functie de data si prioritatea pe care o au intr-o zi daca doua date sunt egale
    public ArrayList<Intalniri> sortIntalniri(ArrayList<Intalniri> intalniri) {
        Collections.sort(intalniri, new SortIntalniri());
        return intalniri;
    }

    public void afisareIntalniri(ArrayList<Intalniri> intalniri)
    {System.out.println("Intalnirile sunt: ");
        for (int i = 0; i < intalniri.size(); i++)
        {
            System.out.println("Data " + intalniri.get(i).getData() + " la " + intalniri.get(i).getLocatie() + " cu " + intalniri.get(i).getNume() + " " + intalniri.get(i).getPrenume());
        }
    }
    public void afisareExamene(ArrayList<Examene> examene)
    {System.out.println("Examenele sunt: ");
        for (int i = 0; i < examene.size(); i++)
        {
            System.out.println("Data " + examene.get(i).getData() + " in " + examene.get(i).getLocatie() + " la " + examene.get(i).getCurs());
        }
    }
    public void afisareHobby(ArrayList<ActivitatiExtrascolare> hobby)
    {System.out.println("Hobby-urile sunt: ");
        for (int i = 0; i < hobby.size(); i++)
        {
            System.out.println("Data " + hobby.get(i).getData() + " la " + hobby.get(i).getLocatie() + " facand " + hobby.get(i).getTipActivitate());
        }
    }
//    -------fiind data o luna de la tastatura se afiseaza toate examenele din acea luna
    public void afisareExamenLuna(ArrayList<Examene> examene, String luna) {
        ArrayList<Examene> exams = new ArrayList<Examene>();
        ArrayList<String> lunileAnului = new ArrayList<String>();
        lunileAnului.add("ianuarie"); lunileAnului.add("februarie"); lunileAnului.add("martie"); lunileAnului.add("aprilie");
        lunileAnului.add("mai"); lunileAnului.add("iunie"); lunileAnului.add("iulie"); lunileAnului.add("august");
        lunileAnului.add("septembrie"); lunileAnului.add("octombrie"); lunileAnului.add("noiembrie"); lunileAnului.add("decembrie");
        for (int i = 0; i < examene.size(); i++)
        {   //extrag data fiecarui examen din vector
            String lunaEx = examene.get(i).getData();
            String s = lunaEx.substring(lunaEx.indexOf("/") + 1, lunaEx.length());
//            luna parsata de forma mm(poate sa contina 0 ca prima cifra)
            String lunaParsata = s.substring(0, s.indexOf("/"));
            String primChar = lunaParsata.substring(0, 1);
            boolean ok = false;
            int numarLuna;
            String sirLuna;
            if (primChar.equals("0") == true)
            {   ok = true;
                sirLuna = lunaParsata.substring(1);
                numarLuna = Integer.parseInt(lunaParsata.substring(1));
            }
            else
            {
                numarLuna = Integer.parseInt(lunaParsata);
            }


          if (lunileAnului.get(numarLuna - 1).equals(luna) == true)
                exams.add(examene.get(i));



        }
        if (exams.size() == 0)
        {
            System.out.println("E bine, poti sa te uiti linistit la seriale, n-ai niciun examen.");
        }
        for (int i = 0; i < exams.size(); i++)
        {
            System.out.println("Data: " + exams.get(i).getData() + " Locatie: " + exams.get(i).getLocatie() + "Curs: " + exams.get(i).getCurs());
        }
    }

//    ----adauga factura la lista
    public void adaugaFactura(ArrayList<Plata> plati, Plata factura)
    {
        plati.add(factura);
    }
// ------afiseaza facturile ce trebuie platite in luna curenta
    public void afisareFacturiLunaCurenta(ArrayList<Plata> plati)
    {
//        aflu data curenta(doar luna)
        ArrayList<Plata> platiCurente = new ArrayList<Plata>();
        DataCurenta dataCurr = new DataCurenta();
        String data = dataCurr.lunaCurenta();
//        extrag luna din fiecare obiect din vectorul plati(din data facturare)
        for (int i = 0; i < plati.size(); i++)
        {
            String dataFactura = plati.get(i).getDataFacturare();
            String subs = dataFactura.substring(dataFactura.indexOf("/") + 1, dataFactura.length());
            String luna = subs.substring(0, subs.indexOf("/"));
            if (luna.equals(data) == true)
            {
                platiCurente.add(plati.get(i));
            }
        }

        for (int j = 0; j < platiCurente.size(); j++)
        {
            System.out.println("Factura: " + platiCurente.get(j).getFactura() + " Data scadenta: " + platiCurente.get(j).getDataScadenta() + " Luna: " + platiCurente.get(j).getDataFacturare());

        }
    }
//--------afiseaza bugetul ce ar ramane daca am plati toate facturile din acea luna
    public int bugetRamas(ArrayList<Plata> plati, int salariu)
    {
        DataCurenta dataCurr = new DataCurenta();
        String data = dataCurr.lunaCurenta();

        for (int i = 0; i < plati.size(); i++)
        {   String dataFactura = plati.get(i).getDataFacturare();
            String subs = dataFactura.substring(dataFactura.indexOf("/") + 1, dataFactura.length());
            String luna = subs.substring(0, subs.indexOf("/"));

            if (luna.equals(data) == true)
        {

            salariu -= plati.get(i).getPret();
        }

        }
        return salariu;
    }
//-------afiseaza facturile ce au trecut de data scadenta
    public void facturiScadente(ArrayList<Plata> plati)
    {   ArrayList<Plata> factScadente = new ArrayList<Plata>();
        DataCurenta dataCurr = new DataCurenta();
        String data = dataCurr.dataCurenta();
        SimpleDateFormat sdfo = new SimpleDateFormat("dd/MM/yyyy");
        Date d1 = null;
        try {
            d1 = sdfo.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < plati.size(); i++)
        {
            System.out.println(plati.get(i).getDataScadenta().compareTo(data));
            Date d2 = null;
            try {
                d2 = sdfo.parse(plati.get(i).getDataScadenta());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d1.compareTo(d2) > 0)
            {
                factScadente.add(plati.get(i));
            }
        }
        for (int j = 0; j < factScadente.size(); j++)
        {
            System.out.println("Factura: " + factScadente.get(j).getFactura() + " Data scadenta: " + factScadente.get(j).getDataScadenta() + " Luna: " + factScadente.get(j).getDataFacturare());
        }
    }
//-----------listeaza facturile
    public void listeazaFacturi(ArrayList<Plata> plati)
    {
        System.out.println("Facturile dumneavoastra sunt: ");
        for (int i = 0; i < plati.size(); i++)
        {   System.out.println(i+1 + ".");
            System.out.println("Factura: " + plati.get(i).getFactura() + " Data scadenta: " + plati.get(i).getDataScadenta() + " Luna: " + plati.get(i).getDataFacturare());
        }
    }


}
