/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class used for printing reports only.
 * @author Dan French
 */
public class Reports {
    
    private static final Reports INSTANCE = new Reports();
    
    private Reports(){}
    
    /**
     * method to get singleton instance
     * @return single instance of class
     */
    public static Reports getInstance(){
        return INSTANCE;
    }
    
    //### Patient print reports start here ###
    
    /**
     * A method for printing out appointments based on their appointment status. A list from the members class 
     * is passed along with the required status. 
     * 
     * @param s the status the search is based on.
     * @param p the list of people to be searched.
     */ 
    public void printAppointmentsByStatus(AppointmentStatus s, List<Person> p){        
        System.out.println("Appointments by Status");
        try{
            for (int i = 0; i < p.size() ; i++) {
                Patient patient = (Patient) p.get(i);
                    List<Appointment> a = patient.getAppointmentByStatus(s);
                    LocalDateTime checkDate = LocalDateTime.now().minusMonths(1);
                    if (a.get(i).getDate().isAfter(checkDate)){
                        for (int j = 0; j < a.size(); j++) {
                            System.out.println("Date: " + a.get(j).getFormattedDate() + 
                                ", Doctor: " + a.get(j).getDoctor().getName() +
                                ", Symptom: " + a.get(j).getSympton() + 
                                ", Status: "  + a.get(j).getStatus().toString());
                        }
                    }
            }
        }
        catch(Exception e){
            System.out.println("No appointments match your criteria.");
        }
    }
    
    /**
     * A method for formatting an appointment list for a patient as a string. 
     * It will iterate over a patient's appointment list and return a string for 
     * each element.
     * 
     * @param p the patient being processed.
     * @return 
     */
    public String getAppointmentList(Person p){
        try{
            for (int i = 0; i < ((Patient)p).getAppointments().size(); i++) {
                Appointment a = ((Patient)p).getAppointments().get(i);
                return "Date: " + a.getFormattedDate() + 
                        ", Doctor: " + a.getDoctor().getName() + 
                        ", Symptom: " + a.getSympton() + 
                        ", Status: "  + a.getStatus().toString();
            }
        }
        catch(Exception e){
            return "No appointments available";
        }
        return "No appointments available";
    }
    
    /**
     * A method for formatting a patient's notes into a string ready for printing, 
     * the patient's notes list is iterated over and each list element is returned.
     * 
     * @param p the patient being processed.
     * @return 
     */
    public String getNotesList(Person p){
        try{
            for (int i = 0; i < ((Patient)p).getNotes().size(); i++) {
                Note n = ((Patient)p).getNotes().get(i);
                return "Date: " + n.getFormattedDate() + 
                        ", Contents: " + n.getContent();
            }
        }
        catch(Exception e){
            return "No notes available";
        }
        return "No notes available";
    }
   
    /**
     * A method for formatting a patient's prescriptions into a string. The patient's
     * prescription arrayList is iterated over and a string is returned for each element.
     * 
     * @param p the patient being processed.
     * @return
     */
    public String getPatientsPrescriptions(Person p){
        try{
            List<Prescription> l = ((Patient)p).getPrescription();
            for (int i = 0; i < ((Patient)p).getPrescription().size(); i++) {
                    return ("Date: " + l.get(i).getFormattedDate() + 
                    ", Medicine: " + l.get(i).getMedicine() + 
                    ", Dosage: " + l.get(i).getDosage() + 
                    ", Quantity: " + l.get(i).getQuantity());
                }
            }
        catch(Exception e){
            return ("No prescriptions available");
        }
        return ("No prescriptions available");
    }
    
    /**
     * A method for printing all prescriptions given to patients.
     * 
     * @return 
     */
    public List<String> getAllPrescriptions(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        List formattedPrescriptions = new ArrayList();
        try{
            List<Person> list = new ArrayList();
            list.addAll(members.getPatients().getMembers());
            for (int i = 0; i < list.size(); i++) {
                Person p = list.get(i);
                Patient patient = (Patient) p;
                List<Prescription> pres = patient.getPrescription();
                    for (int j = 0; j < pres.size(); j++) {
                        LocalDate checkDate = LocalDate.now().minusMonths(1);
                        if (pres.get(j).getDate().isAfter(checkDate)){
                            formattedPrescriptions.add("Date: " + pres.get(j).getFormattedDate() + 
                            ", Medicine: " + pres.get(j).getMedicine() + 
                            ", Dosage: " + pres.get(j).getDosage() + 
                            ", Quantity: " + pres.get(j).getQuantity());
                        }
                    }
            }
        }
        catch(Exception e){
            formattedPrescriptions.add(0, "No prescriptions available");
            return formattedPrescriptions;
        }
        return formattedPrescriptions;
    }
    
    /**
     * A method to format and add a patient record to a list of strings
     * 
     * @param p the patient being processed.
     * @return a list of strings holding a patients record.
     */
    public List<String> getPatientRecord(Person p){
        List<String> recordList = new ArrayList();
        Patient patient = (Patient)p;
        recordList.add("Patient Details");
        recordList.add("Name: " + patient.getName());
        recordList.add("Date of Birth: " + patient.getFormattedDate());
        recordList.add("Address: " + patient.getContact().getHouse());
        recordList.add(patient.getContact().getRoad());
        recordList.add(patient.getContact().getTown());
        recordList.add(patient.getContact().getPostcode());
        recordList.add(patient.getContact().getPhoneNum());
        recordList.add("-----------------------------------");
        recordList.add("Appointments");
        recordList.add(getAppointmentList(patient));
        recordList.add("Notes");
        recordList.add(getNotesList(patient));
        recordList.add("Prescriptions");
        recordList.add(getPatientsPrescriptions(patient)); 
        return recordList;
    }
    
    //### Patient reports end here ###
    
    //### Doctor reports start here ###
    
    /**
     * A method to print the doctor's schedule.
     * 
     * @param d the doctor being processed.
     */
    public void printSchedule(Person d){
        System.out.println("Schedule:");
        ((Doctor)d).getSchedule().forEach((s) -> {
            System.out.println("Date: " + s.getFormattedDate() + " Patient: " + s.getPatient().getName());
        });
    }
    
    //### Doctor reports end here ###
    
    //### Members reports start here ###
    
    /**
     * A method to print the members of a list, i.e patients, doctors etc.
     * only used in testing cli interface.
     * @param member 
     */
    public void printMembers(MemberList member){
        List<Person> list = member.getMembers();
        System.out.println("Members report");
        for (int i = 0; i < list.size(); i++) {
            Person p = list.get(i);
            System.out.println(p.getName());
        }
    }
   
    
    /**
     * A method to format all doctor's schedules from doctors members list and return
     * as a list of strings.
     * @return a list of strings containing schedule information for printing.
     */
    public List<String> getAllSchedules(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        List<String> formattedList = new ArrayList();
        List<Person> docs = members.getDoctors().getMembers();
        for (int i = 0; i < docs.size(); i++) {
            Person d = docs.get(i);
            Doctor doctor = (Doctor) d;
            formattedList.add(doctor.getName());
            for (int j = 0; j < doctor.getSchedule().size(); j++) {
                Schedule s = doctor.getSchedule().get(j);
                LocalDateTime checkDate = LocalDateTime.now().minusMonths(1);
                if (s.getDate().isAfter(checkDate)){
                    formattedList.add("Date: " + s.getFormattedDate() + " Patient: " + s.getPatient().getName());
                }
            }
        }
        return formattedList;
    }
    
    /**
     * A method to format all patients appointments and their status and return
     * a list of strings.
     * @return a list of strings containing appointment information for printing.
     */
    public List<String> getAllAppointments(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        List<String> formattedList = new ArrayList();
        List<Person> patientList = members.getPatients().getMembers();
        for (int i = 0; i < patientList.size(); i++) {
            Person p = patientList.get(i);
            Patient patient = (Patient) p;
            for (int j = 0; j < patient.getAppointments().size(); j++) {
                Appointment a = patient.getAppointments().get(j);
                LocalDateTime checkDate = LocalDateTime.now().minusMonths(1);
                if (a.getDate().isAfter(checkDate)){
                    formattedList.add("Date: " + a.getFormattedDate() + " Patient: "
                            + a.getPatient().getName() + " Doctor: " + a.getDoctor().getName()
                            + " Status: " + a.getStatus());
                }
            }
        }
        return formattedList;
    }    
    
    //### Members reports end here ###
}
