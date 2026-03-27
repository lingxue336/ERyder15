package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void userManagementOptions() {

        int choice;

        do {
            System.out.println("\nWelcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addNewUsers();
                    break;
                case 2:
                    viewRegisteredUsers();
                    break;
                case 3:
                    removeRegisteredUsers();
                    break;
                case 4:
                    updateRegisteredUsers();
                    break;
                case 5:
                    BikeRental rental = new BikeRental();
                    rental.simulateApplicationInput();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }

        } while (choice != 5);
    }



    private void addNewUsers() {

        System.out.print("How many users do you want to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {

            System.out.println("\nEntering details for user " + (i + 1));

            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Email Address: ");
            String email = scanner.nextLine();

            System.out.print("Date of Birth: ");
            String dob = scanner.nextLine();

            System.out.print("Card Number: ");
            long cardNumber = scanner.nextLong();
            scanner.nextLine();

            System.out.print("Card Provider: ");
            String provider = scanner.nextLine();

            System.out.print("Card Expiry Date: ");
            String expiry = scanner.nextLine();

            System.out.print("CVV: ");
            int cvv = scanner.nextInt();
            scanner.nextLine();

            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            String[] trips = new String[3];

            for (int j = 0; j < 3; j++) {

                System.out.println("\nEnter details for Trip " + (j + 1));

                System.out.print("Date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                System.out.print("Source: ");
                String source = scanner.nextLine();

                System.out.print("Destination: ");
                String destination = scanner.nextLine();

                System.out.print("Fare (€): ");
                double fare = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Feedback (or NULL): ");
                String feedback = scanner.nextLine();

                StringBuilder sb = new StringBuilder();
                sb.append("Date: ").append(date)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(destination)
                        .append(", Fare (€): ").append(fare)
                        .append(", Feedback: ").append(feedback);

                trips[j] = sb.toString();
            }

            RegisteredUsers user = new RegisteredUsers(
                    fullName, email, dob, cardNumber,
                    expiry, provider, cvv, userType, trips
            );

            registeredUsersList.add(user);
        }
    }



    private void viewRegisteredUsers() {

        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }

        for (RegisteredUsers user : registeredUsersList) {
            System.out.println("----------------------------------");
            System.out.println(user);
        }
    }



    private void removeRegisteredUsers() {

        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }

        System.out.print("Enter email address to remove: ");
        String email = scanner.nextLine();

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                iterator.remove();
                found = true;
                System.out.println("User removed successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

  

    private void updateRegisteredUsers() {

        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }

        System.out.print("Enter email address to update: ");
        String email = scanner.nextLine();

        for (RegisteredUsers user : registeredUsersList) {

            if (user.getEmailAddress().equalsIgnoreCase(email)) {

                System.out.print("Type new full name (Press ENTER for no change): ");
                String fullName = scanner.nextLine();
                if (!fullName.isEmpty()) {
                    user.setFullName(fullName);
                }

                System.out.print("Type new date of birth (Press ENTER for no change): ");
                String dob = scanner.nextLine();
                if (!dob.isEmpty()) {
                    user.setDateOfBirth(dob);
                }

                System.out.print("Type new card number (Enter 0 for no change): ");
                long cardNumber = scanner.nextLong();
                scanner.nextLine();
                if (cardNumber != 0) {
                    user.setCardNumber(cardNumber);
                }

                System.out.print("Type new user type (Press ENTER for no change): ");
                String userType = scanner.nextLine();
                if (!userType.isEmpty()) {
                    user.setUserType(userType);
                }

                System.out.println("User updated successfully.");
                return;
            }
        }

        System.out.println("No user found with this email address");
    }

}
