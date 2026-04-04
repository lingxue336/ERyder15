package org.example;

import java.util.Iterator;
import java.util.LinkedList;

public class UserService {

    private LinkedList<RegisteredUsers> registeredUsersList = new LinkedList<>();

    public RegisteredUsers addNewUser(String fullName, String emailAddress, String dateOfBirth,
                                      long cardNumber, String cardExpiryDate, String cardProvider,
                                      int cvv, String userType, String[] lastThreeTrips) {

        RegisteredUsers newUser;

        if (userType.equalsIgnoreCase("VIP")) {

            newUser = new VIPUser(fullName, emailAddress, dateOfBirth,
                    cardNumber, cardExpiryDate, cardProvider,
                    cvv, userType, lastThreeTrips);

        } else {

            newUser = new RegularUser(fullName, emailAddress, dateOfBirth,
                    cardNumber, cardExpiryDate, cardProvider,
                    cvv, userType, lastThreeTrips);
        }

        registeredUsersList.add(newUser);

        return newUser;
    }

    public void viewUsers() {

        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users.");
            return;
        }

        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
        }
    }

    public void removeUser(String email) {

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();

        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();

            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                iterator.remove();
                System.out.println("User removed.");
                return;
            }
        }

        System.out.println("User not found.");
    }

    public RegisteredUsers findUserByEmail(String email) {

        for (RegisteredUsers user : registeredUsersList) {

            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                return user;
            }
        }

        return null;
    }
}