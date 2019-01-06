/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class to create an appointment object containing fields and methods to hold 
 * the values associated with a patients appointment. A new instance will automatically 
 * create a scheduleSlot instance and add it to the associated doctor object.
 * @author Dan
 */
public class Appointment{
    
    private LocalDateTime dateTime;
    private Person patient;
    private Person doctor;
    private String symptom;
    private AppointmentStatus status; 
    
    public Appointment(LocalDateTime d, Person doc, Person p, String symptom) {
        this.dateTime = d;
        this.doctor = doc;
        this.patient = p;
        this.symptom = symptom;
        this.status = AppointmentStatus.INACTIVE;
        init();
    }
    
    /**
    * initialisation method for class appointment.
    * creation of a new appointment also creates a scheduleSlot that is allocated to the correct doctor.
    */
    private void init(){
        //cast patient(person) to patient to access patients methods
        Patient p = (Patient) patient;
        
        //add to patients appointments
        p.addToAppointments(this);

        //cast doctor(person) to doctor to access doctors methods
        Doctor d = (Doctor) doctor;
        
        //create a scheduleslot based on appointment
        Schedule s = new Schedule(dateTime, d, p);
        
        //add scheduleSlot to doctors schedule list
        d.addToSchedule(s);   
    }
    
    /**
     * method to change the date field of class appointment, accepts a LocalDateTime value
     * @param d the date being set
     */
    public void setDate(LocalDateTime d){
        this.dateTime = d;
    }
    
    /**
     * method to get the dateTime field of the appointment
     * @return the date and time in LocalDateTime format
     */
    public LocalDateTime getDate(){
        return dateTime;
    }
    
    /**
     * method to set the doctor field of the class appointment
     * @param d the doctor object to be associated.
     */
    public void setDoctor(Doctor d){
        this.doctor = d;
    }
    
    /**
     * method to get the the doctor object associated with the appointment.
     * @return a doctor object
     */
    public Person getDoctor(){
        return doctor;
    }
    
    /**
     * method to set the patient field of the appointment class 
     * @param p the patient object to be set.
     */
    public void setPatient(Patient p){
        this.patient = p;
    }
    
    /**
     * method to get the data associated with the patient field of the class appointment
     * @return a patient object.
     */
    public Person getPatient(){
        return patient;
    }
    
    /**
     * method to set the symptom field of the class appointment
     * @param s the string containing the symptom
     */
    public void setSymptom(String s){
        this.symptom = s;
    }
    
    /**
     * method to get the symptom field of the class appointment
     * @return a string containing the symptom data
     */
    public String getSympton(){
        return symptom;
    }
    
    /**
     * method to set the status field of the class appointment, values based on 
     * the enum AppointmentStatus
     * @param a enum value from AppointmentStatus
     */
    public void setStatus(AppointmentStatus a){
        this.status = a;
    }
    
    /**
     * method to get the status field of the class appointment
     * @return enum value of the status field.
     */
    public AppointmentStatus getStatus(){
        return status;
    }
    
    /**
     * method to return a formatted date in the dd-mm-yyyy HH:ss format
     * @return a string containing the formatted date.
     */
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }
}
