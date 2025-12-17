package app;
import logging.Logger;
import logging.FileLogger;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger("1234", 3);
        Logger logger2 = new Logger("1234", 5);
        FileLogger filelogger = new FileLogger("ого", 1);

        logger.log("Лог");
        logger.log("Лог с левелом",3);
        logger.log("логгер с ошибкой",true);

        filelogger.setfilepath("путь файла");
        filelogger.log("файл");


        System.out.println(logger);
        System.out.println(logger2);
        System.out.println(filelogger);
    }

}