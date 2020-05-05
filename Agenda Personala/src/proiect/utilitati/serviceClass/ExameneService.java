package proiect.utilitati.serviceClass;

import proiect.activitati.Examene;
import proiect.activitati.Intalniri;
import proiect.lista_cumparaturi.ListaCumparaturi;
import proiect.utilitati.Fisiere.CSVMapInterface;
import proiect.utilitati.Fisiere.WriteInCSVFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExameneService implements CSVMapInterface {

    public Examene process(String line) {
        String[] atributeExamen = line.split(",");
        Examene examen = new Examene(atributeExamen[0], atributeExamen[1], atributeExamen[2], atributeExamen[3], atributeExamen[4]);
        return examen;
    }


    public void afisareExamene(ArrayList<Examene> examene)
    {System.out.println("Examenele sunt: ");
        for (int i = 0; i < examene.size(); i++)
        {
            System.out.println("Data " + examene.get(i).getData() + " in " + examene.get(i).getLocatie() + " la " + examene.get(i).getCurs());
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

    public void updateFileExamene(String numeFisier, ArrayList<Examene> examene) throws IOException {
        BufferedWriter writeExamene = new BufferedWriter(new FileWriter(numeFisier));
        WriteInCSVFile<Examene> exameneCSV = new WriteInCSVFile<>(writeExamene, new Examene());
        for (Examene ex : examene) {
            exameneCSV.writeInFile(ex);
        }
        writeExamene.flush();
        writeExamene.close();
    }

}
