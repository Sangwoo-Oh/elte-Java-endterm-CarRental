package rental;

import java.lang.Character;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.text.NumberFormat;
import java.lang.Number;
import java.util.Locale;

public class Car {

    @Override
    public String toString() {
        return brand + " (" + licensePlate + ") " + String.format("%5s", price) + " EUR";
    }

    private String brand;
    private String licensePlate;
    private double price;
    public double getPrice() { return price;}
    private static final double MAX_PRICE = 500.0;
    private final static Car CAR_OF_THE_YEAR = new Car("Alfa Romeo","ABC 123", MAX_PRICE);
    private Car(String brand, String licensePlate, double price) {
        this.brand = brand;
        this.price = price;
        this.licensePlate = licensePlate;
    }
    public boolean isCheaperThan(Car other) {
        return price < other.price;
    }
    public void decreasePrice() {
        if (price >= 10 && price < MAX_PRICE) {
            price -= 10;
        }
    }
    public static Car make(String brand, String licensePlate, double price) {
        if(brand.length() < 2) return null;
        Stream<Character> cStream = IntStream.range(0,brand.length()-1).mapToObj(i -> brand.charAt(i));

        if(!cStream.allMatch(x -> Character.isLetter(x) || x == ' ')) return null;
        if(!isValidLicensePlate(licensePlate)) return null;
        if(price <= 0.0 || price > MAX_PRICE) return null;
        return new Car(brand, licensePlate, price);
    }
    private static boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate.length() != 7) return false;
        for (int i=0; i<7; i++) {
            if (i < 3) {
                if (!Character.isUpperCase(licensePlate.charAt(i))) {
                    return false;
                }
            } else if (i == 3) {
                if (licensePlate.charAt(i) != ' ') {return false;}
            } else {
                if (!Character.isDigit(licensePlate.charAt(i))) {return false;}
            }
        }
        return true;
    }
}
