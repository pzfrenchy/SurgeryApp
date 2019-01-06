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
public class CheckInClickListener implements ActionListener {

    private final JTextField name;
    private final JTextField appointmentNo;
    private final JList appointmentList;
    
    public CheckInClickListener(JTextField n, JTextField appNo, JList aList) {
        this.name = n;
        this.appointmentNo = appNo;
        this.appointmentList = aList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //Get instance of Members to access Members Lists
            Members members = Members.getInstance();
            
            //find person
            Person p = members.getPatients().findMemberByName(name.getText());

            //make person a patient to access patient methods
            Patient patient = (Patient)p;

            //find appointment by index value
            Appointment a = patient.getAppointments().get(Integer.parseInt(appointmentNo.getText()));
            
            //check that the appointment isn't cancelled
            if (a.getStatus()==AppointmentStatus.CANCELLED){
                //display error message
                JOptionPane.showMessageDialog (null, "You cannot check in a cancelled appointment - try again", "Program Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //change appointment status
                a.setStatus(AppointmentStatus.ACTIVE);
            }
            
            //update appointment list with new data(appointment should now show as cancelled)
            appointmentList.setListData(patient.getFormattedAppointments().toArray());
        }
        catch (Exception z){
            //if no appointment selected an error dialog is displayed.
            JOptionPane.showMessageDialog (null, "No appointment selected", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
