import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = "book.ser";

        // 1. Создание и сериализация
        System.out.println("=== Сериализация ===");
        Book book1 = new Book("Java для начинающих", 2023);

        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(book1);
        oos.close();
        fos.close();
        System.out.println("Объект сохранен в " + filename + "\n");

        // 2. Десериализация
        System.out.println("=== Десериализация ===");
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Book book2 = (Book) ois.readObject();

        ois.close();
        fis.close();

        System.out.println("Объект восстановлен из файла");
        System.out.println(book2);

        System.out.println("\n=== Анализ ===");
        System.out.println("Конструктор не вызывался при десериализации!");
        System.out.println("Данные восстановлены напрямую из файла.");
    }
}