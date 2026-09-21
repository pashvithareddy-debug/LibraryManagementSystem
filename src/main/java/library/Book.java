package library;

public class Book {
    private int id, quantity, available;
    private String title, author, category;

    public Book(String title,String author,String category,int quantity){
        this.title=title; this.author=author; this.category=category;
        this.quantity=quantity; this.available=quantity;
    }
    public Book(int id,String title,String author,String category,int quantity,int available){
        this.id=id; this.title=title; this.author=author; this.category=category;
        this.quantity=quantity; this.available=available;
    }
    public int getId(){return id;}
    public String getTitle(){return title;}
    public String getAuthor(){return author;}
    public String getCategory(){return category;}
    public int getQuantity(){return quantity;}
    public int getAvailable(){return available;}
}
