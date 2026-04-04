package org.example;

import java.time.LocalDateTime;

public class ActiveRental {

    private String bikeID;
    private RegisteredUsers user;
    private LocalDateTime startTime;

    public ActiveRental(String bikeID, RegisteredUsers user, LocalDateTime startTime) {
        this.bikeID = bikeID;
        this.user = user;
        this.startTime = startTime;
    }

    public String getBikeID() {
        return bikeID;
    }

    public RegisteredUsers getUser() {
        return user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "Bike ID: " + bikeID +
                ", User: " + user.getFullName() +
                ", Start Time: " + startTime;
    }
}