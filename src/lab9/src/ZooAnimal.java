import javax.naming.Name;

public class ZooAnimal {
    private String name;
    private String type;
    private int mood;
    private int age;

    public ZooAnimal(String name, String type, int mood, int age) {
        setName(name);
        setType(type);
        setMood(mood);
        setAge(age);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getMood() { return mood; }
    public void setMood(int mood) { this.mood = mood; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Животное: " + name +
                ", Тип: " + type +
                ", Настроение: " + mood +
                ", Возраст: " + age;
    }
}
