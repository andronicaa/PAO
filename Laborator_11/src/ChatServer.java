import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
// EXEMPLU CURS
public class ChatServer {
    public static void main(String[] args) throws IOException {
//        socket-ul pentru server
        ServerSocket ss = null;
//        socket-ul pentru client
        Socket cs = null;

        Scanner sc  = new Scanner(System.in);
        System.out.print("Portul: ");
//        instantiem serverul
        int port = sc.nextInt();
        ss = new ServerSocket(port);
        sc.nextLine();
        System.out.println("Serverul a pornit!");

//        serverul asteapta un client sa se conecteze
        cs = ss.accept();

        System.out.println("Un client s-a conectat la server");

//        serverul preia fluxurile de la/catre client

        DataInputStream dis = new DataInputStream(cs.getInputStream());
        DataOutputStream dos = new DataOutputStream((cs.getOutputStream()));

//        citim linia de text transmisa de catre client si o afisam
//        citim o linie si o transmitem clientului
//        chat-ul se inchide cand clientul transmite litera X
        while (true) {
//            citim de pe flux
            String line = dis.readUTF();
            System.out.println("Mesaj receptionat: " + line);
            if (line.equals("X"))
                break;

            System.out.print("Mesaj de trimis: ");
            line = sc.nextLine();
            dos.writeUTF(line);
        }

        dis.close();
        dos.close();
        cs.close();
        ss.close();
    }
}
