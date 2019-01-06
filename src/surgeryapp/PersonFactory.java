/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;

/**
 * Factory class for the creation of person objects based on concrete classes of 
 * patient, doctor, secretary and pharmacist.
 * @author Dan
 */
public class PersonFactory {
    
    public Person getInstance(String type, String n, LocalDate d, char g, Contact c) {
        switch (type.toUpperCase()) {
            case "PATIENT":
            {
                Person person = new Patient(n, d, g, c);
                return person;
            }
            case "DOCTOR":
            {
                Person person = new Doctor(n, d, g, c);
                return person;
            }
            case "SECRETARY":
            {
                Person person = new Secretary(n, d, g, c);
                return person;
            }
            case "PHARMACIST":
            {
                Person person = new Pharmacist(n, d, g, c);
                return person;
            }
            default:
                break;
        }
        return null;
    }
    
}
