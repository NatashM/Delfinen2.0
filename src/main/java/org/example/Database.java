package org.example;

import javax.lang.model.element.Name;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Database {
    private ArrayList<Member> members;
    Scanner scanner = new Scanner(System.in);
    private final File file = new File("swimMembersData.csv");
    private final FileHandler filehandler = new FileHandler();






    public Database() {
        try {
            this.members = filehandler.loadAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addMember(String name, int age, String birthday, String adress, boolean isActive, String grade, String swimTyp) {
        Member newMember = new Member(name, age, birthday, adress, isActive, grade, swimTyp);
        try {
            members.add(newMember);
            filehandler.saveMembers(members, file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean removeMembers(String name) {
        for (Member swimMembers : members) {
            if (swimMembers.getName().equalsIgnoreCase(name)) {
                members.remove(swimMembers);
                System.out.println("The swim member with the name " + name + " got removed from the swim club");
                System.out.println("Here is the list of the members" + members + "\n");
                return true;
            }
        }
        System.out.println("The members with the name " + name + " is not in the club");
        return false;

    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    //TODO
    public void createMembers() {
        Database MembersData = new Database();
        System.out.println("\n write your full name");
        String Name = scanner.next();

        System.out.println("\n write your age");
        int Age = scanner.nextInt();

        System.out.println("\n write your birthday");
        String birthday = scanner.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        LocalDate dateNow =  LocalDate.now();

        LocalDate date = LocalDate.parse(birthday, formatter);
        int age = dateNow.getYear() - date.getYear();

        String grade = "";

        if ( age <= 18 ) {
            System.out.println("You are a junior");
            grade ="junior";

        }
        else {
            System.out.println("You are a senior");
            grade="senior";
        }

        System.out.println("\n write your address: ");
        String adress = scanner.next();

        boolean isActive;
        do {
            System.out.print("You want a active membership (true/false): ");
            if (scanner.hasNextBoolean()) {
                isActive = scanner.nextBoolean();
                break;
            } else {
                System.out.println("You must precisely type true or false.");
                scanner.nextLine();
            }
        } while (true);


        System.out.println("");
        String swimType = scanner.nextLine();


        Member member = new Member(Name, Age, birthday, adress, isActive, grade, swimType);

        MembersData.addMember(Name, Age, birthday, adress, isActive, grade, swimType);

       System.out.println(member);
    }

    public void nameComparator() {
        NameComparator comparison = new NameComparator();
        Collections.sort(members, comparison);
        for (Member member: members){
            System.out.println(member.getName());
        }
    }
    public void showAllMembers() {
        if (members != null) {
            NameComparator comparison = new NameComparator();
            Collections.sort(members, comparison);
            System.out.println("A list of all members:");
            for (Member member : members) {
                System.out.println(member);
            }
        } else {
            System.out.println("Try again");
        }

    }


    public boolean activeComparator() {
        activeComparator comparator = new activeComparator();
        Collections.sort(members, comparator);

        for (Member member : members) {
            if (member.getIsActive())
                return true;
            System.out.println(member.getIsActive());
        }
        return false;
    }


    public void GradeComparator() {
        GradeComparator gradeComparator = new GradeComparator();
        Collections.sort(members, gradeComparator);

        for (Member member : members) {
            System.out.println(member.getName() + " = " + member.getGrade());

        }

    }

    public void SwimTypeComparator() {
        SwimTypeComparator swimTypeComparator = new SwimTypeComparator();
        Collections.sort(members, swimTypeComparator);

        for (Member member : members) {
            System.out.println(member.getSwimType());
        }
    }


    //categorized list of the members (switch case)
    public void sortedOptions() {
        int categorized = scanner.nextInt();
        scanner.nextLine();
        switch (categorized) {
            case 1 -> activeComparator();
            case 2 -> nameComparator();
            case 3 -> GradeComparator();
            case 4 -> SwimTypeComparator();

            default -> System.out.println("try againxxx");
        }
    }

    public void sortedOptionsForChairman() {
        int categorized = scanner.nextInt();
        scanner.nextLine();
        switch (categorized) {
            case 1 -> showAllMembers();  //show all members
            case 2 -> nameComparator();// sorted by name
            case 3 -> activeComparator(); // sorted..
            case 4 -> GradeComparator(); // sorted by grade
            case 5 -> SwimTypeComparator();// sorted by swimtype


            default -> System.out.println("Unable to understand your command");
        }
    }

}