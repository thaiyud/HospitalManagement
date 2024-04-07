/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import models.Patient;
import models.Tasks;

/**
 *
 * @author HP VICTUS
 */
public class patientsData {
    
    public void saveToFile(HashMap<String, Patient> hashmap) throws IOException {
        File file = new File("src\\data\\patients.dat");
        FileWriter writer = new FileWriter(file);

        for (Patient patient : hashmap.values()) {
            String line = patient.getId() + "," + patient.getName() + "," + patient.getAge() + ","
                    + patient.getGender() + "," + patient.getAddress() + "," + patient.getPhone() + ","
                    + patient.getDiagnosis()+ "," + patient.getAdmissionDate()+ "," + patient.getDischargeDate()+ ","
                    + patient.getNurseAssigned1()+","+ patient.getNurseAssigned2();

            writer.write(line + System.lineSeparator());
        }

        writer.close();
    }

    public HashMap<String, Patient> loadFromFile() throws FileNotFoundException, IOException {

        HashMap<String, Patient> hashmap = new HashMap<>();
        ArrayList<Tasks> listPatients = new ArrayList<>();
        File file = new File("src/data/patients.dat");

        if (!file.exists()) {
            System.err.println("File does not exist!");
            return hashmap;
        }

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] item = line.trim().split(",");
                if (item.length == 11) {
                    String patientId = item[0].trim();
                    String name = item[1].trim();
                    String age = item[2].trim();
                    String gender = item[3].trim();
                    String address = item[4].trim();
                    String phone = item[5].trim();
                    
                    
                    String diagnosis = item[6].trim();
                    String admissionDate = item[7].trim();                      
                    String dischargeDate = item[8].trim();          
                    String nurseAssigned1 = item[9].trim();                    
                    String nurseAssigned2 = item[10].trim(); 

                    Patient patient = new Patient(patientId, name, age, gender, address, phone, diagnosis, admissionDate,dischargeDate,nurseAssigned1,nurseAssigned2 );
                    hashmap.put(patientId, patient);
                } else {
                    System.err.println("Invalid data: " + line);
                }
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return hashmap;
    }

}
