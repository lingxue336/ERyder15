package org.example;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {

    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    private static final double BASE_FARE = 3.0;

    // Start rental
    public void startRental(String bikeID, RegisteredUsers user) {

        LocalDateTime startTime = LocalDateTime.now();
        ActiveRental rental = new ActiveRental(bikeID, user, startTime);

        activeRentalsList.add(rental);

        System.out.println("Rental started successfully.");
    }

    // End rental
    public void endRental(String bikeID) {

        Iterator<ActiveRental> iterator = activeRentalsList.iterator();

        while (iterator.hasNext()) {

            ActiveRental rental = iterator.next();

            if (rental.getBikeID().equals(bikeID)) {

                RegisteredUsers user = rental.getUser();

                double finalFare = user.calculateFare(BASE_FARE);

                iterator.remove();

                System.out.println("Trip ended successfully.");
                System.out.println("Total Fare: $" + finalFare);

                user.displayUserType();   // 多态体现

                return;
            }
        }

        System.out.println("Rental not found.");
    }

    // View active rentals
    public void viewActiveRentals() {

        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals.");
            return;
        }

        for (ActiveRental rental : activeRentalsList) {
            System.out.println(rental);
        }
    }
}