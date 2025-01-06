 package Server;

import controllers.CarController;
import models.Car;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerApp {
    public static void main(String[] args) {
        CarController carController = new CarController();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is running on port 5000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String command = reader.readLine();
                    if ("VIEW_CARS".equalsIgnoreCase(command)) {
                        List<Car> availableCars = carController.getAvailableCars();
                        for (Car car : availableCars) {
                            writer.println(car);
                        }
                        writer.println("END");
                    } else if (command.startsWith("BOOK_CAR")) {
                        int carId = Integer.parseInt(command.split(" ")[1]);
                        boolean success = carController.bookCar(carId);
                        writer.println(success ? "Booking Successful" : "Car Not Available");
                    } else if (command.startsWith("CANCEL_BOOKING")) {
                        int bookingId = Integer.parseInt(command.split(" ")[1]);
                        boolean success = carController.cancelBooking(bookingId);
                        writer.println(success ? "Booking Canceled" : "Failed to Cancel Booking");
                    } else {
                        writer.println("Invalid Command");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
