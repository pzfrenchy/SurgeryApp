/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class to create a list of members based on person objects and define associated methods for 
 * manipulating the created arrayLists.
 * @author Dan
 */
public class MemberList{
    
    private final List<Person> m = new ArrayList<>();
    
    public MemberList(){
    }
    
    /**
     * method to add a member to the arrayList
     * @param o the person object being added
     */
    public void addMember(Person o){
        m.add(o);
    }
    
    /**
     * method to get the members list as an arrayList
     * @return an arrayList containing person objects
     */
    public List<Person> getMembers(){
        return m;
    }
    
    /**
     * method to clear the members arrayList
     */
    public void clearList(){
        m.clear();
    }
    
    /**
     * A method to sort a members list into alphabetical order based on name.
     */
    public void sortMembers(){
        Collections.sort(m, (Person p1, Person p2) ->{
        return p1.getName().compareToIgnoreCase(p2.getName());
        });
    }
    
    /**
     * A method to find a person of a membersList by name(String) and return the person object.
     * 
     * @param n the name of the person being searched for.
     * @return the person object is returned.
     */
    public Person findMemberByName(String n){
        for (Person p : m) {
            if (p.getName().toUpperCase().equals(n.toUpperCase())){
                return p;
            }
        }
        return null;
    }
    
    /**
     * A method to check if a person exists in a membersList.
     * 
     * @param n the name of the person being searched for.
     * @return boolean return value, true if found, false if not.
     */
    public Boolean doesMemberExist(String n){
        return m.stream().anyMatch((p) -> (p.getName().toUpperCase().equals(n.toUpperCase())));
    }
     
}
