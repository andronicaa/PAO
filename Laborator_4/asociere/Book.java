package asociere;

public class Book {
    private String title;
    private Page[] pages; // Compunere
    private LibraryRow libraryRow = null; //Agregare

    public Book(int size, String title, LibraryRow libraryRow) {
        this.libraryRow = libraryRow;
        this.title = title;
        pages = new Page[size];

        for (int i = 0; i < size; i++) {
            pages[i] = new Page("Page" + i, i);
        }
    }
}
