package org.example;

import javax.lang.model.element.Name;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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


    public void addMember(String name, int age, int birthday, String adress, boolean isActive, String grade, String swimTyp) {
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

    public void createMembers() {
        Database MembersData = new Database();
        System.out.println("\n write your full name");
        String Name = scanner.next();

        System.out.println("\n write your age");
        int Age = scanner.nextInt();

        System.out.println("\n write your birthday");
        int birthday = scanner.nextInt();


        System.out.println("\n write your adress: ");
        String adress = scanner.next();

        System.out.println("Write if your a active or not");
        boolean isActive = scanner.nextBoolean();


        System.out.println("");
        String grade = scanner.nextLine();

        System.out.println("");
        String swimType = scanner.nextLine();


        Member member = new Member(Name, Age, birthday, adress, isActive, grade, swimType);

        MembersData.addMember(Name, Age, birthday,adress, isActive, grade, swimType);

        System.out.println(member);
    }

    public void NameComparator() {
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

        public boolean activeComparator(){
            activeComparator comparator = new activeComparator();
            Collections.sort(members, comparator);

            for (Member member : members) {
                if (member.getIsActive())
                    return true;
                System.out.println(member.getIsActive());
            }
            return false;
        }



        public void GradeComparator(){
        GradeComparator gradeComparator = new GradeComparator();
        Collections.sort(members, gradeComparator);

        for (Member member : members){
            System.out.println(member.getGrade());
        }
    }
        public void SwimTypeComparator(){
            SwimTypeComparator swimTypeComparator = new SwimTypeComparator();
            Collections.sort(members, swimTypeComparator);

            for (Member member : members){
                System.out.println(member.getSwimType());
            }
            }


        //categorized list of the members (switch case)
        public void sortedOptions () {
            int categorized = scanner.nextInt();
            scanner.nextLine();
            switch (categorized) {
                case 1 -> activeComparator();
                case 2 -> NameComparator();
                case 3 -> GradeComparator();
                case 4 -> SwimTypeComparator();

                default -> System.out.println("try againxxx");
            }
        }
    }
