package asociere;

public class Library {
    public static void main(String[] args) {
        LibraryRow row = new LibraryRow("A1");
        Book book = new Book(100, "title", row);
//        dupa ce nu mai exista nicio referinta la obiectul Book, Garbage Collector va sterge aces instanta, dar obiectul LibraryRow transmic contructorului nu este afectat


        book = null;
    }

}
