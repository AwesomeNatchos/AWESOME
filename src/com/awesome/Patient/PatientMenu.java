package com.awesome.Patient;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.awesome.Patient.Diary.*;

public class PatientMenu {

    private static Scanner scan = new Scanner(System.in);

    public static void Main_menu() {

        String patientXmlPath = "src/com/awesome/Patient/patientDiary.xml";
        ArrayList<Diary> patienDiary = new ArrayList<>();
        Diary diary;
        patienDiary = Diary.readPatientDiary(patientXmlPath);

        boolean patientMainMenu = true;
        int choice = -1;
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
                    diary = writeDiary();
                    patienDiary.add(diary);
                    System.out.println("You have now written the diary but and added to the diary list");
                    System.out.println(patienDiary.size());
                    saveDiaryToXml(patienDiary,"src/com/awesome/Patient/patientDiary.xml");
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

