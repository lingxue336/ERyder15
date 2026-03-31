package org.example;

import java.util.Scanner;


public class UserInputHandler {
    private Scanner scanner = new Scanner(System.in);

    public int getInt(String prompt) {
        System.out.print(prompt);
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public long getLong(String prompt) {
        System.out.print(prompt);
        long num = scanner.nextLong();
        scanner.nextLine();
        return num;
    }

    public double getDouble(String prompt) {
        System.out.print(prompt);
        double num = scanner.nextDouble();
        scanner.nextLine();
        return num;
    }
}