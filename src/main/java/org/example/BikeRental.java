package org.example;

import java.util.Scanner;

public class BikeRental {

    private UserService userService;

    private BikeService bikeService = new BikeService();
    private RentalService rentalService = new RentalService();

    public void simulateApplicationInput() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        String bikeID = bikeService.validateLocation(location);

        if (bikeID == null) {
            return;
        }

        bikeService.reserveBike(bikeID);

        System.out.print("Enter user email: ");
        String email = scanner.nextLine();

        RegisteredUsers user = userService.findUserByEmail(email);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        rentalService.startRental(bikeID, user);

        rentalService.viewActiveRentals();

        rentalService.endRental(bikeID);

        bikeService.releaseBike(bikeID);
    }
}