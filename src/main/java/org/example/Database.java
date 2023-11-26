package org.example;

import comparators.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Database {
    private ArrayList<Member> members;
    private ArrayList<Member> senior = new ArrayList<>();
    private ArrayList<Member> junior = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private final File file = new File("swimMembersData.csv");
    private final FileHandler filehandler = new FileHandler();


//add ligesom inventory
    public Database() {
        try {
            this.members = filehandler.loadAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMember(String name, int age, String birthday, String address, boolean isActive, String grade, String swimType,String trainingTime) {
        Member newMember = new Member(name, age, birthday, address, isActive, grade, swimType,trainingTime);
        try {
            members.add(newMember);
            filehandler.saveMembers(members, file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if( age <= 18){
            try{
                junior.add(newMember);
                filehandler.saveMembers(junior,new File("juniorMembers.csv"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else{
            try{
                senior.add(newMember);
                filehandler.saveMembers(senior,new File("seniorMembers.csv"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        try{
            members.add(newMember);
            filehandler.saveMembers(members, file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
    public void CreateMembers() {
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
            System.out.println("You will be joining the junior team");
            grade ="junior";

        }
        else {
            System.out.println("You will be joining the senior team");
            grade="senior";
        }

        System.out.println("\n write your address: ");
        String address = scanner.next();

        boolean isActive;
        do {
            System.out.print("You want a active membership (true/false): ");
            if (scanner.hasNextBoolean()) {
                isActive = scanner.nextBoolean();
                break;
            } else {
                System.out.println("An error occured:/ \n Please write true or false ");
                scanner.nextLine();
            }
        } while (true);


        System.out.println("Your desired swim discipline");
        String swimType = scanner.next();

        System.out.println("Write your training time");
        String trainingTime = scanner.next();


        Member member = new Member(Name, Age, birthday, address, isActive, grade, swimType,trainingTime);

        MembersData.addMember(Name, Age, birthday, address, isActive, grade, swimType,trainingTime);

       System.out.println(member);
    }

    public void NameComparator() {
        NameComparator comparison = new NameComparator();
        Collections.sort(members, comparison);
        for (Member member: members){
            System.out.println(member.getName());
        }
    }
    public void ShowAllMembers() {
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


    public void trainingTimeForEachSwimmer() {

        System.out.println("Enter the name of the swimmer,to find their training time:");
        String userIndput = scanner.nextLine();

        ArrayList<Member> searchResult = getMembers();

        if (searchResult.isEmpty()) {
            System.out.println("No training results available for this person.");
        } else if (searchResult.size() > 1) {
            int counter = 0;

            for (Member member : members) {
                if (member.getName().startsWith(userIndput)) {
                    System.out.println("Training time: " + " " + member.getTrainingTime());



    }
}}}

    public boolean ActiveComparator() {
        activeComparator comparator = new activeComparator();
        Collections.sort(members, comparator);

        for (Member member : members) {
            if (member.getIsActive())
                return true;
            System.out.println(member.getName() + " = " + member.getIsActive());
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

    public void trainingTimeComparator() {
        TrainingTimeComparator trainingTimeComparator = new TrainingTimeComparator();
        Collections.sort(members, trainingTimeComparator);

        for (Member member : members) {
            System.out.println(member.getName() + " = " + member.getTrainingTime());
        }
    }




    public void SearchSwimmer(){
        System.out.println("Write the name of the Swimmer");
        String userInput = scanner.nextLine();
        ArrayList<Member> searchResult = getMembers();

        if(searchResult.isEmpty()) {
            System.out.println("There is no swimmer with that name in the club");
        }else if(searchResult.size() > 1){

            int counter = 0;
            for (Member member : searchResult){
            if(member.getName().startsWith(userInput)){
                System.out.println("" + counter++ + "\n" +
                member.getName() + "\n" +
                member.getAge() + "\nBorn " +
                member.getBirthday() + "\nThe swimmer lives in " +
                member.getAddress() + "\nThe swimmer is either active or non-active based on (true/false) \n" +
                member.getIsActive() + "\nThe selected swimmer is a " +
                member.getSwimType() + "\n"

                );
            }

            }
}

}

    public CompetitorMember findSwimmerByName(String swimmerName) {
        for (Member member : members) {
            if (member instanceof CompetitorMember && member.getName().equalsIgnoreCase(swimmerName)) {
                return (CompetitorMember) member;
            }
        }
        return null;
    }


    public Result getBestTrainingResultForSwimmer(String swimmerName) {
        CompetitorMember swimmer = findSwimmerByName(swimmerName);
        if (swimmer != null) {
            return swimmer.getBestTrainingResult();
        }
        return null;
    }

    private void handleBestTrainingResult() {
        System.out.println("Enter the name of the swimmer:");
        String swimmerName = scanner.nextLine();
        Result bestResult = getBestTrainingResultForSwimmer(swimmerName);
        if (bestResult != null) {
            System.out.println("Best Training Result for " + swimmerName + ":");
            System.out.println("Date: " + bestResult.getDate());
            System.out.println("Discipline: " + bestResult.getSwimmingDiscipline());
            // Add more details if needed
        } else {
            System.out.println("No training results available for " + swimmerName + ".");
        }
    }

    public void trainingTimeForTopFive(){
        System.out.println("Top 5 swimmers");
        for (Member member : members) {
            if (member instanceof CompetitorMember) {
                CompetitorMember competitorMember = (CompetitorMember) member;
                System.out.println("Swimmer: " + competitorMember.getName());
                Result bestResult = competitorMember.getBestTrainingResult();
                if (bestResult != null) {
                    System.out.println("Best Training Result: " +
                            bestResult.getTime + " seconds on " + bestResult.getDate());
                } else {
                    System.out.println("No training results available.");
                }
                System.out.println();
            }
        }
    }

    public void displayAllTrainingTimes() {
        for (Member member : members) {
            if (member instanceof CompetitorMember) {
                CompetitorMember competitorMember = (CompetitorMember) member;
                System.out.println("Swimmer: " + competitorMember.getName());
                Result bestResult = competitorMember.getBestTrainingResult();
                if (bestResult != null) {
                    System.out.println("Best Training Result: " +
                            bestResult.getTime + " seconds on " + bestResult.getDate());
                } else {
                    System.out.println("No training results available.");
                }
                System.out.println();
            }
        }
    }
    public void sortedOptionsForCoach() {
        int categorized = scanner.nextInt();
        scanner.nextLine();

        switch (categorized) {
            case 1:
                ActiveComparator();
                break;
            case 2:
                NameComparator();
                break;
            case 3:
                GradeComparator();
                break;
            case 4:
                SwimTypeComparator();
                break;
            case 5:
                SearchSwimmer();
                break;
            case 6:
                trainingTimeComparator();
                break;
            case 7:
                trainingTimeForEachSwimmer();
                break;
            case 8:
                handleBestTrainingResult();
                break;


            default:
                System.out.println("Unable to understand your command.");
        }
    }

    public void sortedOptionsForChairman() {
        int categorized = scanner.nextInt();
        scanner.nextLine();
        switch (categorized) {
            case 1 -> ShowAllMembers();  //show all members
            case 2 -> NameComparator();// sorted by name
            case 3 -> ActiveComparator(); // sorted..
            case 4 -> GradeComparator(); // sorted by grade
            case 5 -> SwimTypeComparator();// sorted by swimtype


            default -> System.out.println("Unable to understand your command");
        }
    }

}