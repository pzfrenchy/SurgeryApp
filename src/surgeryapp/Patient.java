/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Patient class to allow a patients details to be recored in the system.
 * @author Dan
 */
public class Patient implements Person{
    
    private String name;
    private LocalDate dob;
    private char gender;
    private Contact contact;
    private Person doctor;
    private final List<Note> notes = new ArrayList();
    private final List<Prescription> prescriptions = new ArrayList();
    private final List<Appointment> appointments = new ArrayList();
    
    public Patient(String n, LocalDate d, char g, Contact c){
        this.name = n;
        this.dob = d;
        this.gender = g;
        this.contact = c;
        this.appointments.clear();
        this.notes.clear();
        this.prescriptions.clear();
        init();
    }
    
    @Override
    public void setName(String n){
        this.name = n;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public void setDob(LocalDate d){
        this.dob = d;
    }
    
    @Override
    public LocalDate getDob(){
        return dob;
    }
    
    @Override
    public void setGender(char g){
        this.gender = g;
    }
    
    @Override
    public char getGender(){
        return gender;
    }
    
    @Override
    public void setContact(Contact c){
        this.contact = c;
    }
    
    @Override
    public Contact getContact(){
        return contact;
    }
    
    /**
     * Initialisation method called in the constructor, adds patient object to 
     * patients members list.
     */
    private void init(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        //add to patient members list
        members.getPatients().addMember(this);
    }
    
    /**
     * method to return the gender of a patient as a string.
     * @return string containing male or female
     */
    public String getGenderAsString(){
        String genderString;
        if (gender == 'f'){
            genderString="female";
        }
        else {
            genderString="male";
        }
        return genderString;
    }
    
    /**
     * method to associate the doctor allocated to a patient.
     * @param d the doctor to be allocated.
     */
    public void setDoctor(Person d){
        this.doctor = d;
    }
    
    /** 
     * method to retrieve the doctor allocated to a patient
     * @return the allocated doctor
     */
    public Person getDoctor(){
        return doctor;
    }
    
    /**
     * method to add a note to a patients list of notes
     * @param n the note being added
     */
    public void addNote(Note n){
        notes.add(n);
    }
    
    /**
     * method to get all the notes on a patients record
     * @return an arrayList of note objects
     */
    public List<Note> getNotes(){
        return notes;
    }
    
    /**
     * method used to format a list of notes into a readable list of Strings.
     * @return a list of stings based on notes.
     */
    public List<String> getFormattedNotes(){
        List formattedNotes = new ArrayList();
        for (int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            String noteInfo = "Date Created: " + n.getFormattedDate() + " Content: " + n.getContent();
            formattedNotes.add(noteInfo);
        }
        return formattedNotes;
    }
    
    /**
     * method to add a prescription object to a patients list of prescriptions
     * @param p the prescription to be added
     */
    public void addPrescription(Prescription p){
        prescriptions.add(p);
    }
    
    /**
     * method to retrieve all of the patients prescriptions
     * @return an arrayList of prescription objects
     */
    public List<Prescription> getPrescription(){
        return prescriptions;
    }
    
    /**
     * method to format the prescription list into a list of readable stings.
     * @return the list of strings
     */
    public List<String> getFormattedPrescription(){
        List formattedPrescription = new ArrayList();
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription p = prescriptions.get(i);
            String presInfo = "Medicine: " + p.getMedicine()+ " Dosage: " + p.getDosage() + " Quantity: " + p.getQuantity();
            formattedPrescription.add(presInfo);
        }
        return formattedPrescription;
    }
    
    /**
     * method to add appointments to a patients appointment list.
     * @param a the appointment being added.
     */
    public void addToAppointments(Appointment a){
        appointments.add(a);
        Collections.sort(appointments, (Appointment a1, Appointment a2) ->{
        return a1.getDate().compareTo(a2.getDate());
        });
    }
    
    /**
     * method to set a patients appointment list to empty.
     */
    public void clearAppointments(){
        appointments.clear();
    }
    
    /**
     * method to retrieve all of the appointments allocated to a patient
     * @return an arrayList of appointment objects.
     */
    public List<Appointment> getAppointments(){
        return appointments;
    }
    
    /**
     * method for formatting the a patients appointments into a readable list of strings
     * @return a list of strings
     */
    public List<String> getFormattedAppointments(){
        List formattedAppointments = new ArrayList();
        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            String appInfo = ("Date and time: " + a.getFormattedDate() + " Doctor: " + a.getDoctor().getName() + " Symptom: " + a.getSympton() + " Status: " + a.getStatus());
            formattedAppointments.add(appInfo);
        }
        return formattedAppointments;
    }
    
    /**
     * A method used to search for appointments by a specified appointment status, it will return an arrayList of 
     * appointments matching the search criteria.
     * 
     * @param a the appointment status being searched for.
     * @return 
     */
    public List<Appointment> getAppointmentByStatus(AppointmentStatus a){
        List<Appointment> appList = new ArrayList();
        try{
            appointments.stream().filter((app) -> (app.getStatus()==a)).forEachOrdered((app) -> {
            appList.add(app);
            });
            return appList;
        }
        catch(Exception e){
            return appList;
        }
    }
    
    public Appointment getAppointmentByDate(LocalDateTime d){
        try{
            for (int i = 0; i < appointments.size(); i++) {
                Appointment a = appointments.get(i);
                if (a.getDate().equals(d)){
                    return a;
                }
            }
        }
        catch(Exception e){
            return null;
        }
        return null;
    }
    
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dob.format(formatter);
        return formattedDate;        
    }

}
