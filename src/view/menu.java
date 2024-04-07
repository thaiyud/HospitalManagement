/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controllers.Utinities;
import controllers.nurseManagement;
import controllers.patientManagement;
import java.io.IOException;

/**
 *
 * @author HP VICTUS
 */
public class menu {

    Utinities le;

    nurseManagement nu;
    patientManagement pa;

    public menu() throws IOException {
        le = new Utinities();
        nu = new nurseManagement();
        pa = new patientManagement();
    }

    public void Menu() throws IOException, Exception {
        String[] options = {"Create a nurse",
            "Find the nurse", "Update the nurse",
            "Delete the nurse", "Add a patient",
            "List patients", "Sort the patients list",
            "Save data", "Load data",
            "Quit"};

        int choice = 0;

        do {
            System.out.println("DEV MENU");
            choice = le.getChoice(options); // show Menu options
            switch (choice) {
                case 1:
                    nu.createNurse();
                    break;
                case 2:
                    nu.findNurse();
                    break;
                case 3:
                    nu.updateNurse();
                    break;
                case 4:
                    nu.deleteNurse();
                    break;
                case 5:
                    pa.addPatient();
                    break;
                case 6:
                    pa.listPatients();
                    break;
                case 7:
                    pa.sortPatients();
                    break;
                case 8:
                    String check = le.inputSaveData("Do you want to save data [1/0-Y/N-T/F]: ");
                    if (check.equals("1") || check.equals("t") || check.equals("y")) {
                       
                        pa.saveData();
                        nu.saveData();
                        
                        System.out.println("Successfully save data!!!");
                    }break;

                case 9:

                default:
                    System.out.println("Good bye!");
            }
        } while (choice > 0 && choice < options.length);

    }

}
