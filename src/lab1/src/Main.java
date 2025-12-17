//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class lab1_variant9_group23isp3_Zhigaltsov {}
    public class Main {
        public static void main(String[] args) {
            //1 Рассчитать индекс массы тела
            double height = 1.85;
            int weight = 82;
            double imt = weight / (height * height);
            System.out.println("индекс массы тела = " + imt);
            //2 Вывести значение своего города
            String city = "Брянск";
            System.out.println(city);
            //3 Поменяйте местами значение двух переменных
            double x = 5.6;
            double y = 6.5;
            double z;
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            z = y;
            y = x;
            x = z;
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        }
    }