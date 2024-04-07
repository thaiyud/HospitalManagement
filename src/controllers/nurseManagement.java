/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import models.Nurse;
import data.nursesData;
import data.taskData;
import java.util.HashMap;
import java.util.Map;
import models.Tasks;

/**
 *
 * @author HP VICTUS
 */
public class nurseManagement {

    Utinities le;
    Scanner sc;
    Nurse nu;
    Tasks ta;
    nursesData da;
    taskData tada;
    HashMap<String, Nurse> nurseMap;
    HashMap<String, Tasks> taskMap;

    public nurseManagement() throws IOException {
        le = new Utinities();
        sc = new Scanner(System.in);
        nu = new Nurse();
        ta = new Tasks();
        da = new nursesData();

        tada = new taskData();
        nurseMap = da.loadFromFile();
        taskMap = tada.loadFromFile();

    }

    public void createNurse() throws IOException {
        String id = le.checkValid("Enter nurse ID(NXXXX): ", "N\\d{4}", "invalid ID!!!");
        if (le.checkId(id,nurseMap)) {
            System.out.println("The ID was existed!!");
            id = le.checkValid("Enter again please.\nEnter nurse ID (NXXXX): ", "N\\d{4}", "invalid ID!!!");
        }
        nu.setId(id);
        ta.setIdNurse(id);
        // set id
        Input(); // input the needed values

        Nurse nurse = new Nurse(nu.getId(), nu.getName(), nu.getAge(), nu.getGender(), nu.getAddress(),
                nu.getPhone(), nu.getDepartment(), nu.getShift(), nu.getSalary());
        Tasks task = new Tasks(ta.getIdNurse(), ta.getIdPatient1(), ta.getIdPatient2());

        nurseMap.put(nu.getId(), nurse);
        taskMap.put(ta.getIdNurse(), task);

       da.saveToFile(nurseMap);
//        le.saveToFileTask(taskMap);

        String check = le.doAgain();
        if (check.equals("1") || check.equals("t") || check.equals("y")) {
            createNurse();

        }

    }

    public void findNurse() {
        System.out.print("what name are you loking for? :");
        String name = sc.nextLine();
        le.findNurseByName(name, nurseMap);

    }

    public void updateNurse() throws IOException {

        checkInputId();
        Input();

        Nurse nurse = new Nurse(nu.getId(), nu.getName(), nu.getAge(), nu.getGender(), nu.getAddress(), nu.getPhone(),
                nu.getDepartment(), nu.getShift(), nu.getSalary());

        nurseMap.put(nu.getId(), nurse);
      //  le.saveToFileNurse(nurseMap);

        System.out.println("Update successfully!!!");
    }

    public void deleteNurse() throws IOException {
        String id = checkInputId();
        if (checkTask(id)) {

            String check = le.inputSaveData("Do you want to delete this nurse [1/0-Y/N-T/F]: ");

            if (check.equals("1") || check.equals("t") || check.equals("y")) {

                nurseMap.remove(id);                                        // Remove the entry associated with the staffId
                taskMap.remove(id);
                System.out.println("Nurse with staffId " + id + " deleted.");
          
            } else {
                System.out.println("cancel deleting");
            }
        }
        else{
            System.out.println("The nurse is taking care for patients!!!");
        }
    }
   public boolean checkTask(String idNurse) {
        Tasks tasks = taskMap.get(idNurse);
        if (tasks != null && !tasks.getIdPatient1().isEmpty() && !tasks.getIdPatient2().isEmpty()) {
            return false;
        }
        return true;
    }

    public String checkInputId() {
        String id = le.checkValid("Enter nurse ID(NXXXX): ", "N\\d{4}", "invalid ID!!!");
        while (!le.checkId(id, nurseMap)) {
            System.err.println("The nurse does not exist!!!");
            id = le.checkValid("Enter again please.\nEnter nurse ID (NXXXX): ", "N\\d{4}", "invalid ID!!!");
        }
        return id;

    }

    public void Input() {

        String gender = "";
        String phone = "";

        System.out.print("Enter name: ");
        String name = le.checkNull();
        nu.setName(name);                                                       //set name

        String age = le.checkValid("Enter age: ", "\\d+", "invalid age!!!");
        nu.setAge(age);

        while (!gender.toLowerCase().equals("male") && !gender.toLowerCase().equals("female")) {
            System.out.print("Enter gender(male or female): ");
            gender = le.checkNull();
        }
        nu.setGender(gender);                                                   //set gender

        System.out.print("Enter address: ");
        String address = le.checkNull();
        nu.setAddress(address);                                                 //set address

        do {
            System.out.print("Enter Phone Number: ");
            phone = le.checkNull();
            if (!phone.matches("^\\d{10}$")) {
                System.out.println("Enter the correct format 10 digits!!!");
            }
        } while (!phone.matches("^\\d{10}$"));
        nu.setPhone(phone);                                                    // set phone            

        String department = le.checkValid("Enter department: ", ".{3,50}", "please enter again!!!!");
        nu.setDepartment(department);                                            // set department

        System.out.print("Enter shilf: ");
        String shilf =  le.checkNull();
        nu.setShift(shilf);                                                     // set shilf

        String salaryString = le.checkValid("Enter salary: ", "^(?=.*[1-9])\\d*(?:\\.\\d+)?$", "please enter again!!!");
        float salary = Float.parseFloat(salaryString);
        nu.setSalary(salary);                                                   // set salary

    }
    public void saveData() throws IOException{
            le.saveToFileNurse(nurseMap,taskMap);
    }
    
    public String checkNurseAvvv(String output,HashMap<String, Tasks> taskMap) {
        String nurse = le.checkValid(output, "N\\d{4}", "Invalid ID!!!");

        while ((!le.checkId(nurse, nurseMap) || !checkNurseAvai(nurse, taskMap))) {
            System.err.println("Invalid or unavailable nurse!!!");
            nurse = le.checkValid("Enter nurse ID (NXXXX): ", "N\\d{4}", "Invalid ID!!!");

        }

        return nurse;
    }

    public boolean checkNurseAvai(String nurse, HashMap<String, Tasks> taskMap) {
        boolean check = false;
        for (Map.Entry<String, Tasks> entry : taskMap.entrySet()) {
            if (nurse.equals(entry.getKey())) {
                if (entry.getValue().getIdPatient1().equals("") || entry.getValue().getIdPatient2().equals("")) {
                    check = true;
                    return check;
                }
                return check;
            }
        }
        return check;
    }

}
