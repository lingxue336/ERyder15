package org.example;

public class Main {

    public static void main(String[] args) {

        BikeService bikeService = new BikeService();
        AdminPanel adminPanel = new AdminPanel(bikeService);

        // Simulate system usage
        bikeService.reserveBike("B1");
        bikeService.startTrip("B1");
        bikeService.reserveBike("B1");
        bikeService.removeTrip("B1");

        // Open Admin Panel
        adminPanel.showMenu();
    }
}