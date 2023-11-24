package org.example;

import org.example.Controller;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void startProgram() {
        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("\n Welcome to the delfin swim club!");
            System.out.println(" Choose a way to access the system:  ");
            System.out.println("1. New member? then please proceed to sign up!");
            System.out.println("2. Dolphin clubs chairman");
            System.out.println("3. Coach Whale");
            System.out.println("4. Accountant");
            System.out.println("9. Exit program");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        controller.createMembers();
                        break;

                    case 2:
                        System.out.println("\n 1. Show all members");
                        System.out.println(" 2. Sorted by name");
                        System.out.println(" 3. Sort by active or passive members:");
                        System.out.println(" 4. Sort by grade: ");
                        System.out.println(" 5. Sort by competition status:");
                        System.out.println(" 6. Remove a member from the system");

                        controller.sortedOptionsForChairman();
                        break;
                    case 3:
                        System.out.println("1. Sort by active or passive members:");
                        System.out.println("2. Sort by name: ");
                        System.out.println("3. Sort by grade:");
                        System.out.println("4. Sort by competition status:");
                        System.out.println("5. Search for a swimmer");
                        System.out.println("6. Top 5 swimmers");
                        controller.SortedOptionsForCoach();
                        break;

                    case 5:
                        controller.searchSwimmer();

                    case 9:
                        System.out.println("The program is closing down");
                        exitProgram = true;


                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Type in a valid value.");
                scanner.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println("Error: wrong date format. Use only YYYY.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error has occurred: " + e.getMessage());
                scanner.nextLine();
            }

        }
    }






}