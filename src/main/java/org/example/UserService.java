package org.example;

import java.util.Iterator;
import java.util.LinkedList;

public class UserService {

    private LinkedList<RegisteredUsers> registeredUsersList = new LinkedList<>();

    public void addUser(RegisteredUsers user) {
        registeredUsersList.add(user);
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
}