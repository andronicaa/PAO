package proiect.utilitati.serviceClass;

import proiect.activitati.ActivitatiExtrascolare;
import proiect.lista_cumparaturi.ListaCumparaturi;
import proiect.plati.Plata;
import proiect.utilitati.DataCurenta;
import proiect.utilitati.Fisiere.CSVMapInterface;
import proiect.utilitati.Fisiere.WriteInCSVFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlatiService implements CSVMapInterface {

    public Plata process(String line) {
        String[] atributePlata = line.split(",");
        int pret = Integer.parseInt(atributePlata[3]);
        Plata plata = new Plata(atributePlata[0], atributePlata[1], atributePlata[2], pret);
        return plata;
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

    public void updateFilePlati(String numeFisier, ArrayList<Plata> plati) throws IOException {
        BufferedWriter writePlati = new BufferedWriter(new FileWriter(numeFisier));
        WriteInCSVFile<Plata> platiCSV = new WriteInCSVFile<>(writePlati, new Plata());
        for (Plata p : plati) {
            platiCSV.writeInFile(p);
        }
        writePlati.flush();
        writePlati.close();
    }

}
