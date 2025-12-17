import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация сериализации ===\n");

        // 1. Создание объекта
        SimpleData data = new SimpleData("Пример текста", 12345);
        System.out.println("1. Создан объект: " + data);

        // 2. Сериализация в файл
        String filename = "data.ser";

        try {
            // Создаем потоки
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Сериализуем объект
            objectOut.writeObject(data);

            // Закрываем потоки
            objectOut.close();
            fileOut.close();

            System.out.println("2. Объект успешно сериализован в файл: " + filename);

            // 3. Проверяем размер файла
            File file = new File(filename);
            System.out.println("3. Размер файла: " + file.length() + " байт");

            // 4. Десериализация
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            SimpleData loadedData = (SimpleData) objectIn.readObject();

            objectIn.close();
            fileIn.close();

            System.out.println("4. Объект десериализован: " + loadedData);

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== Задание выполнено ===");
    }
}