package com.awesome;

import com.awesome.Admin.AdminMainMenu;
import com.awesome.Doctor.Doctor;
import com.awesome.Doctor.MainMenuDoctor;
import com.awesome.Patient.Diary;
import com.awesome.Patient.Patient;
import com.awesome.Patient.PatientMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Patient patient = new Patient();
        Doctor doctor = new Doctor();

        boolean MainMenu = true;

        ArrayList<Diary> patienDiary = new ArrayList<>();
        String patientXmlPath = "src/com/awesome/Patient/patientDiary.xml";

        System.out.println("WELCOME TO AWESOME-HEALTH");
        String patientUserName = "patient";
        String patientUserPassword = "patient";

        String doctorUserName = "doctor";
        String doctorUserPassword = "doctor";

        String adminUserName = "admin";
        String adminUserPassword = "admin";
        System.out.println("-------------------------------");
        System.out.println(" WELCOME TO AWESOME-HEALTH APP");
        System.out.println("-------------------------------\t\t");
        System.out.println("Please log in to continue");

        while (MainMenu) {
            String logInUserName;
            String logInUserPassword;

            System.out.println("Username: ");
            logInUserName = scan.nextLine();
            System.out.println("Password: ");
            logInUserPassword = scan.nextLine();

            if (logInUserName.equals(patientUserName) || logInUserPassword.equals(patientUserPassword)) {
                PatientMenu.Main_menu();
            } else if (logInUserName.equals(doctorUserName) || logInUserPassword.equals(doctorUserPassword)) {
                MainMenuDoctor.Main_menu();
            } else if (logInUserName.equals(adminUserName) || logInUserPassword.equals(adminUserPassword)) {
                AdminMainMenu.MainMenu();
            } else {
                System.out.println("UserName or Password incorrect!");
            }

            System.out.println("Do you want to Exit?\n 1 = Exit, 2 = Go to log-in page");
            int exit;
            exit = scan.nextInt();

            if (exit == 1) {
                MainMenu = false;
            } else if (exit == 2) {
                MainMenu = true;
                exit = 0;
            }
            exit = 0;

        }

    }
}
