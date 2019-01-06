/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Click Listener used by SecretaryPanel to implement a patient search by name(String)
 * Changes static fields in SecretryPanel with updated patient details.
 * 
 * @author Dan
 */
public class SearchClickListener implements ActionListener{
    
    private final JTextField searchField;
    private final JTextField name;
    private final JTextField phone;
    private final JTextField house;
    private final JTextField dob;
    private final JTextField road;
    private final JTextField doc;
    private final JTextField town;
    private final JTextField gender;
    private final JTextField postcode;
    private final JList appList;
    private final JList docList;
    
    public SearchClickListener(JTextField s, JTextField n, JTextField p, JTextField h, JTextField dob,
            JTextField r, JTextField doc, JTextField t, JTextField g, JTextField postcode,
            JList appList, JList docList){
        super();
        this.searchField = s;
        this.name = n;
        this.phone = p;
        this.house = h;
        this.dob = dob;
        this.road = r;
        this.doc = doc;
        this.town = t;
        this.gender = g;
        this.postcode = postcode;
        this.appList = appList;
        this.docList = docList;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //Get instance of Members to access Members Lists
            Members members = Members.getInstance();
            
            //find person
            Person p = members.getPatients().findMemberByName(searchField.getText());
            
            //make person a patient to access patient methods
            Patient patient = (Patient)p;
            
            //find patients doctor
            Person d = patient.getDoctor();
            
            //make (person)doctor a doctor to access doctor methods
            Doctor doctor = (Doctor)d;
            
            //update fields with patient information
            name.setText(patient.getName());
            dob.setText(patient.getFormattedDate());
            doc.setText(patient.getDoctor().getName());
            house.setText(patient.getContact().getHouse());
            phone.setText(patient.getContact().getPhoneNum());
            road.setText(patient.getContact().getRoad());
            town.setText(patient.getContact().getTown());
            postcode.setText(patient.getContact().getPostcode());
            gender.setText(patient.getGenderAsString());
            appList.setListData(patient.getFormattedAppointments().toArray());
            docList.setListData(doctor.getFormattedSchedule().toArray());
        }
        catch(Exception a){
            //if no patient found an error dialog is displayed.
            JOptionPane.showMessageDialog (null, "No matching patients", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
