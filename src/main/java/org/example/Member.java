package org.example;

public class Member {
    private String name;
    private int age;
    private String birthDay;
    private String address;
    private boolean isActive;
    private String grade;
    private String swimType;





    public Member(String name, int age, String birthDay, String address, boolean isActive, String grade, String swimType) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
        this.address = address;
        this.isActive = isActive;
        this.grade = grade;
        this.swimType = swimType;


    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBirthday() {
        return birthDay;
    }


    public String getAddress() {
        return address;
    }

    public boolean getIsActive(){
        return isActive;
    }

    public String getGrade(){
        return grade;
    }

    public String getSwimType(){
        return swimType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthDay( String birthday) {
        this.birthDay = birthday;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "\n" + "Members:" +
                " Name = " + name +
                ", Age = " + age +
                ", Birthday= " + birthDay +
                ", Address= " + address + " ";


    }
}
