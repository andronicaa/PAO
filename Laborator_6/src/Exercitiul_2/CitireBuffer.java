package Exercitiul_2;

import java.io.*;
import java.util.Arrays;

public class CitireBuffer {
    public static void main(String[] args) {
        try (BufferedReader fin = new BufferedReader(new FileReader("date.in"));
             BufferedWriter fout = new BufferedWriter(new FileWriter("date.out"));) {
            String linie;
            while ((linie = fin.readLine()) != null) {
                String cuv[] = linie.split(" ");
                Arrays.sort(cuv);
                System.out.println(Arrays.toString(cuv));
                for (int i = 0; i < cuv.length; i++)
                    fout.write(cuv[i] + " ");
                fout.write("\n");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fisierul nu exista");
        } catch (IOException ex) {
            System.out.println("Operatie de citire/scriere esuata");
        }
    }
}
