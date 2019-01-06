/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 * Mouse listener used in patientAppDetails method in class SecretaryPanel. When mouse click event
 * occurs a JList index value is received and used to populate appointment data in static JTextFields
 * in the SecretaryPanel class.
 * @author Dan
 */
public class AppSelectListener implements MouseListener{

    private final JTextField searchField;
    private final JList list;
    private final JTextField day;
    private final JTextField month;
    private final JTextField year;
    private final JTextField hours;
    private final JTextField minutes;
    private final JTextField symptom;
    private final JTextField appointmentNo;
    
    public AppSelectListener(JTextField searchField, JList list, JTextField day, JTextField month,
            JTextField year, JTextField hour, JTextField min, JTextField s, JTextField a){
        this.searchField = searchField;
        this.list = list;
         this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hour;
        this.minutes = min;
        this.symptom = s;
        this.appointmentNo = a;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        //find person
        Person p = members.getPatients().findMemberByName(searchField.getText());
        
        //make person a patient to access patient methods
        Patient patient = (Patient)p;
        
        /**
         * set appointment number with click index from jlist
         * this is required as other click listeners need this value.
         */
        appointmentNo.setText(Integer.toString(list.locationToIndex(e.getPoint())));
        
        //find correct appointment by received index
        Appointment a = patient.getAppointments().get(list.locationToIndex(e.getPoint()));
        
        //update fields with appointment information
        day.setText(Integer.toString(a.getDate().getDayOfMonth()));
        month.setText(Integer.toString(a.getDate().getMonthValue()));
        year.setText((Integer.toString(a.getDate().getYear())));
        hours.setText(Integer.toString(a.getDate().getHour()));
        minutes.setText(Integer.toString(a.getDate().getMinute()));
        symptom.setText(a.getSympton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
