package com.awesome.Patient;

import com.awesome.Sources.BloodType;
import com.awesome.Users;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Patient extends Users {
    String address;
    int phoneNumber;
    //BloodType bloodType;
    static boolean sex; //true = female;

    public static Scanner scan = new Scanner(System.in);

    //Main Constructor
    public Patient(String firstName, String lastName, String address, int age, int phoneNumber, boolean sex) {
        super(firstName, lastName, age);
        this.address = address;
        //this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public static Patient creat_new_Patient(){

        String firstName, lastName, address, userBloodType;
        int age, phoneNumber;
        Boolean sex = null;
        BloodType bloodType = BloodType.A;

        System.out.print("First name: ");
        firstName = scan.nextLine();
        System.out.print("Last/Family name: ");
        lastName = scan.nextLine();
        System.out.print("Address: ");
        address = scan.nextLine();
        System.out.print("Patient age: ");
        age = scan.nextInt();
        System.out.print("Phone number (can not start with 0): ");
        phoneNumber = scan.nextInt();
        //System.out.println("Patient blood type: \n(A,B,O,AB)");
        //userBloodType = scan.nextLine();

        //bloodType = bloodType.set_bloodType(userBloodType);
        //bloodType = getBloodType_withInput(userBloodType);
        System.out.println("Patients sex; 1(female) 2)male");
        int userSex = scan.nextInt();
        sex = get_Sex(userSex);

        if(userSex == 1){
            sex = true;
        }
        else if(userSex == 2){
            sex = false;
        }

        Patient patient = new Patient(firstName, lastName, address, age , phoneNumber, sex);
        return patient;

    }

    public Patient(){
        this("Zimone", "Rolex", "Horwath street 5", 45, 77755522, true);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public boolean getSex() {
        return sex;
    }

    public static Boolean get_Sex(int female1){
        if(female1 == 1){
            sex = true;
        }
        else if ( female1 == 2){
            sex = false;
        }
        return  sex;
    }
    public void set_Sex(boolean sex) {
        this.sex = sex;
    }

}
