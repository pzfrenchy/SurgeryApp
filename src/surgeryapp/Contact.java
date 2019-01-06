/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

/**
 * class to create an object containing fields and methods to store, access and 
 * modify contact information for a person. String used for phoneNum to allow 
 * for international codes (+44) if required.
 * @author Dan
 */
public class Contact {
    
    private String house;
    private String road;
    private String town;
    private String postcode;
    private String phoneNum;
    
    public Contact (String h, String r, String t, String p, String ph){
        this.house = h;
        this.road = r;
        this.town = t;
        this.postcode = p;
        this.phoneNum = ph;
    }
    
    /**
     * method to set the house number or name.
     * @param h the number or name passed as a string.
     */
    public void setHouse(String h){
        this.house = h;
    }
    
    /**
     * method to get the field house from class contact.
     * @return the house field as a string
     */
    public String getHouse(){
        return house;
    }
    
    /**
     * method to set the road field containing the road name
     * @param r the string containing the road name
     */
    public void setRoad(String r){
        this.road = r;
    }
    
    /**
     * method to get the road field of class contact.
     * @return the road field as a string
     */
    public String getRoad(){
        return road;
    }
    
    /**
     * method to set the town field of class contact
     * @param t a string containing the town data
     */
    public void setTown(String t){
        this.town = t;
    }
    
    /**
     * method to get the town field of class contact
     * @return the town field as a string
     */
    public String getTown(){
        return town;
    }
    
    /**
     * method to set the postcode field of class contact
     * @param p a sting containing the postcode data
     */
    public void setPostcode(String p){
        this.postcode = p;
    }
    
    /**
     * method to get the postcode field of class contact.
     * @return the postcode field as a string
     */
    public String getPostcode(){
        return postcode;
    }
    
    /**
     * method to set the phoneNum field of class contact, string used to allow 
     * for international dialing codes
     * @param p a string containing the phone number data
     */
    public void setPhoneNum(String p){
        this.phoneNum = p;
    }
    
    /**
     * method to get the phoneNum field of the class contact
     * @return the phoneNum field as a string.
     */
    public String getPhoneNum(){
        return phoneNum;
    }
}
