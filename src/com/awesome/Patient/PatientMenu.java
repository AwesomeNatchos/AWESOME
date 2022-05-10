package com.awesome.Patient;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.awesome.Patient.Diary.*;

public class PatientMenu {

    private static Scanner scan = new Scanner(System.in);

    public static void Main_menu() {
        boolean patientMainMenu = true;
        int choice = -1;

        ArrayList<Diary> patienDiary = new ArrayList<>();
        String patientXmlPath = "src/com/awesome/Patient/patientDiary.xml";

        System.out.println("--------------------------");
        System.out.println("WELCOME AWESOME-PATIENT");
        System.out.println("--------------------------\n\n");
        while (patientMainMenu) {
            System.out.println("        MAIN MENU       \n");
            System.out.println("1) Write diary ");
            System.out.println("2) Read Diary ");
            System.out.println();
            System.out.println("0) Exit");
            System.out.println("Please enter your option: ");

            try {
                choice = scan.nextInt();
                scan.nextLine();
                if (choice < 0 || choice > 2) {
                    System.out.println("Invalid option! \nPlease try again");
                }

            } catch (InputMismatchException e) {
                System.out.println("Not a valid option");
                scan.nextLine();
            }

            switch (choice) {
                case 1:
                    System.out.println("\tWrite Diary");
                    patienDiary.add(writeDiary());
                    saveDiaryToXml(patienDiary,patientXmlPath);
                    break;
                case 2:
                    System.out.println("Read Diary");
                    readPatientDiary(patientXmlPath);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    patientMainMenu = false;
                    break;
            }
            System.out.println("");
        }
    }
}

