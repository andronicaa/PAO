package asociere.exemplu;

public class Article {
    private int quantity;
    private ArticleDetails articleDetails;

    public Article(String name, double unitPrice, int quantity) {
        this.articleDetails = new ArticleDetails(name, unitPrice);
        this.quantity = quantity;

    }

    public int getQuantity() {
        return quantity;
    }

    public ArticleDetails getArticleDetails() {
        return articleDetails;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setArticleDetails(ArticleDetails articleDetails) {
        this.articleDetails = articleDetails;
    }

    public double computePrice() {
        return this.articleDetails.getUnitPrice() * this.quantity;
    }
}
