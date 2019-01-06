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
public class addPrescClickListener implements ActionListener {

    private final JTextField searchField;
    private final JList patientPrescriptions;
    private final JTextField medicine;
    private final JTextField dosage;
    private final JTextField quantity;
    
    public addPrescClickListener(JTextField s, JList p, JTextField m, JTextField d, JTextField q) {
        this.searchField = s;
        this.patientPrescriptions = p;
        this.medicine = m;
        this.dosage = d;
        this.quantity = q;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //check that a patient has been selected.
        if (searchField==null){
            //if no patient is selected an error dialog is displayed.
            JOptionPane.showMessageDialog (null, "No patient selected", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if (medicine.getText().isEmpty()){
                //if no medicine is found an error dialog is displayed.
                JOptionPane.showMessageDialog (null, "No medicine entered", "Program Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    //Get instance of Members to access Members Lists
                    Members members = Members.getInstance();
                    
                    //find person
                    Person p = members.getPatients().findMemberByName(searchField.getText());

                    //make person a patient to access patient methods
                    Patient patient = (Patient)p;

                    //create the new note and add it to the patient.
                    Prescription pres = new Prescription(medicine.getText(), dosage.getText(), Integer.parseInt(quantity.getText()));
                    patient.addPrescription(pres);

                    //refresh the note JPanel
                    patientPrescriptions.setListData(patient.getFormattedPrescription().toArray());
                    
                    //clear prescription details
                    medicine.setText(null);
                    quantity.setText(null);
                    dosage.setText(null);
                    }
                catch (Exception a){
                    //if data can not be added
                    JOptionPane.showMessageDialog (null, "Please ensure all boxes are corectly filled in", "Program Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
}
