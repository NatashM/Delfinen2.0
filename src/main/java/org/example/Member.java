package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {
    private String name;
    private int age;
    private String birthDay;
    private String address;
    private boolean isActive;
    private String grade;
    private String swimType;
    private String trainingTime;
    private LocalDate lastPaymentDate;
    private double membershipFee;
    private boolean paid;



    public Member(String name, int age, String birthDay, String address, boolean isActive, String grade, String swimType,String trainingTime,LocalDate lastPaymentDate, double membershipFee, boolean paid) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
        this.address = address;
        this.isActive = isActive;
        this.grade = grade;
        this.swimType = swimType;
        this.trainingTime = trainingTime;
        this.membershipFee = membershipFee;
        this.lastPaymentDate = lastPaymentDate;
        this.paid = false;

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

    public String getTrainingTime(){
        return trainingTime;
    }
    public LocalDate getLastPaymentDate(){
        return lastPaymentDate;
    }

    public double getMembershipFee() {
        return membershipFee;
    }
    public boolean hasPaid(){
        return paid;
    }
public void setPaid(boolean paid){
        this.paid = paid;
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

    public double calculateExpectedDuesPayment(double membershipFee) {
        LocalDate currentDate = LocalDate.now();

        if (lastPaymentDate == null || lastPaymentDate.isAfter(currentDate)) {
            return membershipFee;  // Assuming a full monthly fee for the first payment
        }
        int monthsSinceLastPayment = (int) ChronoUnit.MONTHS.between(lastPaymentDate, currentDate);
        return monthsSinceLastPayment * membershipFee;
    }
    @Override
    public String toString() {
        return "\n" + "Members:" +
                " Name = " + name +
                ", Age = " + age +
                ", Birthday = " + birthDay +
                ", Address = " + address + " " +
                ", Membership = " + isActive +
                ", Swim discipline = " + swimType +
                ", Team category = " + grade +
                ", Training time =" + trainingTime + '\'' +
                ", lastPaymentDay=" + lastPaymentDate +
                ", membershipFee=" + membershipFee +
                ", paid=" + paid +
                '}';
    }



    }

