package org.example;

import java.util.Scanner;

public class AdminPanel {

    private UserService userService = new UserService();

    public void userManagementOptions() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Add User");
        System.out.println("2. View Users");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:

                System.out.print("Full name: ");
                String fullName = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Date of Birth: ");
                String dob = scanner.nextLine();

                System.out.print("Card Number: ");
                long cardNumber = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Card Expiry Date: ");
                String expiry = scanner.nextLine();

                System.out.print("Card Provider: ");
                String provider = scanner.nextLine();

                System.out.print("CVV: ");
                int cvv = scanner.nextInt();
                scanner.nextLine();

                System.out.print("User Type: ");
                String userType = scanner.nextLine();

                // 创建空的 lastThreeTrips 数组
                String[] lastThreeTrips = new String[3];

                RegisteredUsers user =
                        new RegisteredUsers(fullName, email, dob,
                                cardNumber, expiry, provider,
                                cvv, userType, lastThreeTrips);

                userService.addUser(user);

                break;

            case 2:
                userService.viewUsers();
                break;
        }
    }
}