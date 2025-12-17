import java.io.Serializable;

class Book implements Serializable {
    private String title;
    private int year;

    public Book(String title, int year) {
        System.out.println("Вызван конструктор Book для: " + title);
        this.title = title;
        this.year = year;
    }

    public String toString() {
        return "Книга: \"" + title + "\", год издания: " + year;
    }
}
