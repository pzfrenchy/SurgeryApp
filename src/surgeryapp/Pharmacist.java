/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;

/**
 * class for creation of pharmacist objects to model the pharmacist role in the 
 * surgery application. Only implements person methods and associated fields.
 * @author Dan
 */
public class Pharmacist implements Person{
    
    private String name;
    private LocalDate dob;
    private char gender;
    private Contact contact;
    
    public Pharmacist(String n, LocalDate d, char g, Contact c){
        this.name = n;
        this.dob = d;
        this.gender = g;
        this.contact = c;
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
    
    private void init(){
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        members.getPharmacists().addMember(this);
    }
    
}
