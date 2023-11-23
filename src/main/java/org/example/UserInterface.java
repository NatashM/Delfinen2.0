package org.example;

import org.example.Controller;

import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void startProgram(){
        System.out.println("Welcome to the delfin swim club!");
        System.out.println("\nMenu: ");
        System.out.println("1. Sign up");
        System.out.println("2. A list of all members");
        System.out.println("3. Categorized list of all members ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                controller.createMembers();
                break;
            case 2:
                controller.ShowListOfMember();
                break;
            case 3:
                System.out.println("1.categorize by active or passive ");
                System.out.println("2.Sort by name: ");
                System.out.println("3.Sort by grade");
                System.out.println("4.sort by competition status");
                controller.SortedOptions();
                break;

        }

    }
}
