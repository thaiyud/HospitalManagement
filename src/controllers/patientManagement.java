/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.nursesData;
import data.patientsData;
import data.taskData;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import models.Nurse;
import models.Patient;
import models.Tasks;

/**
 *
 * @author HP VICTUS
 */
public class patientManagement {

    Patient pa;
    Tasks ta;

    patientsData pati;
    taskData tada;
    nurseManagement nu;
    Utinities le;
    Scanner sc;
    HashMap<String, Nurse> nusMap;
    HashMap<String, Tasks> taskMap;
    HashMap<String, Patient> patiMap;
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public patientManagement() throws IOException {
        ta = new Tasks();
        pa = new Patient();
        nu = new nurseManagement();
        le = new Utinities();
        sc = new Scanner(System.in);
        tada = new taskData();
        pati = new patientsData();
        taskMap = tada.loadFromFile();
        patiMap = pati.loadFromFile();

    }

    public void addPatient() throws IOException {
        String id = le.checkValid("Enter Patient ID(PXXXX): ", "P\\d{4}", "invalid ID!!!");
        if (le.checkIdPatient(id)) {
            System.err.println("The ID was existed!!");
            id = le.checkValid("Enter again please.\nEnter Patient ID (PXXXX): ", "P\\d{4}", "invalid ID!!!");
        }
        pa.setId(id);                                                                                               //set ID
        Input();

        Patient patient = new Patient(pa.getId(), pa.getName(), pa.getAge(), pa.getGender(), pa.getAddress(), pa.getPhone(),
                pa.getDiagnosis(), pa.getAdmissionDate(), pa.getDischargeDate(), pa.getNurseAssigned1(), pa.getNurseAssigned2());

        patiMap.put(pa.getId(), patient);


    }

    public void listPatients() {
        if (patiMap.isEmpty()) {
            System.err.println("There is no patient now!!!");
        } else {
            LocalDate startDate = le.inputDate("Enter Start Date (dd/MM/yyyy): ");
            LocalDate endDate = le.inputDate("Enter End Date (dd/MM/yyyy): ");

            while (endDate.isBefore(startDate)) {
                System.err.println("End date cannot be earlier than Start date. Please try again.");
                endDate = le.inputDate("Enter End Date (dd/MM/yyyy): ");
            }

            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("|  No. | Patient Id   |   Admission Date | Full Name          |         Phone | Diagnosis      |");
            System.out.println("------------------------------------------------------------------------------------------------");

            int i = 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Patient patient : patiMap.values()) {
                LocalDate admissionDate = LocalDate.parse(patient.getAdmissionDate(), formatter);

                if (admissionDate.isEqual(startDate) || (admissionDate.isAfter(startDate) && admissionDate.isBefore(endDate) || admissionDate.isEqual(endDate))) {
                    System.out.printf("|%5s | %-13S|%17s | %-19s|%14s | %15s|\n",
                            i, patient.getId(), admissionDate, patient.getName(), patient.getPhone(), patient.getDiagnosis());
                    System.out.println("------------------------------------------------------------------------------------------------");

                    i += 1;
                }
            }
        }

    }

    public void sortPatients() {
        System.out.print("Sorted by: ");
        String field = le.checkNull();
        while (!field.equalsIgnoreCase("id") && !field.equalsIgnoreCase("name")) {
            System.err.print("id' or 'name' only!!\nPlease enter again: ");
            field = le.checkNull();
        }
        System.out.print("Sorted Order: ");
        String sortOrder = le.checkNull();
        while (!sortOrder.equalsIgnoreCase("asc") && !sortOrder.equalsIgnoreCase("desc")) {
            System.err.print("'ASC' or 'DESC' only!!\nPlease enter again");
            sortOrder = le.checkNull();
        }

        le.sortPatients(field, sortOrder);

    }

    public void Input() {

        String gender = "";
        String phone = "";

        System.out.print("Enter name: ");
        String name = le.checkNull();
        pa.setName(name);                                                                  //set name

        String age = le.checkValid("Enter age: ", "\\d+", "invalid age!!!");
        pa.setAge(age);                                                                   // set age  

        while (!gender.toLowerCase().equals("male") && !gender.toLowerCase().equals("female")) {
            System.out.print("Enter gender(male or female): ");
            gender = sc.nextLine();
        }
        pa.setGender(gender);                                                            //set gender

        System.out.print("Enter address: ");
        String address = le.checkNull();
        pa.setAddress(address);                                                           //set address

        do {
            System.out.print("Enter Phone Number: ");
            phone = le.checkNull();
            if (!phone.matches("^\\d{10}$")) {
                System.err.println("Enter the correct format 10 digits!!!");
            }
        } while (!phone.matches("^\\d{10}$"));
        pa.setPhone(phone);                                                             // set phone            

        System.out.print("Enter diagnosis: ");
        String diagnosis = le.checkNull();
        pa.setDiagnosis(diagnosis);                                                     // set diagnosis

        checkDate();                                                                   // set date

        String nurse1 = nu.checkNurseAvvv("Enter first nurseID(NXXXX): ", taskMap);
        pa.setNurseAssigned1(nurse1);
        Tasks line = taskMap.get(nurse1);

        if (line.getIdPatient1().equals("")) {
            line.setIdPatient1(pa.getId());
            taskMap.put(nurse1, line);
        } else {
            line.setIdPatient2(pa.getId());
            taskMap.put(nurse1, line);
        }

        String nurse2 = nu.checkNurseAvvv("Enter second nurseID(NXXXX): ",taskMap);
        pa.setNurseAssigned2(nurse2);
        Tasks line2 = taskMap.get(nurse2);

        if (line2.getIdPatient1().equals("")) {
            line2.setIdPatient1(pa.getId());
            taskMap.put(nurse2, line2);

        } else {
            line2.setIdPatient2(pa.getId());
            taskMap.put(nurse2, line2);                                                          // set nurse assigned && set patient in task 

        }

    }

    public void checkDate() {

        LocalDate admissionDate = le.inputDate("Enter admission date (dd/MM/yyyy): ");
        LocalDate dischargeDate = le.inputDate("Enter discharge date (dd/MM/yyyy): ");

        while (dischargeDate.isBefore(admissionDate)) {
            System.err.println("Discharge date cannot be earlier than admission date. Please try again.");
            dischargeDate = le.inputDate("Enter discharge date (dd/MM/yyyy): ");
        }

        pa.setAdmissionDate(admissionDate.format(DATE_FORMATTER));
        pa.setDischargeDate(dischargeDate.format(DATE_FORMATTER));

    }

    public void saveData() throws IOException{
        le.saveToFilePatient(patiMap,taskMap);
       
    }
           
}
