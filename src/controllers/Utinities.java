/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Nurse;
import data.nursesData;
import data.patientsData;
import data.taskData;
import models.Nurse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import models.Patient;
import models.Tasks;

/**
 *
 * @author HP VICTUS
 */
public class Utinities {

    nursesData nu;
    taskData ta;

    Scanner sc;
    patientsData pada;

    Patient pa;
    HashMap<String, Tasks> taskMap;
    HashMap<String, Nurse> nurseMap;
    HashMap<String, Patient> patiMap;
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Utinities() throws IOException {
        nu = new nursesData();
        ta = new taskData();
        pada = new patientsData();

        pa = new Patient();
        nurseMap = nu.loadFromFile();
        taskMap = ta.loadFromFile();
        patiMap = pada.loadFromFile();

        sc = new Scanner(System.in);

    }

    public String checkNull() {
        String text = "";
        do {
            text = sc.nextLine();
            if (text.equals("")) {
                System.err.print("please enter value :");
            }
        } while (text.equals(""));

        return text;
    }

    public boolean checkId(String id,HashMap<String, Nurse> nurseMap) {
        boolean check = false;
        for (Map.Entry<String, Nurse> entry : nurseMap.entrySet()) {
            if (id.equals(entry.getKey())) {
                check = true;
                return check;
            }
        }
        return check;

    }

    public boolean checkIdPatient(String id) {
        boolean check = false;
        for (Map.Entry<String, Patient> entry : patiMap.entrySet()) {
            if (id.equals(entry.getKey())) {
                check = true;
                return check;
            }
        }
        return check;

    }

    public String checkValid(String enter, String regex, String output) {

        String text = "";
        do {
            System.out.print(enter);
            text = checkNull();
            if (!text.matches(regex)) {
                System.err.println(output);
            }
        } while (!text.matches(regex));
        return text;
    }

    
    //-------------------------------------------------------------------------

    public LocalDate inputDate(String message) {

        String input = checkValid(message, "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$",
                "Invalid format (dd/MM/yyyy) or invalid day, please enter again!!!");
        return LocalDate.parse(input, DATE_FORMATTER);
    }
//=========================================================================================

    public void sortPatients(String field, String sortOrder) {
        Comparator<Patient> comparator;

        if (field.equalsIgnoreCase("id")) {
            comparator = Comparator.comparing(Patient::getId);
        } else if (field.equalsIgnoreCase("name")) {
            comparator = Comparator.comparing(Patient::getName);
        } else {
            System.err.println("Invalid field for sorting patients!");
            return;
        }

        List<Patient> patientList = new ArrayList<>(patiMap.values());

        if (sortOrder.equalsIgnoreCase("asc")) {
            patientList.sort(comparator);
        } else if (sortOrder.equalsIgnoreCase("desc")) {
            patientList.sort(comparator.reversed());
        } else {
            System.err.println("Invalid sort order for sorting patients!");
        }

        if (patiMap.isEmpty()) {
            System.err.println("There is no patient now!!!");
        } else {

            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("|  No. | Patient Id   |   Admission Date | Full Name          |         Phone | Diagnosis      |");
            System.out.println("------------------------------------------------------------------------------------------------");

            int i = 1;

            for (Patient patient : patientList) {

                System.out.printf("|%5s | %-13S|%17s | %-19s|%14s | %15s|\n",
                        i, patient.getId(), patient.getAdmissionDate(), patient.getName(), patient.getPhone(), patient.getDiagnosis());
                System.out.println("------------------------------------------------------------------------------------------------");

                i += 1;
            }

        }

    }

//=========================================================================================
    public void saveToFilePatient(HashMap<String, Patient> patimap, HashMap<String, Tasks> task) throws IOException {

        if (!patimap.equals(patiMap)) {

            pada.saveToFile(patimap);
            ta.saveToFile(task);

        }
    }

    public void saveToFileNurse(HashMap<String, Nurse> hashmap,HashMap<String, Tasks> task) throws IOException {
        if (!hashmap.equals(nurseMap)) {
            nu.saveToFile(hashmap);
            ta.saveToFile(task);

        }
    }


//------------------------------------------------------------------------------------

    public void findNurseByName(String name, HashMap<String, Nurse> hashmap) {
        boolean found = false;

        for (Nurse nurse : hashmap.values()) {
            if (nurse.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(nurse.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("The nurse does not exist.");
        }
    }

    //====================================================================================
    public String doAgain() {
        String str = "";
        do {
            System.out.print("Do you want to add another nurse? [1/0-Y/N-T/F]: ");
            str = sc.nextLine();
            str = str.toLowerCase();
        } while (!(str.equals("1") || str.equals("0") || str.equals("y") || str.equals("n") || str.equals("t") || str.equals("f")));
        return str;

    }
    //----------------------------------------------------------------------------

    public String inputSaveData(String output) { //"Do you want to save data [1/0-Y/N-T/F]: "
        String str = "";
        do {
            System.out.print(output);
            str = sc.nextLine();
            str = str.toLowerCase();
        } while (!(str.equals("1") || str.equals("0") || str.equals("y") || str.equals("n") || str.equals("t") || str.equals("f")));
        return str;
    }

    //-----------------------------------------------------------------------------
    public int getChoice(Object[] options) {
        for (int i = 0; i < options.length; i++) {

            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your options from 1 - " + options.length + ": ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int number = 0;
        if (input.matches("\\d+") && (number = Integer.parseInt(input)) <= options.length) {
            return number;
        } else {
            System.out.println("Invalid input! Please enter again!!!");
            getChoice(options);
        }
        return number;
    }

}
