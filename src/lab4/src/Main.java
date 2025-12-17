import java.util.Random;
import java.util.Scanner;

public class Main {
    static void printArray(int[] a)
    {
        for(int a1:a)
        {
            System.out.printf(a1+" ");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность массива");
        int d = scanner.nextInt();
        Random rand = new Random();
        int [] lab4_array = new int[d];
        for(int i=0;i<d;i++)
        {
            lab4_array[i] = rand.nextInt(-100,100);
        }
        printArray(lab4_array);
        System.out.println();
        System.out.println("Минимальное число массива");
        int min=1000;
        for(int i=0;i<d;i++)
        {
            if(lab4_array[i]<min)
            {
               min = lab4_array[i];
            }
        }
        System.out.println(min);
        System.out.println("Максимальное число массива");
        int max=-1000;
        for(int i=0;i<d;i++)
        {
            if(lab4_array[i]>max)
            {
                max = lab4_array[i];
            }
        }
        System.out.println(max);
        System.out.println("Разность между числами массива");
        System.out.println(max-min);
    }
}