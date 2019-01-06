/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * class for creation of prescription objects based on prescriptions that are
 * created for patients. Fields for medicine prescribed, dosage and quantity, 
 * date set on creation of new instance.
 * @author Dan
 */
public class Prescription {
    
    private String medicine;
    private String dosage;
    private int quantity;
    private LocalDate date;
    
    public Prescription(String m, String dosage, int q){
        this.date = LocalDate.now();
        this.dosage = dosage;
        this.medicine = m;
        this.quantity = q;
    }
    
    public void setMedicine(String m){
        this.medicine = m;
    }
    
    public String getMedicine(){
        return medicine;
    }
    
    public void setDosage(String d){
        this.dosage = d;
    }
    
    public String getDosage(){
        return dosage;
    }
    
    public void setQuantity(int q){
        this.quantity = q;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setDate(LocalDate d){
        this.date = d;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        return formattedDate;        
    }
    
}
