/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * JPanel class to create the patient interface. Allows patients to view their details
 * and check in for appointments.
 * @author Dan
 */
public class PatientPanel extends JPanel{
    
    private static final PatientPanel INSTANCE = new PatientPanel();
    private final JTextField nameField = new JTextField();
    private final JTextField phoneField = new JTextField();
    private final JTextField houseField = new JTextField();
    private final JTextField dobField = new JTextField();
    private final JTextField roadField = new JTextField();
    private final JTextField doctorField = new JTextField();
    private final JTextField townField = new JTextField();
    private final JTextField genderField = new JTextField();
    private final JTextField postcodeField = new JTextField(); 
    private final JList appointmentList = new JList();
    private final JTextField appointmentNo = new JTextField();
    
    private PatientPanel(){
        init();
    }
    
    //initialisation called in constructor, creates patient interface.
    private void init(){
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(headingPanel());
        this.add(patientDetailsPanel());
        this.add(patientsAppPanel());
        this.add(checkInPanel());
    }
    
    public static PatientPanel getInstance(){
        return INSTANCE;
    }
    
    //create the panel heading.
    public JPanel headingPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(780, 50));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.setLayout(new BorderLayout());
        JLabel headingLabel = new JLabel(("Patient Dashboard"),SwingConstants.CENTER);
        panel.add(headingLabel, BorderLayout.CENTER);
        return panel;
    }
    
    /*Not needed at present may implement in future.
    //create the patient print report panel.
    public JPanel patientPrtPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Print Report"));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton prtPatientsRptBtn = new JButton("Print Report");
        panel.add(prtPatientsRptBtn);
        //searchBtn.addActionListener(new prtPatientRptClickListener(searchField));
        return panel;
    }*/
    
    //create the patient details panel. Contains multiple static JTextFields and JLabels
    public JPanel patientDetailsPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Patient Details"));  
        panel.setLayout(new GridLayout(5,4, 40, 0));
        panel.add(new JLabel("Name: "));
        panel.add(nameField);
        panel.add(new JLabel("Tel No: "));
        panel.add(phoneField);
        panel.add(new JLabel("Address: "));
        panel.add(houseField);
        panel.add(new JLabel("Date of Birth: "));
        panel.add(dobField);
        panel.add(new JLabel(""));
        panel.add(roadField);
        panel.add(new JLabel("Doctor: "));
        panel.add(doctorField);
        panel.add(new JLabel(""));
        panel.add(townField);
        panel.add(new JLabel("Gender"));
        panel.add(genderField);
        panel.add(new JLabel(""));
        panel.add(postcodeField);
        return panel;
    }
    
    public JPanel appNoPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Appointment selected: "));
        panel.add(appointmentNo);
        appointmentNo.setColumns(2);
        return panel;
    }
    public JPanel patientsAppPanel(){
        JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(500, 200));
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(BorderFactory.createTitledBorder("Patients Appointments"));
        JScrollPane appPane = new JScrollPane(appointmentList);
        appointmentList.addMouseListener(new PatientAppSelectListener(appointmentList, appointmentNo));
        panel.add(appPane);
        panel.add(appNoPanel());
        return panel;
    }
    
    public JPanel checkInPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Select the correct appointment and click check in"));
        JButton checkIn = new JButton("Check In");
        checkIn.addActionListener(new CheckInClickListener(nameField, appointmentNo, appointmentList));
        panel.add(checkIn);
        return panel;
    }
    
    //set methods to set JTextFields on the JPanel
    public void setNameField(String n){
        this.nameField.setText(n);
    }
    
    public void setDobField(String d){
        this.dobField.setText(d);
    }
        
    public void setHouseField(String h){
        this.houseField.setText(h);
    }
    
    public void setRoadField(String r){
        this.roadField.setText(r);
    }
    
    public void setTownField(String t){
        this.townField.setText(t);
    }
    
    public void setPostcodeField(String p){
        this.postcodeField.setText(p);
    }
    
    public void setDoctorField(String d){
        this.doctorField.setText(d);
    }
    
    public void setPhoneField(String p){
        this.phoneField.setText(p);
    }
    
    public void setGenderField(String g){
        this.genderField.setText(g);
    }
    
    public void setAppointmentList(List<String> l){
        this.appointmentList.setListData(l.toArray());
    }
}
