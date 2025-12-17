import java.io.*;
import java.net.*;

public class TestClient {
    public static void main(String[] args) {
        try {
            System.out.println("Подключаемся к серверу...");
            Socket socket = new Socket("localhost", 12345);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), "UTF-8"));
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

            System.out.println("Ответ сервера:");
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if (line.isEmpty()) break;
            }

            String[] testCommands = {
                    "HELP",
                    "LIST",
                    "FIND Интернет",
                    "CALC S001 3",
                    "ADD S006,Мобильная связь,Связь,350.0,720",
                    "LIST",
                    "EXIT"
            };

            for (String command : testCommands) {
                System.out.println("\n>>> Отправка: " + command);
                out.println(command);
                Thread.sleep(500);

                System.out.println("<<< Ответ сервера:");
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    if (line.equals("END")) break;
                }
            }

            socket.close();
            System.out.println("\nТестирование завершено.");

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}