import java.awt.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//Задача 25
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        int d = scanner.nextInt();
        System.out.println("Введите месяц");
        int m = scanner.nextInt();
        if(d<=31 && m<=12)
            System.out.println("valid");
        else
            System.out.println("invalid");
        //Задача 18
        System.out.println("Ввдите сумму");
        int sum = scanner.nextInt();
        if(sum<0){
            System.out.println("invalid");
        }
        else if(sum<1000){
            System.out.println("Сумма  = " + sum);
            System.out.println("Скидка 0%");
        }
        else if(sum<5000)
        {
            System.out.println("Сумма  = " + (sum-sum*0.05));
            System.out.println("Скидка 5%");
        }
        else
        {
            System.out.println("Сумма  = " + (sum-sum*0.1));
            System.out.println("Скидка 10%");
        }
        //Задача 16
        System.out.println("Введите x");
        int x = scanner.nextInt();
        int z =5;
        int e = 2;
        String s = "+";
        String s1 = "-";
        String s2 = "0";
        String s3 = " ";
        System.out.println((x>0 & x!=0) ? s : s1);
        System.out.println((x==0) ? s2 : s3);





    }
}

