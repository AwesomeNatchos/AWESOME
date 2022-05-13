package com.awesome.Admin;

import com.awesome.Doctor.Doctor;
import com.awesome.Doctor.XmlDoctor;
import com.awesome.Patient.ModifyPatient;
import com.awesome.Patient.Patient;
import com.awesome.Patient.XmlPatient;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminMainMenu {


        private static Scanner scan = new Scanner(System.in);

        public static void MainMenu() {

            //Patients
            String xmlPatientpath = "src/com/awesome/Patient/patient.xml";
            ArrayList<Patient> allPatiens = new ArrayList<>();
            allPatiens = XmlPatient.readPatientXml("src/com/awesome/Patient/patient.xml");

            //Doctor
            String xmlDoctorPath = "src/com/awesome/Doctor/doctor.xml";
            ArrayList<Doctor> allDoctors = new ArrayList<>();
            allDoctors = XmlDoctor.readDoctortXml(xmlDoctorPath);

            boolean mainMenu = true;
            int choice = -1;
            System.out.println("--------------------------");
            System.out.println("WELCOME AWESOME-ADMIN");
            System.out.println("--------------------------\n\n");
            while(mainMenu){
                System.out.println("        MAIN MENU       \n");
                System.out.println("1) See Patients list ");
                System.out.println("2) Add patient ");
                System.out.println("3) Edit patient ");
                System.out.println("4) Delete patient");
                System.out.println();
                System.out.println("5) Add Doctor");
                System.out.println("6) Read Doctors");
                System.out.println("7) Edit Doctor ");
                System.out.println("8) Delete Doctor ");

                System.out.println("0) Exit");
                System.out.println("Please enter your option: ");

                try{
                    choice = scan.nextInt();
                    scan.nextLine();
                    if(choice < 0 || choice > 7){
                        System.out.println("Invalid option! \nPlease try again");
                    }

                } catch (InputMismatchException e){
                    System.out.println("Not a valid option");
                    scan.nextLine();  //should it be int? should I save it to choice?
                }

                switch(choice){
                    case 1:
                        System.out.println("See patient list");
                        XmlPatient.readPatientXml(xmlPatientpath);    //can get a array list of patients back
                        break;
                    case 2:
                        System.out.println("Add patient ");
                        allPatiens.add(Patient.creat_new_Patient());
                        System.out.println("You have now successfully added a new patient");
                        XmlPatient.WriteXmlPatient(allPatiens, xmlPatientpath);
                        break;
                    case 3:
                        System.out.println("Edit patient");
                        ModifyPatient.modifyPatient(allPatiens);
                        XmlPatient.WriteXmlPatient(allPatiens, xmlPatientpath);
                        System.out.println("You have now successfully created a new patient");
                        break;
                    case 4:
                        System.out.println("Delete patient");
                        ModifyPatient.removePatient(allPatiens);
                        XmlPatient.WriteXmlPatient(allPatiens, xmlPatientpath);
                        break;
                    case 5:
                        System.out.println("Add a new Doctor");
                        allDoctors.add(Doctor.creat_new_Doctor());
                        System.out.println("You have successfully created a new Doctor user");
                        XmlDoctor.WriteXmlDoctor(allDoctors, xmlDoctorPath);
                        break;
                    case 6:
                        System.out.println("Read all doctors");
                        XmlDoctor.readDoctortXml(xmlDoctorPath);
                        break;
                    case 7:
                        System.out.println("Edit Doctor");
                        Doctor.modifyDoctor(allDoctors);
                        break;
                    case 8:
                        System.out.println("Delete doctor");
                        Doctor.removeDoctor(allDoctors);
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        mainMenu = false;
                        break;

                }
            }
        }
}

