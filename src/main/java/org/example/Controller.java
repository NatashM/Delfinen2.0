package org.example;

public class Controller {
    private Database database = new Database();


    public void createMembers(){
       database.createMembers();
    }

    public boolean removeMembers(String name){
       return database.removeMembers(name);
    }

    public void getMembers(){
        database.getMembers();
    }

    public void ShowListOfMember(){
        database.NameComparator();
    }
    public void SortedOptions (){
        database.sortedOptions();
    }




}
