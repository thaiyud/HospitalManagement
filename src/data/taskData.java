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

import java.util.HashMap;
import models.Nurse;
import models.Tasks;

/**
 *
 * @author HP VICTUS
 */
public class taskData {
    public void saveToFile(HashMap<String, Tasks> hashmap) throws IOException {
        File file = new File("src\\data\\tasks.dat");
        FileWriter writer = new FileWriter(file,false);

        for (Tasks task : hashmap.values()) {
              if (task != null && task.getIdNurse() != null) {
              String line = task.getIdNurse();

        if (task.getIdPatient1() != null) {
            line += "," + task.getIdPatient1();
        }

        if (task.getIdPatient2() != null) {
            line += "," + task.getIdPatient2();
        }

            writer.write(line + System.lineSeparator());
        }}

        writer.close();
    }
    public HashMap<String, Tasks> loadFromFile() throws FileNotFoundException, IOException {

        HashMap<String, Tasks> hashmap = new HashMap<>();

        File file = new File("src/data/tasks.dat");

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
                    String staffId = item[0].trim();
                    String idPatient1 = item.length > 1 ? item[1].trim() : "";
                    String idPatient2 = item.length > 2 ? item[2].trim() : "";
                  
                    

                    Tasks task = new Tasks(staffId, idPatient1,idPatient2);
                    hashmap.put(staffId, task);

                
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
