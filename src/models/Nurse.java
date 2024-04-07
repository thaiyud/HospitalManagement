/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author HP VICTUS
 */
public class Nurse extends person {
    private String department;
    private String shift;
    private float salary;
  

    public Nurse() {
        super(null, null, null, null, null, null);
      
        
    }

    
    
    public Nurse(String id, String name, String age, String gender, String address, String phone,String department, String shift, float salary) {
        super(id, name, age, gender, address, phone);
        this.department = department;
        this.shift = shift;
        this.salary = salary;
   
        
        
    }

    public String getDepartment() {
        return department;
    }

    public String getShift() {
        return shift;
    }

    public float getSalary() {
        return salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }


    
    
    
    @Override
    public String toString() {
        return  getId() + "," + getName() + "," + getAge() + "," + getGender() + ", "+ getAddress() + ", "+ getPhone() + ", "+ getDepartment()+ ", "+ getShift()+", "+getSalary();
    }
    
}
