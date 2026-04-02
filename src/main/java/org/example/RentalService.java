package org.example;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {

    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

    public void startRental(String bikeID, String email) {

        LocalDateTime startTime = LocalDateTime.now();
        ActiveRental rental = new ActiveRental(bikeID, email, startTime);

        activeRentalsList.add(rental);
    }

    public void endRental(String bikeID) {

        Iterator<ActiveRental> iterator = activeRentalsList.iterator();

        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();

            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                System.out.println("Trip ended successfully.");
                break;
            }
        }
    }

    public void viewActiveRentals() {

        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals.");
        } else {
            for (ActiveRental rental : activeRentalsList) {
                System.out.println(rental);
            }
        }
    }
}