package org.example;

import java.util.Scanner;

public class BikeRental {

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

        rentalService.startRental(bikeID, "user@email.com");

        rentalService.viewActiveRentals();

        rentalService.endRental(bikeID);

        bikeService.releaseBike(bikeID);
    }
}