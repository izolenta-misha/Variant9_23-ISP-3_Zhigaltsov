
class Book{
    private final String title;
    private final int pages;

    public Book()
    {
        this.title = "Книга";
        this.pages = 0;
    }
    public Book(String title, int pages)
    {
        this.title = title;
        this.pages = pages;
    }
    public Book(Book other)
    {
        this.pages = other.pages;
        this.title = other.title;
    }
    public boolean isBig()
    {
        return pages > 300;
    }
    public String brief()
    {
        return title + " (" +  pages + " страниц" + ")";
    }
    @Override
    public String toString()
    {
        return "Книга : " + title + " Кол-во страниц : " + pages;
    }

}
public class Main {
    public static void main(String[] args) {
        Book book1 = new Book();
        if (book1.isBig()) System.out.println("Книга 1 большая");
        else System.out.println("Книга 1 небольшая");
        System.out.println(book1.brief());
        Book book2 = new Book("Война и мир", 1232);
        Book book3 = new Book("Путь к успеху", 123);
        Book book4 = new Book(book3);
        if (book2.isBig()) System.out.println("Книга 1 большая");
        else System.out.println("Книга 2 небольшая");
        if (book3.isBig()) System.out.println("Книга 2 большая");
        else System.out.println("Книга 3 небольшая");
        System.out.println(book2.brief());
        System.out.println(book3.brief());
        if (book4.isBig()) System.out.println("Книга 4 большая");
        else System.out.println("Книга 4 небольшая");
        System.out.println(book4.brief());
        System.out.println(book2);
    }
}
