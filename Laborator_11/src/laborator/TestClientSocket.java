package laborator;

public class TestClientSocket {
    public static void main(String[] args) {
        GreetClient client = new GreetClient();
        client.startConnection('127.0.0.1', 8081);
        String response = client.sendMessage("Hello server");
        System.out.println(response);
    }
}
