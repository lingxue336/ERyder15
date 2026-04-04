package org.example;

import java.util.Scanner;

public class AdminPanel {

    private BikeService bikeService;
    private UserService userService;
    private RentalService rentalService;

    private Scanner scanner = new Scanner(System.in);

    public AdminPanel(BikeService bikeService,
                      UserService userService,
                      RentalService rentalService) {

        this.bikeService = bikeService;
        this.userService = userService;
        this.rentalService = rentalService;
    }

    public void showMenu() {

        while (true) {

            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. Add New User");
            System.out.println("2. Start Rental");
            System.out.println("3. End Rental");
            System.out.println("4. View Active Rentals");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addNewUser();
                    break;

                case 2:
                    startRental();
                    break;

                case 3:
                    endRental();
                    break;

                case 4:
                    rentalService.viewActiveRentals();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addNewUser() {

        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Date of Birth: ");
        String dob = scanner.nextLine();

        System.out.print("Card Number: ");
        long cardNumber = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Card Expiry Date: ");
        String expiry = scanner.nextLine();

        System.out.print("Card Provider: ");
        String provider = scanner.nextLine();

        System.out.print("CVV: ");
        int cvv = scanner.nextInt();
        scanner.nextLine();

        System.out.print("User Type (VIP/Regular): ");
        String userType = scanner.nextLine();

        String[] lastTrips = new String[0];

        RegisteredUsers user = userService.addNewUser(
                fullName, email, dob,
                cardNumber, expiry, provider,
                cvv, userType, lastTrips
        );

        System.out.println("User created successfully.");
        user.displayUserType();
    }

    private void startRental() {

        System.out.print("Bike ID: ");
        String bikeID = scanner.nextLine();

        System.out.print("User Email: ");
        String email = scanner.nextLine();

        RegisteredUsers user = userService.findUserByEmail(email);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        rentalService.startRental(bikeID, user);
    }

    private void endRental() {

        System.out.print("Bike ID: ");
        String bikeID = scanner.nextLine();

        rentalService.endRental(bikeID);
    }
}