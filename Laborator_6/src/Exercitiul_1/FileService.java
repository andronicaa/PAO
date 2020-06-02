package Exercitiul_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileService {

    public static void write() throws FileNotFoundException {
        FileOutputStream outputStream = new FileOutputStream("ex1.txt");
    }

    public static void writeToFile() {
        try {
            FileOutputStream outputStream = new FileOutputStream("ex1.txt");
            // write to file
        } catch (FileNotFoundException e) {

        }
    }

    public static void writeToFileAndPrint() {
        try {
            FileOutputStream outputStream = new FileOutputStream("ex1.txt");
        } catch (FileNotFoundException e) {

        } finally {
            System.out.println("Info");
        }

    }

//    sometimes, the code can throw more than one exception and we can have mor than one catch block handle each individually

    public static void writeNumberToFile() {
        try {
            FileOutputStream outputStream = new FileOutputStream("ex1.txt");
            outputStream.write(String.valueOf(53).getBytes());
        } catch (FileNotFoundException e) {
            // handle FileNotFoundException
        } catch (IOException e) {
            // handle IOException
        } finally {
            System.out.println("Finally block");
        }
    }
//  multiple catch blocks(in the same block)
    public static void writeTextToFile() {
        try {
            FileOutputStream outputStream = new FileOutputStream("ex1.txt");
            int number = Integer.parseInt("2f5");
            outputStream.write(String.valueOf(number).getBytes());

        } catch (IOException | NumberFormatException e) {
            // handle exception
        } finally {
            System.out.println("Finally block");
        }
    }

    public static void writeGrades(Object o) throws AccessDeniedException {
        if (o instanceof Student) {
            throw new AccessDeniedException("Access denied for students");
        } else if (o instanceof Profesor) {
            // write grades to file
        }

    }

    public static void readFromFile(String file) {
        if (file.isBlank() || file.isEmpty()) {
            throw new IllegalArgumentException("File name is not valid");
        }


    }
//  we can also choose to rethrow an exception we've caught
    public static void writeInfoTofile() throws FileNotFoundException {
        try {
            FileOutputStream outputStream = new FileOutputStream("ex1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }


}
