/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * class for creation of note objects containing the date of creation and the content
 * of the note in a string field.
 * @author Dan
 */
public class Note {
    
    private final LocalDate date;    
    private String content;
        
    public Note(String n){
        this.date = LocalDate.now();
        this.content = n;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public void setContent(String c){
        this.content = c;
    }
    
    public String getContent(){
        return content;
    }    
    
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = date.format(formatter);
        return formattedDate;        
    }
}
