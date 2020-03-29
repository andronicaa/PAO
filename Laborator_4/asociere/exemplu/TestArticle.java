package asociere.exemplu;

public class TestArticle {
    public static void main(String[] args) {
        Article[]  articles = new Article[3];
        articles[0] = new Article("Mouse", 23.2, 2);
        articles[1] = new Article("MousePad", 5.3, 3);
        articles[2] = new Article("Printer", 4.2, 2);

        for (int i = 0; i < articles.length; i++) {
            System.out.print(articles[i].getArticleDetails().getName());
            System.out.print("->");
            System.out.println(articles[i].getArticleDetails().getUnitPrice());
        }
    }
}
