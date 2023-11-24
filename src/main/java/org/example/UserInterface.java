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
            System.out.println(" Choose access as:  ");
            System.out.println("1. A new member, please sign up!");
            System.out.println("2. Dolphin clubs chairman");
            System.out.println("3. Coach Whale");
            System.out.println("4. Accountant");
            System.out.println("5. Exit program");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        controller.createMembers();
                        break;

                    case 2:
                        System.out.println("\n 1.Show all members");
                        System.out.println(" 2.Sorted by name");
                        System.out.println(" 3.Sort by active or passive members:");
                        System.out.println(" 4.Sort by grade: ");
                        System.out.println(" 5.sort by competition status:");

                        controller.sortedOptionsForChairman();
                        break;
                    case 3:
                        System.out.println("1.Sort by active or passive members:");
                        System.out.println("2.Sort by name: ");
                        System.out.println("3.Sort by grade:");
                        System.out.println("4.sort by competition status:");
                        controller.SortedOptions();
                        break;

                    case 5:
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