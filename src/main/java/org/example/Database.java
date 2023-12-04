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



    public Database() {
        try {
            this.members = filehandler.loadAllData();
            splitMembers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void splitMembers() {
        for (Member member : members) {
            if (member.getAge() < 18) {
                junior.add(member);
            } else {
                senior.add(member);
            }
        }
    }
    public void addMember(String name, int age, String birthday, String address, boolean isActive, String grade, String swimType, String trainingTime, LocalDate lastPaymentDate, double membershipFee) {
        Member newMember = new Member(name, age, birthday, address, isActive, grade, swimType, trainingTime, lastPaymentDate, membershipFee, false);

        try {
            members.add(newMember);
            filehandler.saveMembers(members, file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
            System.out.println("\nWrite your full name:");
            String name = scanner.next();

            System.out.println("\n write your birthday");
            String birthday = scanner.next();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
            LocalDate dateNow =  LocalDate.now();

            LocalDate date = LocalDate.parse(birthday, formatter);
            int age = dateNow.getYear() - date.getYear();


                double fee = (age < 18) ? calculateMembershipFee(date) : 0.0;
                boolean isActive;

                String grade = "";

                if (age < 18) {
                    System.out.println("You will be joining the junior team");
                    grade = "junior";
                } else {
                    System.out.println("You will be joining the senior team");
                    grade = "senior";
                }

                System.out.println("\nWrite your address:");
                String address = scanner.next();

                do {
                    System.out.print("Do you want an active membership (true/false): ");
                    if (scanner.hasNextBoolean()) {
                        isActive = scanner.nextBoolean();
                        break;
                    } else {
                        System.out.println("An error occurred:/ \n Please write true or false ");
                        scanner.nextLine();
                    }
                } while (true);

                System.out.println("Your desired swim discipline");
                String swimType = scanner.next();

                System.out.println("Write your training time");
                String trainingTime = scanner.next();

                System.out.println("Enter the last payment day (yyyy-MM-dd): ");
                String lastPaymentDayString = scanner.nextLine();

                LocalDate lastPaymentDay = LocalDate.parse(lastPaymentDayString);

                System.out.println("Enter the membership fee: ");
                String membershipFeeString = scanner.next();

                double membershipFee;
                try {
                    membershipFee = Double.parseDouble(membershipFeeString);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for membership fee.");
                    membershipFee = 0.0;
                }

                Member member = new Member(name, age, birthday, address, isActive, grade, swimType, trainingTime, lastPaymentDay, membershipFee, false);

                System.out.println(member);
            }



            public double MembershipFee(Member member) {
                int age = member.getAge();
                boolean isActive = member.getIsActive();
                double base = 0;

                if (isActive) {
                    if (age < 18) {
                        base = 1000;
                    } else if (age >= 18 && age <= 60) {
                        base = 1600;
                    } else if (age > 60) {
                        base = (1600 / 4) * 3;
                    }
                } else {
                    base = 500;
                }
                return base;
            }

            public void NameComparator() {
                NameComparator comparison = new NameComparator();
                Collections.sort(members, comparison);
                for (Member member : members) {
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


            public void displayDuesPayments(double membershipFee) {
                System.out.println("Membership Fee Payments:");

                for (Member member : getMembers()) {
                    double duesPayment = member.calculateExpectedDuesPayment(membershipFee);
                    System.out.println(member.getName() + ": " + duesPayment);
                }
            }


            public void identifyMembersInArrears(double membershipFee) {
                System.out.println("Members in Arrears:");

                for (Member member : members) {
                    double duesPayment = member.calculateExpectedDuesPayment(membershipFee);

                    if (duesPayment > 0) {
                        System.out.println(member.getName() + ": Expected Dues Payment - " + duesPayment);
                    }
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
                    }
                }
            }

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




            public void SearchSwimmer() {
                System.out.println("Write the name of the Swimmer");
                String userInput = scanner.nextLine();
                ArrayList<Member> searchResult = getMembers();

                if (searchResult.isEmpty()) {
                    System.out.println("There is no swimmer with that name in the club");
                } else if (searchResult.size() > 1) {

                    int counter = 0;
                    for (Member member : searchResult) {
                        if (member.getName().startsWith(userInput)) {
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

                } else {
                    System.out.println("No training results available for " + swimmerName + ".");
                }
            }

            public void trainingTimeForTopFiveSenior() {
                System.out.println("Top 5 swimmers on the senior team");

                // Sorting senior team members by training time
                senior.sort(new TrainingTimeComparator());

                // Top 5 swimmers
                for (int i = 0; i < Math.min(5, senior.size()); i++) {
                    Member member = senior.get(i);
                    System.out.println("Swimmer: " + member.getName());
                    String trainingTime = member.getTrainingTime();
                    if (trainingTime != null && !trainingTime.isEmpty()) {
                        System.out.println("Best Training Time: " + trainingTime + " seconds");
                    } else {
                        System.out.println("No training results available.");
                    }
                    System.out.println();
                }
            }

            public void trainingTimeForTopFiveJunior() {
                System.out.println("Top 5 swimmers on the junior team");

                // sorting junior team members by training time
                junior.sort(new TrainingTimeComparator());

                // Top 5 swimmers
                for (int i = 0; i < Math.min(5, junior.size()); i++) {
                    Member member = junior.get(i);
                    System.out.println("Swimmer: " + member.getName());
                    String trainingTime = member.getTrainingTime();
                    if (trainingTime != null && !trainingTime.isEmpty()) {
                        System.out.println("Best Training Time: " + trainingTime + " seconds");
                    } else {
                        System.out.println("No training results available.");
                    }
                    System.out.println();
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
                    case 1 -> ActiveComparator();
                    case 2 -> NameComparator();
                    case 3 -> GradeComparator();
                    case 4 -> SwimTypeComparator();
                    case 5 -> SearchSwimmer();
                    case 6 -> trainingTimeComparator();
                    case 7 -> trainingTimeForEachSwimmer();
                    case 8 -> handleBestTrainingResult();
                    case 9 -> trainingTimeForTopFiveJunior();
                    case 10 -> trainingTimeForTopFiveSenior();
                    default -> System.out.println("Unable to understand your command.");
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
                    case 6 -> {
                        System.out.println("Enter the name of the member to be removed: ");
                        String memberNameToRemove = scanner.nextLine();
                        removeMembers(memberNameToRemove);
                    }


                    default -> System.out.println("Unable to understand your command");
                }
            }

   /*
   public void sortedOptionsForAccountant() {
    int categorized = scanner.nextInt();
    scanner.nextLine();
    switch(categorized){
        case 1 ->
        break;
        case 2 ->
        break;
        default -> {
        System.out.println("unable to understand your command");
    }*/
        }

