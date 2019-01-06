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
 *
 * @author Dan
 */
public class PharmSearchClickListener implements ActionListener {

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
    private final JList prescriptionsList;
    
    public PharmSearchClickListener(JTextField s, JTextField n, JTextField p, JTextField h, JTextField dob,
            JTextField r, JTextField doc, JTextField t, JTextField g, JTextField postcode, JList pList){
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
        this.prescriptionsList = pList;
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
            prescriptionsList.setListData(patient.getFormattedPrescription().toArray());
        }
        catch(Exception a){
            //if no patient found an error dialog is displayed.
            JOptionPane.showMessageDialog (null, "No matching patients", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}