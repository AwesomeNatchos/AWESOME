package com.awesome.Doctor;

import com.awesome.Patient.Diary;
import com.awesome.Patient.XmlPatient;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.awesome.Patient.Diary.*;

public class MainMenuDoctor {

    private static Scanner scan = new Scanner(System.in);

    public static void Main_menu() {
        ArrayList<PatientReport> allReports = new ArrayList<>();
        boolean doctorMainMenu = true;
        int choice = -1;

        ArrayList<Diary> patienDiary = new ArrayList<>();
        String patientXmlPath = "src/com/awesome/Patient/patientDiary.xml";

        System.out.println("--------------------------");
        System.out.println("WELCOME AWESOME-DOCTOR");
        System.out.println("--------------------------\n\n");

        while (doctorMainMenu) {
            System.out.println("        MAIN MENU       \n");
            System.out.println("1) See all Patients ");
            System.out.println("2) Read Patient Diary ");
            System.out.println("3) Write patient Medical report");
            System.out.println();
            System.out.println("0) Exit");
            System.out.println("Please enter your option: ");
            try {
                choice = scan.nextInt();
                scan.nextLine();
                if (choice < 0 || choice > 3) {
                    System.out.println("Invalid option! \nPlease try again");
                }

            } catch (InputMismatchException e) {
                System.out.println("Not a valid option");
                scan.nextLine();
            }

            switch (choice) {
                case 1:
                    System.out.println("\tSee Patient list");
                    XmlPatient.readPatientXml("src/com/awesome/Patient/patient.xml");
                    break;
                case 2:
                    System.out.println("Read Patient Diary");
                    readPatientDiary("src/com/awesome/Patient/patientDiary.xml");
                    break;
                case 3:
                    System.out.println("Write Patient medical report");
                    allReports.add(PatientReport.writeMedicalReport());
                    PatientReport.saveMedicalReportToXml(allReports,"src/com/awesome/Doctor/medicalReport.xml");
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    doctorMainMenu = false;
                    break;
            }

            System.out.println("");

        }
    }

}

