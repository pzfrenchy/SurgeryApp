/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

/**
 * Singleton class containing lists of members associated with the app.
 * @author Dan
 */
public class Members {
    
    /**
     * Members fields, each is a list of type CreateMembersList
     */
    private final MemberList patients = new MemberList();
    private final MemberList doctors = new MemberList();
    private final MemberList secretaries = new MemberList();
    private final MemberList pharmacists = new MemberList();
    
    private static final Members INSTANCE = new Members();
    
    private Members(){};
    
    /**
     * Method to access members lists, once instance is created use get methods
     * to access the correct list of members.
     * 
     * @return instance of members
     */
    public static Members getInstance(){
        return INSTANCE;
    }
    
    /**
     * method for accessing the patients members list.
     * @return list of Patient objects
     */
    public MemberList getPatients(){
        return patients;
    }
    
    /**
     * method for accessing the doctors members list.
     * @return list of Patient objects
     */
    public MemberList getDoctors(){
        return doctors;
    }
    
    /**
     * method for accessing the secretaries members list.
     * @return list of Secretary objects
     */
    public MemberList getSecretaries(){
        return secretaries;
    }
    
    /**
     * method for accessing the pharmacists members list
     * @return list of Pharmacist objects
     */
    public MemberList getPharmacists(){
        return pharmacists;
    }
}
