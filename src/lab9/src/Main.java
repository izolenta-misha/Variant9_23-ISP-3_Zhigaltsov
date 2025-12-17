
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ZooAnimal> animals = new ArrayList<>();
        animals.add(new ZooAnimal("Лев", "Хищник", 65, 5));
        animals.add(new ZooAnimal("Слон", "Травоядное", 80, 10));
        animals.add(new ZooAnimal("Жираф", "Травоядное", 75, 7));
        animals.add(new ZooAnimal("Тигр", "Хищник", 90, 6));
        animals.add(new ZooAnimal("Зебра", "Травоядное", 60, 4));

        System.out.println("Вывод через for:");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println(animals.get(i));
        }

        System.out.println("\nВывод через for-each:");
        for (ZooAnimal animal : animals) {
            System.out.println(animal);
        }
        animals.add(new ZooAnimal("Медведь", "Хищник", 70, 8));
        animals.add(new ZooAnimal("Обезьяна", "Примат", 85, 3));

        animals.remove(3);
        animals.remove(new ZooAnimal("Зебра", "Травоядное", 60, 4));
        System.out.println("\nТекущий размер списка: " + animals.size());
        ZooAnimal selected = animals.get(2);
        System.out.println("\nИмя выбранного животного: " + selected.getName());
        selected.setMood(95);
        for (ZooAnimal animal : animals) {
            if (animal.getMood() > 70) {
                System.out.println("\nЖивотное с настроением > 70: " + animal);
                break;
            }
        }
        ZooAnimal check = new ZooAnimal("Слон", "Травоядное", 80, 10);
        System.out.println("\nСодержит ли список слона? " + animals.contains(check));
        animals.clear();
        System.out.println("\nРазмер после очистки: " + animals.size());
    }
}