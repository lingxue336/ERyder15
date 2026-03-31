package org.example;


public class AdminPanel {
    private UserService userService = new UserService();
    private UserInputHandler input = new UserInputHandler();

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

            choice = input.getInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addNewUsers();
                case 2 -> viewRegisteredUsers();
                case 3 -> removeRegisteredUsers();
                case 4 -> updateRegisteredUsers();
                case 5 -> {
                    BikeRental rental = new BikeRental();
                    rental.simulateApplicationInput();
                }
                default -> System.out.println("Invalid choice. Please try again");
            }
        } while (choice != 5);
    }

    private void addNewUsers() {
        int count = input.getInt("How many users do you want to add? ");

        for (int i = 0; i < count; i++) {
            System.out.println("\nEntering details for user " + (i + 1));

            String fullName = input.getString("Full Name: ");
            String email = input.getString("Email Address: ");
            String dob = input.getString("Date of Birth: ");
            long cardNumber = input.getLong("Card Number: ");
            String provider = input.getString("Card Provider: ");
            String expiry = input.getString("Card Expiry Date: ");
            int cvv = input.getInt("CVV: ");
            String userType = input.getString("User Type: ");

            String[] trips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("\nEnter details for Trip " + (j + 1));
                String date = input.getString("Date (YYYY-MM-DD): ");
                String source = input.getString("Source: ");
                String destination = input.getString("Destination: ");
                double fare = input.getDouble("Fare (€): ");
                String feedback = input.getString("Feedback (or NULL): ");

                trips[j] = "Date: " + date +
                        ", Source: " + source +
                        ", Destination: " + destination +
                        ", Fare (€): " + fare +
                        ", Feedback: " + feedback;
            }

            RegisteredUsers user = new RegisteredUsers(
                    fullName, email, dob, cardNumber,
                    expiry, provider, cvv, userType, trips
            );
            userService.addUser(user);
        }
    }

    private void viewRegisteredUsers() {
        if (userService.isUserListEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        for (RegisteredUsers user : userService.getAllUsers()) {
            System.out.println("----------------------------------");
            System.out.println(user);
        }
    }

    private void removeRegisteredUsers() {
        if (userService.isUserListEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        String email = input.getString("Enter email address to remove: ");
        boolean success = userService.removeUserByEmail(email);
        System.out.println(success ? "User removed successfully." : "No user found with this email address");
    }

    private void updateRegisteredUsers() {
        if (userService.isUserListEmpty()) {
            System.out.println("No registered users to update");
            return;
        }
        String email = input.getString("Enter email address to update: ");
        String newName = input.getString("Type new full name (Press ENTER for no change): ");
        String newDob = input.getString("Type new date of birth (Press ENTER for no change): ");
        long newCard = input.getLong("Type new card number (Enter 0 for no change): ");
        String newType = input.getString("Type new user type (Press ENTER for no change): ");

        boolean success = userService.updateUserByEmail(email, newName, newDob, newCard, newType);
        System.out.println(success ? "User updated successfully." : "No user found with this email address");
    }
}