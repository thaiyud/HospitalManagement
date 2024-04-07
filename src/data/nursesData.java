/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import models.Nurse;
import models.Tasks;

/**
 *
 * @author HP VICTUS
 */
public class nursesData {

    public void saveToFile(HashMap<String, Nurse> hashmap) throws IOException {
        File file = new File("src\\data\\nurses.dat");
        FileWriter writer = new FileWriter(file);

        for (Nurse nurse : hashmap.values()) {
            String line = nurse.getId() + "," + nurse.getName() + "," + nurse.getAge() + ","
                    + nurse.getGender() + "," + nurse.getAddress() + "," + nurse.getPhone() + ","
                    + nurse.getDepartment() + "," + nurse.getShift() + "," + nurse.getSalary();

            writer.write(line + System.lineSeparator());
        }

        writer.close();
    }

    public HashMap<String, Nurse> loadFromFile() throws FileNotFoundException, IOException {

        HashMap<String, Nurse> hashmap = new HashMap<>();
ArrayList<Tasks> listPatients = new ArrayList<>();
        File file = new File("src/data/nurses.dat");

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
                if (item.length == 9) {
                    String staffId = item[0].trim();
                    String name = item[1].trim();
                    String age = item[2].trim();
                    String gender = item[3].trim();
                    String address = item[4].trim();
                    String phone = item[5].trim();
                    String department = item[6].trim();
                    String shift = item[7].trim();
                    float salary = Float.parseFloat(item[8].trim());

                    Nurse nurse = new Nurse(staffId, name, age, gender, address, phone, department, shift, salary  );
                    hashmap.put(staffId, nurse);
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
