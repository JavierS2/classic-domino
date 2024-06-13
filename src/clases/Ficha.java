package clases;

// Clase que representa una ficha de dominó

public class Ficha {
    private final int value1;
    private final int value2;

    public Ficha(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Ficha() {
        this.value1 = generateValue();
        this.value2 = generateValue();
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int generateValue() {
        return (int) Math.round(Math.random() * 6);
    }

    @Override
    public String toString() {
        return "|" + value1 +
                "|" + value2 + "| ";
    }
}

