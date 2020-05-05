package proiect.utilitati.serviceClass;

import proiect.lista_cumparaturi.ListaCumparaturi;
import proiect.persoana.Persoana;
import proiect.utilitati.Fisiere.CSVMapInterface;
import proiect.utilitati.Fisiere.WriteInCSVFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class AgendaTelefonService implements CSVMapInterface {
//      Metoda suprascrisa din interfata CSVMapInterface ce mapeaza o linie
    public String[] process(String line) {
        String[] linieParsata = line.split(",");
        return linieParsata;
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


}
