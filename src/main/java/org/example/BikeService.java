package org.example;

import java.time.LocalDateTime;

public class BikeService {

    public String validateLocation(String location) {

        for (Bike bike : BikeDatabase.bikes) {

            if (bike.getLocation().equalsIgnoreCase(location)
                    && bike.isAvailable()) {

                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }

        System.out.println("Sorry, no bikes are available at this location.");
        return null;
    }

    public void reserveBike(String bikeID) {

        for (Bike bike : BikeDatabase.bikes) {

            if (bike.getBikeID().equals(bikeID)) {

                bike.setIsAvailable(false);
                bike.setLastUsedTime(LocalDateTime.now());

                System.out.println("Bike reserved successfully.");
                break;
            }
        }
    }

    public void releaseBike(String bikeID) {

        for (Bike bike : BikeDatabase.bikes) {

            if (bike.getBikeID().equals(bikeID)) {

                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                break;
            }
        }
    }
}