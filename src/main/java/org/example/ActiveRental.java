package org.example;

import java.time.LocalDateTime;

public class ActiveRental {

    private String bikeID;
    private String userEmail;
    private LocalDateTime tripStartTime;

    // Constructor
    public ActiveRental(String bikeID, String userEmail, LocalDateTime tripStartTime) {
        this.bikeID = bikeID;
        this.userEmail = userEmail;
        this.tripStartTime = tripStartTime;
    }

    // Getters
    public String getBikeID() {
        return bikeID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public LocalDateTime getTripStartTime() {
        return tripStartTime;
    }

    @Override
    public String toString() {
        return "Bike ID: " + bikeID +
                ", User: " + userEmail +
                ", Trip Started: " + tripStartTime;
    }
}