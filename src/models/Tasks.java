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
public class Tasks {
    private String idNurse;
    private String  idPatient1;
   private String idPatient2;

    public Tasks(String idNurse,String idPatient1, String idPatient2) {
        this.idNurse = idNurse;
        this.idPatient1 = idPatient1;
        this.idPatient2 = idPatient2;
    }

    public Tasks() {
        
    }

    public String getIdNurse() {
        return idNurse;
    }

    public void setIdNurse(String idNurse) {
        this.idNurse = idNurse;
    }

    public String getIdPatient1() {
        return idPatient1;
    }

    public String getIdPatient2() {
        return idPatient2;
    }

    public void setIdPatient1(String idPatient1) {
        this.idPatient1 = idPatient1;
    }

    public void setIdPatient2(String idPatient2) {
        this.idPatient2 = idPatient2;
    }
   
     @Override
    public String toString() {
        return idNurse+ "," +idPatient1 + ", " + idPatient2 ;
    }

    
}
