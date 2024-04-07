/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import controllers.Utinities;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import models.Nurse;
import models.Patient;
import models.Tasks;

/**
 *
 * @author HP VICTUS
 */
public class hospital {
    Utinities le;
    Scanner sc;
    
    Nurse nu;
    Patient pa;
    Tasks ta;
    
    patientsData pati;
    nursesData da;
    taskData tada;
    
    
    
    private static HashMap<String, Nurse> nurseMap;
    private static HashMap<String, Tasks> taskMap;
    private static HashMap<String, Patient> patiMap;

   
    public hospital() throws IOException {
        le = new Utinities();
        sc = new Scanner(System.in);
        nu = new Nurse();
        ta = new Tasks();
        pa = new Patient();
        
        da = new nursesData();
        pati = new patientsData();
        tada = new taskData();
        
        nurseMap = new HashMap<>();
        taskMap = new HashMap<>();
        patiMap = new HashMap<>();
        
        hospital.patiMap = pati.loadFromFile();
        hospital.nurseMap = da.loadFromFile();
        hospital.taskMap = tada.loadFromFile();
        
        
    }
    
    
    
    
    
    public  void setTaskMap(HashMap<String, Tasks> taskMap) {
        hospital.taskMap = taskMap;
    }
    
    public void setPatiMap(HashMap<String, Patient> patiMap) {
        hospital.patiMap = patiMap;
    }
    
    public  void setNurseMap(HashMap<String, Nurse> nurseMap) {
        hospital.nurseMap = nurseMap;
    }
    
    public static HashMap<String, Tasks> getTaskMap() {
        return taskMap;
    }
    
    public static HashMap<String, Patient> getPatiMap() {
        return patiMap;
    }
    
    public static HashMap<String, Nurse> getNurseMap() {
        return nurseMap;
    }
}
