/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dan
 */
public class SurgeryAppTest {
    
    private LocalDate dob1;
    private LocalDate dob2;
    private LocalDate dob3;
    private Contact contact1;
    private Person patient1;
    private Contact contact2;
    private Person patient2;
    private Person patient3;
    private Person doctor1;
    private Person doctor2;
    private Person secretary;
    private Person pharmacist;
    private LocalDateTime dateTime1;
    private Schedule schedule1;
    private LocalDateTime dateTime2;
    private Schedule schedule2;
    private Appointment appointment1;
    private Appointment appointment2;
    private Note note1;
    private Prescription prescription1;
    
    /**
     * Creation of objects used during testing.
     */
    @Before
    public void setup(){
        //create dates for dob
        dob1 = LocalDate.of(1982, 3, 14);
        dob2 = LocalDate.of(1986, 6, 27);
        dob3 = LocalDate.of(1984, 9, 2);
        
        //create contact objects
        contact1 = new Contact("4", "The Mews", "Penzance", "TR182SA", "01736358477");
        contact2 = new Contact("5", "The Mews", "Penzance", "TR182SA", "01736358477");
        
        PersonFactory personFactory = new PersonFactory();
        
        //create patient objects
        patient1 = personFactory.getInstance("Patient", "Daniel", dob2, 'm', contact2);
        patient2 = personFactory.getInstance("Patient", "Bob", dob2, 'm', contact2);
        patient3 = personFactory.getInstance("Patient", "Bob", dob2, 'm', contact2);
                
        //create doctor objects
        doctor1 = personFactory.getInstance("Doctor", "Rob", dob3, 'm', contact2);
        doctor2 = personFactory.getInstance("Doctor", "Annie", dob2, 'f', contact2);
        
        //create secretary objects
        secretary = personFactory.getInstance("Secretary", "Ryan", dob1, 'm', contact1);
        
        //create pharmascist objects
        pharmacist = personFactory.getInstance("Pharmacist", "Natalie", dob1, 'f', contact2);
        
        //create appointment dates
        dateTime1 = LocalDateTime.of(2017,7,5,9,30);
        dateTime2 = LocalDateTime.of(2017,7,5,9,0);
        
        //create doctor schedule and add to dcotors
        schedule1 = new Schedule(dateTime1, doctor1, patient1);
        schedule2 = new Schedule(dateTime2, doctor1, patient2);
        
        //create appointments and add to patients
        appointment1 = new Appointment(dateTime1, doctor1, patient1, "sore ear");
        appointment2 = new Appointment(dateTime2, doctor2, patient2, "high temp");
        
        //create note and add to patient
        note1 = new Note("This patient is a hypochondriac");
        ((Patient)patient1).addNote(note1);
        
        //create prescription and add to patient
        prescription1 = new Prescription("Itcing powder", "100mg", 2);
        ((Patient)patient1).addPrescription(prescription1);
        ((Patient)patient2).addPrescription(prescription1);
    }
    
    /**
     * Test of addPatient method, of class Doctor. This test will also confirm 
     * that multiple Patient objects can be added to a Doctor arrayList object.
     */
    @Test
    public void testGetPatients(){
        ((Doctor)doctor1).addPatient(patient1);
        ((Doctor)doctor1).addPatient(patient2);
        assertEquals(Arrays.asList(patient1, patient2), ((Doctor)doctor1).getPatients());
    }

    /**
     * Test of addToSchedule method, of class Doctor. This test will also confirm 
     * that multiple Patient objects can be added to a Doctor arrayList object.
     * The test will also confirm that the schedule is sorted into date order.
     */
    @Test
    public void testAddToSchedule(){
        ((Doctor)doctor1).clearSchedule();
        ((Doctor)doctor1).addToSchedule(schedule1);
        assertEquals(Arrays.asList(schedule1), ((Doctor)doctor1).getSchedule());
        ((Doctor)doctor1).addToSchedule(schedule2);
        assertEquals(Arrays.asList(schedule2, schedule1), ((Doctor)doctor1).getSchedule());
    }

    /**
     * Test of removeScheduleSlot method, of class Doctor. This test will confirm
     * if schedule slots can be removed from a doctor's schedule.
     */ 
    @Test
    public void testRemoveScheduleSlot(){
        ((Doctor)doctor1).clearSchedule();
        ((Doctor)doctor1).addToSchedule(schedule1);
        assertEquals(Arrays.asList(schedule1), ((Doctor)doctor1).getSchedule());
        ((Doctor)doctor1).addToSchedule(schedule2);
        assertEquals(Arrays.asList(schedule2, schedule1), ((Doctor)doctor1).getSchedule());
        ((Doctor)doctor1).removeFromSchedule(schedule1);
        assertEquals(Arrays.asList(schedule2), ((Doctor)doctor1).getSchedule());
    }
    
    /**
     * Test to ensure appointments are automatically added to patients when they are created.
     */
    @Test
    public void testAppointmentAutoApplies(){
        assertEquals(Arrays.asList(appointment1), ((Patient)patient1).getAppointments());
        Appointment appointment3 = new Appointment(dateTime2, doctor2, patient1, "high temp");
        assertEquals(Arrays.asList(appointment3, appointment1), ((Patient)patient1).getAppointments());
    }
    
    /**
     * Test to ensure a doctors scheduleList is updated automatically when an appointment is created.
     */
    @Test
    public void testScheduleAutoUpdated(){
        assertNotNull("List shouldn't be null", ((Doctor)doctor1).getSchedule());
        ((Doctor)doctor1).clearSchedule();
        assertEquals(Arrays.asList(), ((Doctor)doctor1).getSchedule());
        Appointment appointment3 = new Appointment(dateTime2, doctor1, patient1, "high temp");
        assertNotNull("List shouldn't be null", ((Doctor)doctor1).getSchedule());
    }
    
    /**
     * Test of searchScheduleByDate method, of class Doctor.
     */
    @Test
    public void testSearchScheduleByDate(){
        ((Doctor)doctor1).clearSchedule();
        ((Doctor)doctor1).addToSchedule(schedule1);
        ((Doctor)doctor1).addToSchedule(schedule2);
        assertEquals(schedule1, ((Doctor)doctor1).getScheduleByDate(dateTime1));
        assertEquals(schedule2, ((Doctor)doctor1).getScheduleByDate(dateTime2));
    }
    
    /**
     * Test of addToAppointment method, of class Patient. This test will confirm 
     * whether Appointment objects can be added to a List in a Patient object. The 
     * test will also confirm that appointments are sorted into date order.
     */
    public void testAddToAppointment(){
        assertEquals(Arrays.asList(appointment1), ((Patient)patient1).getAppointments());
        ((Patient)patient1).addToAppointments(appointment2);
        assertEquals(Arrays.asList(appointment2, appointment1), ((Patient)patient1).getAppointments());
    }
    
    /*
    * Test to ensure that when a new appointment is created the correct doctor has the 
    * appointment added to their scheduleList.
    */
    @Test
    public void testNewAppointmentAddsToSchedule(){ //init method
        
    }
    
    /**
     * Test for changing a patient's appointment in class Patient.
     */
    @Test
    public void testChangeAppointment(){
        ((Patient)patient1).clearAppointments();
        ((Patient)patient1).addToAppointments(appointment1);
        assertEquals(Arrays.asList(appointment1), ((Patient)patient1).getAppointments());
        assertEquals(dateTime1, appointment1.getDate());
        appointment1.setDate(dateTime2);
        assertEquals(dateTime2, appointment1.getDate());
    }
    
    /**
     * Test for getAppointmentByStatus, of the class Patient. This test will confirm
     * if the correct appointment is returned based on a given status.
     */
    @Test
    public void testGetAppointmentByStatus(){
        ((Patient)patient1).clearAppointments();
        ((Patient)patient1).addToAppointments(appointment1);
        ((Patient)patient1).addToAppointments(appointment2);
        assertEquals(Arrays.asList(), ((Patient)patient1).getAppointmentByStatus(AppointmentStatus.ACTIVE));
        appointment1.setStatus(AppointmentStatus.ACTIVE);
        assertEquals(Arrays.asList(appointment1), ((Patient)patient1).getAppointmentByStatus(AppointmentStatus.ACTIVE)); 
        appointment2.setStatus(AppointmentStatus.ACTIVE);
        assertEquals(Arrays.asList(appointment2, appointment1), ((Patient)patient1).getAppointmentByStatus(AppointmentStatus.ACTIVE)); 
    }

    /**
     * Test of setStatus method, of class Appointment. This test will confirm
     * if appointment status can be correctly set and returned.
     */     
    @Test
    public void testSetStatus(){
        ((Patient)patient1).addToAppointments(appointment1);
        assertEquals(AppointmentStatus.INACTIVE, appointment1.getStatus());
        appointment1.setStatus(AppointmentStatus.ACTIVE);
        assertEquals(AppointmentStatus.ACTIVE, appointment1.getStatus());
    }
    
    /**
     * Test of memberSearch method, of class Members. This test will confirm if 
     * the search method works correctly. It will also confirm if multiple 
     * objects can be added to a members arrayList object.
     */
    @Test
    public void testMemberSearch(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        members.getPatients().clearList();
        members.getPatients().addMember(patient1);
        members.getPatients().addMember(patient2);
        assertEquals(patient1, members.getPatients().findMemberByName("Daniel"));
        assertEquals(patient2, members.getPatients().findMemberByName("Bob"));
        assertEquals(null, members.getPatients().findMemberByName("Sue"));
    }
    
    /**
     * Test of sortMembers method, of class Members. This test will sort members 
     * into alphabetical order.
     */
    @Test
    public void testSortMembers(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        members.getPatients().clearList();
        members.getPatients().addMember(patient1);
        members.getPatients().addMember(patient2);
        assertEquals(Arrays.asList(patient1, patient2), members.getPatients().getMembers());
        members.getPatients().sortMembers();
        assertEquals(Arrays.asList(patient2, patient1), members.getPatients().getMembers());
    }
    
}
