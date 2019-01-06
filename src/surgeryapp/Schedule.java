/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Dan
 */
public class Schedule {
    
    private LocalDateTime slotDate;
    private Person doctor;
    private Person patient;
    
    public Schedule(LocalDateTime d, Person doc, Person p){
        this.slotDate = d;
        this.doctor = doc;
        this.patient = p;
    }
    
    /**
     * method to change the date field of class scheduleSlot, accepts a LocalDateTime value
     * @param d the date being set
     */
    public void setDate(LocalDateTime d){
        this.slotDate = d;
    }
    
    /**
     * method to get the dateTime field of the scheduleSlot
     * @return the date and time in LocalDateTime format
     */
    public LocalDateTime getDate(){
        return slotDate;
    }
    
    /**
     * method to set the doctor field of the class scheduleSlot
     * @param d the doctor object to be associated.
     */
    public void setDoctor(Doctor d){
        this.doctor = d;
    }
    
    /**
     * method to get the the doctor object associated with the scheduleSlot.
     * @return a doctor object
     */
    public Person getDoctor(){
        return doctor;
    }
    
    /**
     * method to set the patient field of the scheduleSlot class 
     * @param p the patient object to be set.
     */
    public void setPatient(Patient p){
        this.patient = p;
    }
    
    /**
     * method to get the data associated with the patient field of the class scheduleSlot
     * @return a patient object.
     */
    public Person getPatient(){
        return patient;
    }
    
    /**
     * method to get the schedule date as a String and formatted to dd mm yyyy HH:ss
     * @return a string containing the formatted date
     */
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = slotDate.format(formatter);
        return formattedDate;
    }
}
