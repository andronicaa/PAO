import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws IOException  {
//        se va incerca realizarea unei conexiuni ce un server chiar in momentul crearii unui socket de tip client
        Scanner sc = new Scanner(System.in);
        System.out.print("Adresa serverului: ");
        String adresa = sc.next();
        System.out.print("Portul serverului: ");
        int port = sc.nextInt();
        sc.nextLine();

//        conectare la server - ii dam ca param adresa si port
        Socket cs = new Socket(adresa, port);
        System.out.println("Conectare reusita la server");

//        preluam fluxurile de intrare/iesire de la/catre server
        DataInputStream dis = new DataInputStream(cs.getInputStream());
        DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

//        citim o linie de text de la tastatura si o transmitem serverului
//        asteptam raspunsul de la server
//        chat-ul se inchide cand se tasteaza "X"
        while (true) {
            System.out.print("Mesaj de trimis: ");
            String line = sc.nextLine();
            dos.writeUTF(line);
            if (line.equals("X"))
                break;
            line = dis.readUTF();
            System.out.println("Mesaj receptionat: " + line);
        }

        cs.close();
        dis.close();
        dos.close();
    }
}
