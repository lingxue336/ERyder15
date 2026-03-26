package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;


public class UserRegistration {
    private static final double  VIP_DISCOUNT_UNDER_18_BIRTHDAY =25.0;
    private static final double  VIP_DISCOUNT_UNDER_18 =20.0;
    private static final double  VIP_BASE_FEE=100.0;

    

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;

    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;


    public UserRegistration(){

    }



    public UserRegistration(String fullName, String emailAddress, String dateOfBirth, long cardNumber,
                            String cardExpiryDate, String cardProvider, int cvv, String userType) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
    }

    public void registration() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.print("Please enter your choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 


        if (choice == 1) {
            userType = "Regular User";
        } else {
            userType = "VIP User";
        }


        System.out.print("Enter your full name: ");
        fullName = scanner.nextLine();
        System.out.print("Enter your email address: ");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);
        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.print("Enter your card number (VISA/MasterCard/American Express only): ");
        cardNumber = scanner.nextLong();
        cardNumberValid = analyseCardNumber(cardNumber);
        scanner.nextLine();

        System.out.print("Enter card expiry date (MM/YY): ");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.print("Enter card CVV: ");
        cvv = scanner.nextInt();
        validCVV = analyseCVV(cvv);
        finalCheckpoint();
        scanner.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration();
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate now = LocalDate.now();
        int age = Period.between(dob, now).getYears();
        boolean isBirthday = (dob.getMonth() == now.getMonth() && dob.getDayOfMonth() == now.getDayOfMonth());

        // VIP用户折扣判断
        if (userType.equals("VIP User")) {
            if (age <= 18 && age > 12) {
                if (isBirthday) {
                    System.out.println("Happy Birthday!");
                    System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                    minorAndBirthday = true;
                } else {
                    System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                    minor = true;
                }
            }
        }

        if (age <= 12 || age >= 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            System.exit(0);
        }
        return true;
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));
        boolean valid = false;

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            valid = true;
        }

        else if (cardNumStr.length() == 16 &&
                ((firstTwoDigits >= 51 && firstTwoDigits <= 55) ||
                        (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            valid = true;
        }
        else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            valid = true;
        }
        if (!valid) {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
            System.out.println("Going back to the start of the registration.");
            registration();
        }
        return valid;
    }

    private boolean analyseCardExpiryDate(String expiry) {
        int month = Integer.parseInt(expiry.substring(0, 2));
        int year = 2000 + Integer.parseInt(expiry.substring(3, 5));
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();
        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card.");
            System.out.println("Going back to the start fo the registration process…");
            registration();
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);
        boolean valid = false;

        if (cardProvider.equals("American Express") && cvvStr.length() == 4) {
            valid = true;
        } else if ((cardProvider.equals("VISA") || cardProvider.equals("MasterCard")) && cvvStr.length() == 3) {
            valid = true;
        }
        if (valid) {
            System.out.println("Card CVV is valid.");
        } else {
            System.out.println("Invalid CVV for the given card.");
            System.out.println("Going back to the start of the registration process.");
            registration();
        }
        return valid;
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if (!emailValid) System.out.println("Invalid email address");
            if (!ageValid) System.out.println("Invalid age");
            if (!cardNumberValid) System.out.println("Invalid card number");
            if (!cardStillValid) System.out.println("Card has expired");
            if (!validCVV) System.out.println("Invalid CVV");
            System.out.println("Going back to the start of the registration process.");
            registration();
        }
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18_BIRTHDAY) / 100;
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18) / 100;
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardStr = String.valueOf(cardNumber);
        String lastFour = cardStr.substring(cardStr.length() - 4);
        System.out.println("Thank you for your payment.");
        System.out.println("A fee of " + feeToCharge + " has been charged to your card ending with ****" + lastFour);
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "Registration successful! Here are your details:\n" +
                "User Type: " + userType + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email Address: " + emailAddress + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Card Number: " + censoredNumber + "\n" +
                "Card Provider: " + cardProvider + "\n" +
                "Card Expiry Date: " + cardExpiryDate;
    }
}




