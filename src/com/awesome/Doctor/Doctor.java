package com.awesome.Doctor;

import com.awesome.Patient.Patient;
import com.awesome.Users;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Doctor extends Users {
    int doctorID;
    String address;
    int phoneNumber;

    private static Scanner scan = new Scanner(System.in);

    public Doctor(String firstName, String lastName, String address, int age, int doctorID, int phoneNumber) {
        super(firstName, lastName, age);
        this.address = address;
        this.doctorID = doctorID;
        this.phoneNumber = phoneNumber;
    }

    public Doctor(){
        this("Gabòr", "Horvath", "Szegeti 12, 7624 Pècs", 35, 123, 4588623);
    }

    public static Doctor creat_new_Doctor(){
        String firstName, lastName, address;
        int age, doctorID, phoneNumber;

        System.out.println("First name: ");
        firstName = scan.nextLine();
        System.out.println("Family/Last name: ");
        lastName = scan.nextLine();
        System.out.println("Address: ");
        address = scan.nextLine();
        System.out.println("Age: ");
        age = scan.nextInt();
        System.out.println("Doctor ID number: ");
        doctorID = scan.nextInt();
        System.out.println("Phone number");
        phoneNumber = scan.nextInt();

        Doctor doctor = new Doctor(firstName,lastName,address,age, doctorID, phoneNumber);

        return doctor;
    }


    //Modify Patient
    public static void modifyDoctor(ArrayList<Doctor> allDoctors){
        Doctor doctor = new Doctor();
        String file = "src/com/awesome/Doctor/doctor.xml";


        doctor = findDoctorIn(allDoctors, file);
        int age = readBirthyear();
        int phoneNumber = readPhoneNumber();
        int doctorID = readDoctorID();
        System.out.println("Please enter address of Doctor");
        String address = scan.nextLine();


        // creat a new Doctor and update it to the user
        allDoctors.set(allDoctors.indexOf(doctor), new Doctor(doctor.getFirstName(),doctor.getLastName(), address, age, phoneNumber, doctorID ));

    }

    private static int readDoctorID(){
        int doctorID = 0;
        while(doctorID == 0){
            try{
                System.out.println("Enter Doctor ID (can not start with 0): ");
                doctorID = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e){
                System.out.println("This is not a valid ID number, please enter an integer");
                scan.nextLine();
            }
        } return doctorID;


    }
    private static int readBirthyear(){
        int age = 0;
        while(age == 0){
            try{
                System.out.println("Enter age of Doctor");
                age = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e){
                System.out.println("This is not a valid age, please enter an integer");
                scan.nextLine();
            }
        } return age;
    }
    private static int readPhoneNumber(){
        int phoneNumber = -1;
        while(phoneNumber == -1){
            try{
                System.out.println("Enter Doctors phone number(can not start with 0): ");
                phoneNumber = scan.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid phone number. \nCan not start with 0");
                scan.nextLine();

            }
        } return phoneNumber;
    }

    private static Doctor findDoctorIn(ArrayList<Doctor>allDoctors, String filepath){
        Doctor doctor = new Doctor();
        String name = "";
        while(name.isEmpty()){
            System.out.println("Enter last name of Doctor: ");
            name = scan.nextLine();
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(new FileInputStream(filepath));

                NodeList root = document.getElementsByTagName("doctor");
                for(int i = 0; i<root.getLength(); i++){
                    Node node = root.item(i);
                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) node; // Here just casting the node into Element, because node does not have

                        String firstname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("first").item(0).getTextContent();
                        String lastname = ((Element) element.getElementsByTagName("name").item(0)).getElementsByTagName("last").item(0).getTextContent();

                        if(firstname.equals(name)){
                            System.out.println("You fount the right doctor");
                        }
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }


        }
        return doctor;

    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
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
}
