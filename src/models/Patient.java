/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author HP VICTUS
 */
public class Patient extends person{
    
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    private String nurseAssigned1;
            
    private String nurseAssigned2;

    public Patient() {
    }
    
    public Patient(String id, String name, String age, String gender, String address, String phone, String diagnosis,
            String admissionDate , String dischargeDate , String nurseAssigned1 , String nurseAssigned2) {
        super(id, name, age, gender, address, phone);
        this.diagnosis =  diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned1 = nurseAssigned1;
        
        this.nurseAssigned2 = nurseAssigned2;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public String getNurseAssigned1() {
        return nurseAssigned1;
    }

    public String getNurseAssigned2() {
        return nurseAssigned2;
    }
    

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public void setNurseAssigned1(String nurseAssigned1) {
        this.nurseAssigned1 = nurseAssigned1;
    }

    public void setNurseAssigned2(String nurseAssigned2) {
        this.nurseAssigned2 = nurseAssigned2;
    }
 
    @Override 
    public String toString(){
        return  getId() + "," + getName() + "," + getAge() + "," + getGender() + ", "+ getAddress() + ", "+ getPhone() + ", "+
                getDiagnosis() + ", "+ getAdmissionDate()+", "+getNurseAssigned1()+", "+ getNurseAssigned2();
 
    }
    
    
}
