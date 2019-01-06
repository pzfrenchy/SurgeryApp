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
 * click listener used to change the status of a patients appointment to cancelled.
 * JTextField is passed to get the patients name and associated appointment list, static index 
 * field from SecretaryPanel will give the appointment index of the required appointment.
 * @author Dan
 */
public class CancelAppClickListener implements ActionListener {
    
    private final JTextField searchField;
    private final JList appointmentList;
    private final JList docList;
    private final JTextField index;
    
    public CancelAppClickListener(JTextField searchField, JList appointmentList, JTextField index, JList docList) {
        this.searchField = searchField;
        this.appointmentList = appointmentList;
        this.index = index;
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
            
            //find appointment by index value
            Appointment a = patient.getAppointments().get(Integer.parseInt(index.getText()));

            //change appointment status
            a.setStatus(AppointmentStatus.CANCELLED);

            //update appointment list with new data(appointment should now show as cancelled)
            appointmentList.setListData(patient.getFormattedAppointments().toArray());
            
            //find patients doctor
            Person d = patient.getDoctor();

            //make (person)doctor a doctor to access doctor methods
            Doctor doctor = (Doctor)d;
            
            //update doctors schedule with new information
            docList.setListData(doctor.getFormattedSchedule().toArray());
        }
        catch(Exception z){
            //if no patient found or no appointment selected an error dialog is displayed.
            JOptionPane.showMessageDialog (null, "No matching patients or appointment selected", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
