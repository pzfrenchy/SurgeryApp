/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dan
 */
public class SurgeryApp {

    private static void constructGUI(){
        JFrame.setDefaultLookAndFeelDecorated(false);
        MainJFrame frame = new MainJFrame();
        frame.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        //create dates for dob
        LocalDate dob1 = LocalDate.of(1982, 3, 14);
        LocalDate dob2 = LocalDate.of(1986, 6, 27);
        LocalDate dob3 = LocalDate.of(1984, 9, 2);
        
        //create contact objects
        Contact contact1 = new Contact("4", "The Mews", "Penzance", "TR182SA", "01736358477");
        Contact contact2 = new Contact("5", "The Mews", "Penzance", "TR182SA", "01736358477");
        Contact contact3 = new Contact("5", "The Mews", "Penzance", "TR182SA", "01736358477");
        Contact contact4 = new Contact("15", "The Mews", "Penzance", "TR182SA", "01736358477");
        Contact contact5 = new Contact("43", "The Mews", "Penzance", "TR182SA", "01736358477");
        
        //create personFactory
        PersonFactory personFactory = new PersonFactory();
        
        //create patient objects
        Person patient1 = personFactory.getInstance("Patient", "Daniel", dob1, 'm', contact1);
        Person patient2 = personFactory.getInstance("Patient", "Bob", dob2, 'm', contact2);
        Person patient3 = personFactory.getInstance("Patient", "Lisa", dob3, 'f', contact3);
        Person patient4 = personFactory.getInstance("Patient", "Mike", dob1, 'm', contact1);
        Person patient5 = personFactory.getInstance("Patient", "Steve", dob2, 'm', contact5);
        Person patient6 = personFactory.getInstance("Patient", "Sue", dob3, 'f', contact4);
        Person patient7 = personFactory.getInstance("Patient", "Evie", dob1, 'f', contact5);
        Person patient8 = personFactory.getInstance("Patient", "Kane", dob2, 'm', contact3);
        Person patient9 = personFactory.getInstance("Patient", "Bella", dob3, 'f', contact4);
        Person patient10 = personFactory.getInstance("Patient", "Christine", dob1, 'f', contact2);
                
        //create doctor objects
        Person doctor1 = personFactory.getInstance("Doctor", "Rob", dob3, 'm', contact1);
        Person doctor2 = personFactory.getInstance("Doctor", "Annie", dob2, 'f', contact5);
        Person doctor3 = personFactory.getInstance("Doctor", "Stacey", dob1, 'f', contact2);
        
        //create secretary objects
        Person secretary = personFactory.getInstance("Secretary", "Ryan", dob2, 'm', contact3);
        
        //create pharmascist objects
        Person pharmacist = personFactory.getInstance("Pharmacist", "Natalie", dob3, 'f', contact2);
        
        //Add doctor to patients
        ((Patient)patient1).setDoctor(doctor1);
        ((Patient)patient2).setDoctor(doctor2);
        ((Patient)patient3).setDoctor(doctor3);
        ((Patient)patient4).setDoctor(doctor1);
        ((Patient)patient5).setDoctor(doctor2);
        ((Patient)patient6).setDoctor(doctor3);
        ((Patient)patient7).setDoctor(doctor1);
        ((Patient)patient8).setDoctor(doctor2);
        ((Patient)patient9).setDoctor(doctor3);
        ((Patient)patient10).setDoctor(doctor1);
       
        //create appointment dates
        LocalDateTime dateTime1 = LocalDateTime.of(2017,4,17,9,0);
        LocalDateTime dateTime2 = LocalDateTime.of(2017,4,17,9,30);
        LocalDateTime dateTime3 = LocalDateTime.of(2017,4,17,10,30);
        LocalDateTime dateTime4 = LocalDateTime.of(2017,4,18,9,30);
        LocalDateTime dateTime5 = LocalDateTime.of(2017,4,18,10,30);
        LocalDateTime dateTime6 = LocalDateTime.of(2017,4,18,11,30);
        LocalDateTime dateTime7 = LocalDateTime.of(2017,4,18,12,30);
        LocalDateTime dateTime8 = LocalDateTime.of(2017,4,19,9,30);
        LocalDateTime dateTime9 = LocalDateTime.of(2017,4,19,10,30);
        LocalDateTime dateTime10 = LocalDateTime.of(2017,4,19,11,30);
        LocalDateTime dateTime11 = LocalDateTime.of(2017,4,19,12,30);
        LocalDateTime dateTime12 = LocalDateTime.of(2017,4,20,9,30);
        LocalDateTime dateTime13 = LocalDateTime.of(2017,4,20,10,30);
        LocalDateTime dateTime14 = LocalDateTime.of(2017,4,21,9,30);
        LocalDateTime dateTime15 = LocalDateTime.of(2017,4,21,10,30);
        
        //create appointments and add to patients
        Appointment appointment1 = new Appointment(dateTime1, doctor1, patient1, "sore ear");
        Appointment appointment2 = new Appointment(dateTime2, doctor2, patient2, "high temp");
        Appointment appointment3 = new Appointment(dateTime3, doctor3, patient3, "saturday night feever");
        Appointment appointment4 = new Appointment(dateTime4, doctor1, patient4, "chicken pox");
        Appointment appointment5 = new Appointment(dateTime5, doctor2, patient5, "vomiting");
        Appointment appointment6 = new Appointment(dateTime6, doctor3, patient6, "the plague");
        Appointment appointment7 = new Appointment(dateTime7, doctor1, patient7, "broken arm");
        Appointment appointment8 = new Appointment(dateTime8, doctor2, patient8, "headache");
        Appointment appointment9 = new Appointment(dateTime9, doctor3, patient9, "runny nose");
        Appointment appointment10 = new Appointment(dateTime10, doctor1, patient10, "ichy belly button");
        Appointment appointment11 = new Appointment(dateTime11, doctor1, patient1, "back ache");
        Appointment appointment12 = new Appointment(dateTime12, doctor2, patient2, "ring worm");
        Appointment appointment13 = new Appointment(dateTime13, doctor3, patient3, "mumps");
        Appointment appointment14 = new Appointment(dateTime14, doctor1, patient4, "purple rash all over");
        Appointment appointment15 = new Appointment(dateTime15, doctor2, patient5, "common cold");
        
        //create note and add to patient
        Note note1 = new Note("This patient is a hypochondriac");
        ((Patient)patient1).addNote(note1);
        Note note2 = new Note("Patient shows signs of pregnancy, send for a scan");
        ((Patient)patient2).addNote(note2);
        Note note3 = new Note("Definite broken arm, sent to hospital");
        ((Patient)patient3).addNote(note3);
        Note note4 = new Note("Reported itching all over, seems to be related to a washing powder change, recommend changing back");
        ((Patient)patient6).addNote(note4);
        Note note5 = new Note("Patient has put on weight recently, recommended - must stop eating childrens easter eggs!");
        ((Patient)patient8).addNote(note5);
        Note note6 = new Note("Patient arrived with a sword impaled in their head, sent to hospital straight away");
        ((Patient)patient10).addNote(note6);
        
        //create prescription and add to patient
        Prescription prescription1 = new Prescription("Itcing powder", "100mg", 2);
        ((Patient)patient1).addPrescription(prescription1);
        Prescription prescription2 = new Prescription("Viagra", "500mg", 10);
        ((Patient)patient4).addPrescription(prescription2);
        Prescription prescription3 = new Prescription("Paracetmol", "200mg", 2);
        ((Patient)patient5).addPrescription(prescription3);
        Prescription prescription4 = new Prescription("A long holiday", "2 weeks", 1);
        ((Patient)patient7).addPrescription(prescription4);
        Prescription prescription5 = new Prescription("Codeine", "50mg", 6);
        ((Patient)patient9).addPrescription(prescription5);
        Prescription prescription6 = new Prescription("Bandages", "10m", 2);
        ((Patient)patient10).addPrescription(prescription6);

        //gui implementation
        SwingUtilities.invokeLater(() -> {
            constructGUI(); 
        });
    }
}
