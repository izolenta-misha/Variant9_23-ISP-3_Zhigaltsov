import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    private static final int PORT = 12345;
    private static List<Service> services = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== Сервер управления услугами ===\n");
        System.out.println("Сервер запущен на порту " + PORT);

        initializeServices();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Ожидание подключений...\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился новый клиент: " +
                        clientSocket.getInetAddress().getHostAddress());

                new ClientHandler(clientSocket).start();
            }

        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }

    private static void initializeServices() {
        services.add(new Service("S001", "Интернет 100 Мбит/с", "Интернет", 500.0, 720));
        services.add(new Service("S002", "Кабельное TV", "Развлечения", 300.0, 720));
        services.add(new Service("S003", "Хостинг сайта", "IT-услуги", 1500.0, 168));
        services.add(new Service("S004", "Техническая поддержка", "Поддержка", 200.0, 1));
        services.add(new Service("S005", "Абонентская плата", "Базовая", 100.0, 720));

        System.out.println("Загружено услуг: " + services.size());
    }

    private static String processCommand(String command) {
        String[] parts = command.split(" ", 2);
        String action = parts[0].toUpperCase();
        String argument = parts.length > 1 ? parts[1] : "";

        switch (action) {
            case "LIST":
                return listServices();
            case "FIND":
                return findService(argument);
            case "CALC":
                return calculatePrice(argument);
            case "ADD":
                return addService(argument);
            case "HELP":
                return getHelp();
            default:
                return "Неизвестная команда. Введите HELP для списка команд.";
        }
    }

    private static String listServices() {
        if (services.isEmpty()) return "Список услуг пуст.";
        StringBuilder result = new StringBuilder();
        result.append("=== Список всех услуг ===\n");
        for (Service service : services) {
            result.append(service.toString()).append("\n");
        }
        result.append("Всего услуг: ").append(services.size());
        return result.toString();
    }

    private static String findService(String query) {
        if (query.isEmpty()) return "Укажите ID или название услуги для поиска.";
        List<Service> found = new ArrayList<>();
        for (Service service : services) {
            if (service.getId().equalsIgnoreCase(query) ||
                    service.getName().toLowerCase().contains(query.toLowerCase())) {
                found.add(service);
            }
        }
        if (found.isEmpty()) return "Услуги по запросу '" + query + "' не найдены.";
        StringBuilder result = new StringBuilder();
        result.append("=== Найдено услуг: ").append(found.size()).append(" ===\n");
        for (Service service : found) {
            result.append(service.toString()).append("\n");
        }
        return result.toString();
    }

    private static String calculatePrice(String argument) {
        try {
            String[] parts = argument.split(" ");
            if (parts.length != 2) return "Использование: CALC <ID_услуги> <количество>";
            String serviceId = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            Service service = null;
            for (Service s : services) {
                if (s.getId().equalsIgnoreCase(serviceId)) {
                    service = s;
                    break;
                }
            }
            if (service == null) return "Услуга с ID '" + serviceId + "' не найдена.";
            double total = service.getPrice() * quantity;
            return String.format("Стоимость %d единиц услуги '%s': %.2f руб.",
                    quantity, service.getName(), total);
        } catch (NumberFormatException e) {
            return "Ошибка: количество должно быть числом.";
        } catch (Exception e) {
            return "Ошибка расчета: " + e.getMessage();
        }
    }

    private static String addService(String argument) {
        try {
            String[] parts = argument.split(",");
            if (parts.length != 5) return "Ошибка формата. Используйте: ADD id,название,категория,цена,длительность";
            String id = parts[0].trim();
            String name = parts[1].trim();
            String category = parts[2].trim();
            double price = Double.parseDouble(parts[3].trim());
            int duration = Integer.parseInt(parts[4].trim());
            for (Service service : services) {
                if (service.getId().equals(id)) {
                    return "Ошибка: услуга с ID '" + id + "' уже существует.";
                }
            }
            Service newService = new Service(id, name, category, price, duration);
            services.add(newService);
            return "Услуга успешно добавлена: " + newService.toString();
        } catch (NumberFormatException e) {
            return "Ошибка: цена и длительность должны быть числами.";
        } catch (Exception e) {
            return "Ошибка добавления: " + e.getMessage();
        }
    }

    private static String getHelp() {
        return "=== Доступные команды ===\n" +
                "LIST - Показать все услуги\n" +
                "FIND <запрос> - Найти услугу по ID или названию\n" +
                "CALC <ID> <количество> - Рассчитать стоимость\n" +
                "ADD id,название,категория,цена,длительность - Добавить услугу\n" +
                "HELP - Показать эту справку\n" +
                "EXIT - Выйти из программы\n";
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        public ClientHandler(Socket socket) { this.socket = socket; }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {

                out.println("Добро пожаловать в систему управления услугами!");
                out.println("Для списка команд введите HELP\n");

                String clientMessage;
                while ((clientMessage = in.readLine()) != null) {
                    System.out.println("Клиент [" + socket.getInetAddress() + "] отправил: " + clientMessage);
                    if (clientMessage.equalsIgnoreCase("EXIT")) {
                        out.println("До свидания!");
                        break;
                    }
                    String response = processCommand(clientMessage);
                    out.println(response);
                    System.out.println("Отправлен ответ клиенту");
                }
                System.out.println("Клиент отключился: " + socket.getInetAddress());
            } catch (IOException e) {
                System.err.println("Ошибка при работе с клиентом: " + e.getMessage());
            } finally {
                try { socket.close(); } catch (IOException e) { System.err.println("Ошибка при закрытии сокета: " + e.getMessage()); }
            }
        }
    }
}