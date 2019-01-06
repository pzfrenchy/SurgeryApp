/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Click listener to change viewstate value in the main class based on a received JTextField value.
 * This allows the correct JPanel to be displayed based on the type of user entered in the JTextField.
 * @author Dan
 */
public class LoginClickListener implements ActionListener{
    
    private JTextField name = new JTextField();
    
    public LoginClickListener(JTextField name){
        super();
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        if (members.getPatients().doesMemberExist(name.getText().toUpperCase())==true){
            //launch patient GUI
            MainJFrame.changeState(ViewState.PATIENT);
            
            //find person
            Person p = members.getPatients().findMemberByName(name.getText());
            
            //make person a patient to access patient methods
            Patient patient = (Patient)p;
            
            //Get instance of patient panel
            PatientPanel patientPanel = PatientPanel.getInstance();
            
            //update fields with patient information
            patientPanel.setNameField(patient.getName());
            patientPanel.setAppointmentList(patient.getFormattedAppointments());
            patientPanel.setDobField(patient.getFormattedDate());
            patientPanel.setDoctorField(patient.getDoctor().getName());
            patientPanel.setGenderField(patient.getGenderAsString());
            patientPanel.setHouseField(patient.getContact().getHouse());
            patientPanel.setPhoneField(patient.getContact().getPhoneNum());
            patientPanel.setPostcodeField(patient.getContact().getPostcode());
            patientPanel.setRoadField(patient.getContact().getRoad());
            patientPanel.setTownField(patient.getContact().getTown());
        }
        else if (members.getDoctors().doesMemberExist(name.getText().toUpperCase())==true){
            //launch doctor GUI
            MainJFrame.changeState(ViewState.DOCTOR);
            
            //find person
            Person d = members.getDoctors().findMemberByName(name.getText());
            
            //make person a patient to access patient methods
            Doctor doctor = (Doctor)d;
            
            //Get instance of Doctors panel
            DoctorPanel doctorPanel = DoctorPanel.getInstance();
            
            //populate doctors schedule on log in
            doctorPanel.setSchedule(doctor.getFormattedSchedule());
            
            //set the doctors name to a string, used to maintain which doctor is logged in
            DoctorPanel.doctorsName = name.getText();

        }
        else if (members.getSecretaries().doesMemberExist(name.getText().toUpperCase())==true){
            //Launch Secretary GUI
            MainJFrame.changeState(ViewState.SECRETARY);
        }
        else if (members.getPharmacists().doesMemberExist(name.getText().toUpperCase())==true){
            //Launch Pharmacist GUI
            MainJFrame.changeState(ViewState.PHARMACIST);
        }        
        else{
            //if no patient matches, return error dialog box.
            JOptionPane.showMessageDialog (null, "Incorrect username entered", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //Get instance of login panel
        LoginPanel loginPanel = LoginPanel.getInstance();
        //Clear the text box
        loginPanel.clearLoginButton();
    }
}
