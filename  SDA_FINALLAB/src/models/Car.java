/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
public class Car {
    private int id;
    private String model;
    private boolean available;

    public Car(int id, String model, boolean available) {
        this.id = id;
        this.model = model;
        this.available = available;
    }

    public int getId() { return id; }
    public String getModel() { return model; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Car ID: " + id + ", Model: " + model + ", Available: " + available;
    }
}
