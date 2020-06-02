package laborator;

public class TestSocket {
    public static void main(String[] args) {
        GreetServer server = new GreetServer();
//        pornim serverul pe portul 8081
        server.startServer(8081);
    }
}
