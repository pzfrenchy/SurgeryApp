/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Dan
 */
public class UpdateAppClickListener implements ActionListener{
    
    private final JTextField searchField;
    private final JTextField day;
    private final JTextField month;
    private final JTextField year;
    private final JTextField hour;
    private final JTextField min; 
    private final JTextField symptom;
    private final JList appList;
    private final JList docList;
    private final JTextField index;
    
    public UpdateAppClickListener(JTextField searchField, JTextField d,
            JTextField month, JTextField y, JTextField h, JTextField min, 
            JTextField symptom, JList appList, JList docList, JTextField index){
        this.searchField = searchField;
        this.appList = appList;
        this.day = d;
        this.docList = docList;
        this.hour = h;
        this.min = min;
        this.month = month;
        this.symptom = symptom;
        this.year = y;
        this.index = index;
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

            //find appointment by index value
            Appointment a = patient.getAppointments().get(Integer.parseInt(index.getText()));

            //capture date and convert to LocalDateTime format
            LocalDateTime date = LocalDateTime.of(Integer.parseInt(year.getText()),
                    Integer.parseInt(month.getText()),
                    Integer.parseInt(day.getText()),
                    Integer.parseInt(hour.getText()),
                    Integer.parseInt(min.getText()));
            
            //change doctors schedule details
            Schedule s = (doctor.getScheduleByDate(a.getDate()));
            s.setDate(date);

            //change appointment details
            a.setDate(date);
            a.setSymptom(symptom.getText());

            //update appointment list with new data(appointment should now show as cancelled)
            appList.setListData(patient.getFormattedAppointments().toArray());

            //update doctors schedule with new information
            docList.setListData(doctor.getFormattedSchedule().toArray());
            
        }
        catch (Exception a){
            JOptionPane.showMessageDialog (null, "No matching date in doctors schedule", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
