package org.example;

import java.time.LocalDateTime;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;

public class BikeService {

    private boolean bikeAvailable = true;

    private Stack<ERyderLog> systemLogs = new Stack<>();
    private Queue<BikeRequest> bikeRequest = new ArrayDeque<>();


    public void reserveBike(String bikeID) {

        ERyderLog log = new ERyderLog(
                "BR" + System.currentTimeMillis(),
                "Bike " + bikeID + " was rented",
                java.time.LocalDateTime.now()
        );

        systemLogs.push(log);

        System.out.println("Bike reserved successfully.");
    }


    public void startTrip(String bikeID) {

        ERyderLog log = new ERyderLog(
                "TS" + System.currentTimeMillis(),
                "Trip started for bike " + bikeID,
                LocalDateTime.now()
        );

        systemLogs.push(log);

        System.out.println("Trip started.");
    }


    public void removeTrip(String bikeID) {

        bikeAvailable = true;

        ERyderLog log = new ERyderLog(
                "TE" + System.currentTimeMillis(),
                "Trip ended for bike " + bikeID,
                LocalDateTime.now()
        );

        systemLogs.push(log);

        System.out.println("Trip ended.");

        if (!bikeRequest.isEmpty()) {

            BikeRequest nextRequest = bikeRequest.remove();

            bikeAvailable = false;

            System.out.println("Bike assigned to next user: "
                    + nextRequest.getUserEmail());
        }
    }


    public void viewSystemLogs() {

        if (systemLogs.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }

        for (ERyderLog log : systemLogs) {
            System.out.println(log);
        }
    }


    public void viewQueue() {

        if (bikeRequest.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (BikeRequest request : bikeRequest) {
            System.out.println(request);
        }
    }


    public void updateQueue() {

        if (!bikeRequest.isEmpty()) {
            bikeRequest.remove();
            System.out.println("First request removed.");
        } else {
            System.out.println("Queue is empty.");
        }
    }

    public String validateLocation(String location) {
        if (location == null || location.isEmpty()) {
            System.out.println("Invalid location.");
            return null;
        }

        return  location;
    }

    public void releaseBike(String bikeID) {
        bikeAvailable = true;

        ERyderLog log = new ERyderLog(
                "RL" + System.currentTimeMillis(),
                "Bike " + bikeID + " released.",
                java.time.LocalDateTime.now()
        );

        systemLogs.push(log);

        System.out.println("Bike released successfully.");
    }
}