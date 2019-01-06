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
 *
 * @author Dan
 */
public class SecretaryPanel extends JPanel{
    
    private static final SecretaryPanel INSTANCE = new SecretaryPanel();
    private final JTextField searchField = new JTextField();
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
    private final JTextField dayField = new JTextField();
    private final JTextField monthField = new JTextField();
    private final JTextField yearField = new JTextField();
    private final JTextField hoursField = new JTextField();
    private final JTextField minutesField = new JTextField();
    private final JTextField symptomField = new JTextField();
    private final JList doctorsSchedule = new JList();
            
    /*
    * Private constructor due to singleton pattern, calls init(initiialisation) method.
    */
    private SecretaryPanel(){
        init();
    }
    
    /*
    * Initialisation method for SecretaryPanel, used to call sperate JPanel methods 
    * to create the complete SecretaryPanel layout.
    */
    private void init(){
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(headingPanel());
        this.add(patientSearchPanel());
        this.add(patientDetailsPanel());
        //this.add(updateDetailsPanel());
        this.add(patientsAppPanel());
        this.add(updateAppPanel());
        this.add(printReports());
    }
    
    public static SecretaryPanel getInstance(){
        return INSTANCE;
    }
    
    //create the panel heading.
    public JPanel headingPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 50));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.setLayout(new BorderLayout());
        JLabel headingLabel = new JLabel(("Admin Dashboard"),SwingConstants.CENTER);
        panel.add(headingLabel, BorderLayout.CENTER);
        return panel;
    }
    
    //create the patient search panel. JTextField to enter the search string, JButton to call the click listener.
    public JPanel patientSearchPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Patient Search"));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Enter patient name: "));
        searchField.setColumns(20);
        panel.add(searchField);
        JButton searchBtn = new JButton("Search");
        panel.add(searchBtn);
        searchBtn.addActionListener(new SearchClickListener(searchField, nameField,
                phoneField, houseField, dobField, roadField, doctorField, townField,
                genderField, postcodeField, appointmentList, doctorsSchedule));
        JButton prtPatientsRptBtn = new JButton("Print Report");
        panel.add(prtPatientsRptBtn);
        prtPatientsRptBtn.addActionListener(new prtPatientClickListener(searchField));
        return panel;
    }
    
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
    
    //create the update patient details panel, contains only one button.
    //currently not implemented
    /* public JPanel updateDetailsPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Update Patient Details"));
        panel.add(new JLabel("To update the patient's details, change the values above and click 'Update Details'"));
        JButton updateDetailsBtn = new JButton("Update Details");
        panel.add(updateDetailsBtn);
        return panel;        
    }*/
    
    /*
    * create patients appointments panel. Contains Jlist populated from SearchClickListener method used in
    * patientSearchPanel. Mouse listener called to populate appDetailsPanel on JList selection.
    */
    public JPanel patientsAppPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 200));
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(BorderFactory.createTitledBorder("Patients Appointments"));

        
        //seperate panel to display the selected appointment and number
        JPanel panel2 = new JPanel();
        JScrollPane appPane = new JScrollPane(appointmentList);
        appointmentList.setToolTipText("Select an appointment to make changes");
        appointmentList.addMouseListener(new AppSelectListener(searchField, appointmentList, 
        dayField, monthField, yearField, hoursField, minutesField, symptomField, appointmentNo));
        panel2.add(appPane);
        panel2.add(new JLabel("Appointment selected: "));
        appointmentNo.setColumns(3);
        panel2.add(appointmentNo);
        
        panel.add(panel2);
        panel.add(appDetailsPanel());
        return panel;
    }
    
    /*
    * create appointment details panel, used to show editable details of a selected appointment.
    * contains multiple panels to create layout. Three JButtons allow cancelling, changing and 
    * creating of appointments
    */
    public JPanel appDetailsPanel(){
        //heading
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.add(new JLabel("Enter details below to create/change appointment"));
        
        //seperate panel to format appointment date layout
        JPanel appDatePanel = new JPanel();
        appDatePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        appDatePanel.add(new JLabel("Date and Time: "));
        appDatePanel.add(new JLabel("D:"));
        dayField.setColumns(2);
        dayField.setToolTipText("Enter the day in dd format");
        appDatePanel.add(dayField);
        appDatePanel.add(new JLabel("M:"));
        monthField.setColumns(2);
        monthField.setToolTipText("Enter the month in mm format");
        appDatePanel.add(monthField);
        appDatePanel.add(new JLabel("Y:"));
        yearField.setColumns(4);
        yearField.setToolTipText("Enter the year in yyyy format");
        appDatePanel.add(yearField);
        appDatePanel.add(new JLabel("H:"));
        hoursField.setColumns(2);
        hoursField.setToolTipText("Enter the hours in hh format");
        appDatePanel.add(hoursField);
        appDatePanel.add(new JLabel("M:"));
        minutesField.setColumns(2);
        minutesField.setToolTipText("Enther the minutes in mm format");
        appDatePanel.add(minutesField);
        
        //seperate panel to format symptom layout
        JPanel symptomPanel = new JPanel();
        symptomPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        symptomPanel.add(new JLabel("Symptom: "));
        symptomPanel.add(symptomField);
        symptomField.setColumns(30);
        
        //appointment details JTextFields
        JPanel panel2 = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxLayout2);
        panel2.add(appDatePanel);
        panel2.add(symptomPanel);
                
        //doctors schedule panel
        JPanel panel3 = new JPanel();
        BoxLayout boxLayout3 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        panel3.setLayout(boxLayout3);
        panel3.add(new JLabel("Schedule for allocated doctor: "));
        JScrollPane doctorsSchedulePane = new JScrollPane(doctorsSchedule);
        panel3.add(doctorsSchedulePane);
        
        panel.add(panel2);
        panel.add(panel3);
        return panel;        
    }
    
    public JPanel updateAppPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Update Appointments"));
        panel.add(new JLabel("To update, cancel or create appointments, please enter the "
                + "details above and select the correct option"),BorderLayout.NORTH);
        
        //appointment options buttons
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JButton cancelAppBtn = new JButton("Cancel Appointment");
        panel2.add(cancelAppBtn);
        cancelAppBtn.addActionListener(new CancelAppClickListener(searchField, 
                appointmentList, appointmentNo, doctorsSchedule));
        JButton updateAppBtn = new JButton("Update Appointment");     
        panel2.add(updateAppBtn);
        updateAppBtn.addActionListener(new UpdateAppClickListener(searchField, 
                dayField, monthField, yearField, hoursField, minutesField, symptomField, 
                appointmentList, doctorsSchedule, appointmentNo));
        JButton createAppBtn = new JButton("Create Appointment");
        panel2.add(createAppBtn);
        createAppBtn.addActionListener(new CreateAppClickListener(searchField, 
                dayField, monthField, yearField, hoursField, minutesField, symptomField, 
                appointmentList, doctorsSchedule));
        
        //add panel2
        panel.add(panel2, BorderLayout.SOUTH);
        return panel;
    }
    
    //report printing buttons.
    public JPanel printReports(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Print Reports"));
        JButton prtDocRpt = new JButton("Doctor Report");
        prtDocRpt.addActionListener(new PrtDocListener());
        panel.add(prtDocRpt);
        JButton prtAppRpt = new JButton("Appointment Report");
        prtAppRpt.addActionListener(new PrtAppListener());
        panel.add(prtAppRpt);
        JButton prtMedRpt = new JButton("Medication Report");
        prtMedRpt.addActionListener(new PrtMedListener());
        panel.add(prtMedRpt);
        return panel;
    }
}
