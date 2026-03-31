package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserService {
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();


    public void addUser(RegisteredUsers user) {
        registeredUsersList.add(user);
    }


    public List<RegisteredUsers> getAllUsers() {
        return registeredUsersList;
    }


    public boolean removeUserByEmail(String email) {
        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateUserByEmail(String email, String newName, String newDob, long newCard, String newType) {
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equalsIgnoreCase(email)) {
                if (!newName.isEmpty()) user.setFullName(newName);
                if (!newDob.isEmpty()) user.setDateOfBirth(newDob);
                if (newCard != 0) user.setCardNumber(newCard);
                if (!newType.isEmpty()) user.setUserType(newType);
                return true;
            }
        }
        return false;
    }


    public boolean isUserListEmpty() {
        return registeredUsersList.isEmpty();
    }
}