import java.io.Serializable;

// Простой класс для сериализации
class SimpleData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;    // Строковое поле
    private int number;     // Числовое поле

    public SimpleData(String text, int number) {
        this.text = text;
        this.number = number;
    }

    @Override
    public String toString() {
        return "SimpleData{text='" + text + "', number=" + number + "}";
    }
}
