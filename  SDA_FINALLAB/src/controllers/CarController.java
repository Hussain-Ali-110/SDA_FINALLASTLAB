 package controllers;

import models.Car;
import java.util.ArrayList;
import java.util.List;

public class CarController {
    private List<Car> cars;

    public CarController() {
        cars = new ArrayList<>();
        cars.add(new Car(1, "Toyota Corolla", true));
        cars.add(new Car(2, "Honda Civic", true));
        cars.add(new Car(3, "Hyundai Elantra", true));
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public boolean bookCar(int carId) {
        for (Car car : cars) {
            if (car.getId() == carId && car.isAvailable()) {
                car.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    public boolean cancelBooking(int bookingId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
