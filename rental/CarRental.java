package rental;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.Random;

public class CarRental{
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Car car : cars) {
            sb.append(car.toString() + "\n");
            i++;
        }
        return sb.toString();
    }
    private ArrayList<Car> cars;
    public CarRental(String filename) throws FileNotFoundException {
        cars = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while(line != null) {
                if (line.matches(".+:.+,.+")) {
                    String[] token = line.split(":");
                    String brand = token[0];
                    token = token[1].split(",");
                    String licensePlate = token[0];
                    double price = Double.parseDouble(token[1]);
                    Car newCar = Car.make(brand, licensePlate, price);
                    if (newCar != null) {
                        cars.add(Car.make(brand, licensePlate, price));
                    }
                }
                line = br.readLine();
            }
        } catch (NumberFormatException e) {
            throw e;
        } catch ( IOException  e) {

        }
    }
    public int numberOfCars() {return cars.size();}
    public void insertionSort() {
        Car pivot;
        int i = 0;
        for (Car car : cars ){
            if (i > 0) {
                pivot = car;
                int j = i - 1;
                while (j >= 0 && pivot.isCheaperThan(cars.get(j))) {
                    cars.set(j+1, cars.get(j));
                    j--;
                }
                cars.set(j+1, pivot);
            }
            i++;
        }
    }
    public double weightedAverage() {
        if (cars.size() == 0) return -1.0;
        int weight = 1;
        double sum = 0.0;
        int sumWeight = 0;
        for (Car car : cars) {
            sum += car.getPrice() * weight;
            sumWeight += weight;
            weight++;
        }
        return sum / sumWeight;

    }
    public Car rentCheapest() {
        if (cars.size() == 0) return null;
        insertionSort();
        return cars.removeFirst();
    }
    public ArrayList<Car> sale() {
        ArrayList<Car> allCars = new ArrayList<Car>(cars);
        Random random = new Random();
        for (Car car: allCars ) {
            if (random.nextInt(3) == 1) car.decreasePrice();
        }
        return allCars;
    }
}

