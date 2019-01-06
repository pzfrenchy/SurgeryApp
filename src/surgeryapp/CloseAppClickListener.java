/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author Dan
 */
public class CloseAppClickListener implements ActionListener {

    private JList schedule = new JList();
    
    public CloseAppClickListener(JList s) {
        this.schedule = s;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            //Get instance of Members to access Members Lists
            Members members = Members.getInstance();

            //find doctor
            Person d = members.getDoctors().findMemberByName(DoctorPanel.doctorsName);

            //make (person)doctor a doctor to access doctor methods
            Doctor doctor = (Doctor)d;
            
            //get doctors schedule
            Schedule scheduleItem = doctor.getSchedule().get(DoctorPanel.scheduleIndex);
            
            //get patient from schedule
            Person p = scheduleItem.getPatient();
            
            //make (person)patient and Patient to access patient methods
            Patient patient = (Patient)p;
            
            //get the correct appointment by date
            Appointment a = patient.getAppointmentByDate(scheduleItem.getDate());
            
            //update appointment
            a.setStatus(AppointmentStatus.COMPLETE);
            
            //update doctors schedule with new information
            schedule.setListData(doctor.getFormattedSchedule().toArray());
    }
    
}
