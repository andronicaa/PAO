package asociere.compozitie;

public class TestSale {
    public static void main(String[] args) {
        Sale sale = new Sale(2);
        sale.addArticles("mouse", 120,1);
        sale.addArticles("mousePad", 23, 3);
        System.out.println(sale.computeTotal());
    }
}
