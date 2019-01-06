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
 * Doctors class used to create doctor objects with associated methods and fields.
 * creating a new doctor instance will automatically add the doctor to the Members.doctors list.
 * @author Dan
 */
public class Doctor implements Person{
   
    private String name;
    private LocalDate dob;
    private char gender;
    private Contact contact;
    private String room;
    private final List<Person> patients = new ArrayList<>();
    private final List<Schedule> schedule = new ArrayList<>();
    
    public Doctor(String n, LocalDate d, char g, Contact c){
        this.name = n;
        this.dob = d;
        this.gender = g;
        this.contact = c;
        
        //initialisation method
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
     * initialisation method called by constructor, automatically adds doctor 
     * objects to Members.Doctors list.
     */
    private void init(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        members.getDoctors().addMember(this);
    } 
    
    /**
     * method for setting the room field of class doctor
     * @param r the room location being set.
     */
    public void setRoom(String r){
        this.room = r;
    }

    /**
     * method to get the room field of class doctor
     * @return string containing the allocated room.
     */
    public String getRoom(){
        return room;
    }
    
    /**
     * method to add a patient object to a doctors arrayList of patients
     * @param p the patient object to be added
     */
    public void addPatient(Person p){
        patients.add(p);
    }
    
    /**
     * method to get all of the doctors associated patients.
     * @return an arrayList of associated patient objects
     */
    public List<Person> getPatients(){
        return patients;
    }
    
    /**
     * a method to add to a doctors schedule, the schedule is sorted by date after each addition.
     * @param s the scheduleSlot to be added. 
     */
    public void addToSchedule(Schedule s){
        schedule.add(s);
        Collections.sort(schedule, (Schedule s1, Schedule s2) ->{
        return s1.getDate().compareTo(s2.getDate());
        });
    }
    
    /**
     * method to clear the schedule arrayList
     */
    public void clearSchedule(){
        schedule.clear();
    }
    
    /**
     * method to get a list of all the doctors schedule objects
     * @return an arrayList of schedule objects
     */
    public List<Schedule> getSchedule(){
        return schedule;
    }
    
    /**
     * method to remove a schedule object from the doctors arrayList
     * @param s the scheduleSlot to be removed
     */
    public void removeFromSchedule(Schedule s){
        schedule.remove(s);
    }
    
    /**
     * method for formatting a doctors schedule list into a list of strings, to access
     * the patients appointment status the patients members list instance is called
     * and the appointment found.
     * @return a list of strings
     */
    public List<String> getFormattedSchedule(){
        List formattedSchedule = new ArrayList();
        for (int i = 0; i < schedule.size(); i++) {
            //get the schedule item
            Schedule s = schedule.get(i);
            //get the instance of members
            Members members = Members.getInstance();
            //find the patient by passing the schedule item patient
            Person p = members.getPatients().findMemberByName(s.getPatient().getName());
            //cast patient to Patient
            Patient patient = (Patient) p;
            //find the patients appointment
            Appointment a = patient.getAppointmentByDate(s.getDate());  
            String appInfo = ("Date and time: " + s.getFormattedDate() + " Patient: " + s.getPatient().getName() + " Status: " + a.getStatus());
            formattedSchedule.add(appInfo);
        }
        return formattedSchedule;
    }
    
    /**
     * method for searching a doctors schedule list by date and returning the matching scheduleSlot.
     * @param d the date to be searched for
     * @return the matching scheduleSlot
     */
    public Schedule getScheduleByDate(LocalDateTime d){
        for (int i = 0; i < schedule.size(); i++) {
            Schedule s = schedule.get(i);
            if (s.getDate()==d){
                return s;
            }
        }
        return null;
    }
    
    /**
     * method to check if a scheduleSlot date is already taken
     * @param d the LocalDateTime value being tested 
     * @return boolean value, true if found, false if not
     */
    public boolean findScheduleSlotByDate(LocalDateTime d){
        int i = 0;
        while (i < schedule.size()) {
            Schedule s = schedule.get(i);
            i++;
            if (s.getDate().equals(d)){
                return true;
            }
        }
        return false;
    }
    
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dob.format(formatter);
        return formattedDate;        
    }
}
