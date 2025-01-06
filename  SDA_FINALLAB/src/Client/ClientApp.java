/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;
 

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to the server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. View Cars");
                System.out.println("2. Book a Car");
                System.out.println("3. Cancel Booking");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1: // View Cars
                        writer.println("VIEW_CARS");
                        displayServerResponse(reader);
                        break;

                    case 2: // Book a Car
                        System.out.print("Enter Car ID to book: ");
                        int carId = scanner.nextInt();
                        writer.println("BOOK_CAR " + carId);
                        System.out.println(reader.readLine());
                        break;

                    case 3: // Cancel Booking
                        System.out.print("Enter Booking ID to cancel: ");
                        int bookingId = scanner.nextInt();
                        writer.println("CANCEL_BOOKING " + bookingId);
                        System.out.println(reader.readLine());
                        break;

                    case 4: // Exit
                        writer.println("EXIT");
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayServerResponse(BufferedReader reader) throws IOException {
        String response;
        while (!(response = reader.readLine()).equals("END")) {
            System.out.println(response);
        }
    }
}
