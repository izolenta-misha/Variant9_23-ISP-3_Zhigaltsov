
public class Main {
    public static void main(String[] args) {
        String readPath = "input.txt";
        String writePath = "output.txt";
        String text = "Пример текста для записи в файл";

        // Демонстрация чтения файла
        System.out.println("Чтение файла:");
        FileProcessing.readFile(readPath);

        // Демонстрация записи в файл
        System.out.println("\nЗапись в файл:");
        FileProcessing.writeFile(writePath, text);

        // Пример вызова с несуществующим файлом
        System.out.println("\nПопытка чтения несуществующего файла:");
        FileProcessing.readFile("nonexistent.txt");
    }
}
