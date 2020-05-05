package proiect.utilitati.serviceClass;

import proiect.lista_cumparaturi.ListaCumparaturi;
import proiect.utilitati.Fisiere.CSVMapInterface;
import proiect.utilitati.Fisiere.WriteInCSVFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListaCumparaturiService implements CSVMapInterface {

    public ListaCumparaturi process(String line) {
//        trebuie sa mapam linia
        String[] atributeObiect = line.split(",");
        ListaCumparaturi produsCantitate = new ListaCumparaturi(atributeObiect[0], atributeObiect[1]);
//        returnez un produs de acest tip
        return produsCantitate;

    }

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
    public void updateFile(String numeFisier, ArrayList<ListaCumparaturi> listaGeneralaCumparaturi) throws IOException {
        BufferedWriter writeCumparaturi = new BufferedWriter(new FileWriter(numeFisier));
        WriteInCSVFile<ListaCumparaturi> cumparaturiCSV = new WriteInCSVFile<>(writeCumparaturi, new ListaCumparaturi());
        for (ListaCumparaturi shop : listaGeneralaCumparaturi) {
            cumparaturiCSV.writeInFile(shop);
        }
        writeCumparaturi.flush();
        writeCumparaturi.close();
    }

}
