import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static double circleLenght(double r)
    {
        return 2*Math.PI*r;
    }
    public static double circleArea(double r)
    {
        return Math.PI*Math.pow(r,2);
    }
    public static void isInsideCircle(int x,int y,double r) {
        int x0 = 0;
        int y0 = 0;
        if ((Math.pow((x - x0), 2) + Math.pow((y - y0), 2) <= Math.pow(r, 2))) {
            System.out.println("Точка входит в окружность");
        }
        else {
            System.out.println("Точка не входит в окружность");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите радиус");
        double r = scanner.nextDouble();
        System.out.println("Длина окружности равна " + circleLenght(r));
        System.out.println("Площадь круга равна " + circleArea(r));
        System.out.println("Введите х точки");
        int x = scanner.nextInt();
        System.out.println("Введите у точки");
        int y = scanner.nextInt();
        isInsideCircle(x,y,r);
    }
}