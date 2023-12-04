package org.example;

public class Controller {
    private Database database = new Database();


    public void createMembers(){
       database.CreateMembers();
    }

    public boolean removeMembers(String name){
       return database.removeMembers(name);
    }

    public void getMembers(){
        database.getMembers();
    }

    public void ShowListOfMember(){
        database.ShowAllMembers();

    }
    public void SortedOptionsForCoach (){
        database.sortedOptionsForCoach();
    }


    public void nameComparator(){
        database.NameComparator();
    }

    public void sortedOptionsForChairman(){
        database.sortedOptionsForChairman();
    }
    public void searchSwimmer(){
        database.SearchSwimmer();
    }
    public Result getBestTrainingResultForSwimmer(String swimmerName) {
        return database.getBestTrainingResultForSwimmer(swimmerName);
    }

    public void displayAllTrainingTimes() {
        database.displayAllTrainingTimes();

    }
    public void trainingTimefortopFiveSenior(){
        database.trainingTimeForTopFiveSenior();
    }
    public void trainingTimesforTopFiveJunior(){
        database.trainingTimeForTopFiveJunior();
    }

}



