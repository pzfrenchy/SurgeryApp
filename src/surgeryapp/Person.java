/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;

/**
 *
 * @author Dan
 */
public interface Person {

    public void setName(String n);
    
    public String getName();
    
    public void setDob(LocalDate d);
    
    public LocalDate getDob();
    
    public void setGender(char g);
    
    public char getGender();
    
    public void setContact(Contact c);
    
    public Contact getContact();
}
