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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Dan
 */
public class DoctorPanel extends JPanel{
    
    private static final DoctorPanel INSTANCE = new DoctorPanel();
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
    private final JList doctorsSchedule = new JList();
    private final JList patientNotes = new JList();
    private final JTextArea noteField = new JTextArea();
    private final JList patientPrescriptions = new JList();
    private final JTextField medicineField = new JTextField();
    private final JTextField dosageField = new JTextField();
    private final JTextField quantityField = new JTextField();
    public static int scheduleIndex;
    public static String doctorsName;
    
    private DoctorPanel(){
        init();
    }
    
    public static DoctorPanel getInstance(){
         return INSTANCE;
    }
    
    private void init(){
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(headingPanel());
        this.add(docSchedule());
        this.add(patientSearchPanel());
        this.add(patientDetailsPanel());
        this.add(patientNotes());
        this.add(patientPrescriptions());
    }
    
    //create the panel heading.
    public JPanel headingPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(780, 50));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.setLayout(new BorderLayout());
        JLabel headingLabel = new JLabel(("Doctor Dashboard"),SwingConstants.CENTER);
        panel.add(headingLabel, BorderLayout.CENTER);
        return panel;
    }
    
    //create doctors schedule panel
    public JPanel docSchedule(){
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        
        JPanel panel2 = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(panel2, BoxLayout.X_AXIS);
        panel2.setLayout(boxLayout2);
        JScrollPane doctorsSchedulePane = new JScrollPane(doctorsSchedule);
        panel2.add(doctorsSchedulePane);
        doctorsSchedule.addMouseListener(new ScheduleSelectListener(doctorsSchedule));
        JButton closeAppBtn = new JButton("Close Appointment");
        panel2.add(closeAppBtn);
        closeAppBtn.addActionListener(new CloseAppClickListener(doctorsSchedule));
        
        panel.setLayout(boxLayout);
        panel.add(new JLabel("Your schedule"));
        panel.add(panel2);
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
        searchBtn.addActionListener(new DocSearchClickListener(searchField, nameField,
                phoneField, houseField, dobField, roadField, doctorField, townField, 
                genderField, postcodeField, appointmentList, doctorsSchedule, patientNotes, 
                patientPrescriptions));
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
    
    //Create JPanel holding patient notes
    public JPanel patientNotes(){
        //create containing panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Patient Notes"));
        panel.setLayout(new GridLayout(1,2));
        
        //create present notes panel
        JPanel panel2 = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxLayout);
        panel2.add(new JLabel("Excisting Notes:"));
        JScrollPane patientNotesPane = new JScrollPane(patientNotes);
        panel2.add(patientNotesPane);
        
        //create new note panel
        JPanel panel3 = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        panel3.setLayout(boxLayout2);
        panel3.add(new JLabel("New Note"));
        JScrollPane notePane = new JScrollPane(noteField);
        noteField.setWrapStyleWord(true);
        noteField.setLineWrap(true);
        panel3.add(notePane);
        JButton addNoteBtn = new JButton("Add Note");
        addNoteBtn.addActionListener(new addNoteClickListener(searchField, patientNotes, noteField));
        panel3.add(addNoteBtn);
                
        //add panels to containing panel
        panel.add(panel2);
        panel.add(panel3);
        return panel;
    }
    
    //Create JPanel holding patient prescriptions
    public JPanel patientPrescriptions(){
        //create containing JPanel
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(BorderFactory.createTitledBorder("Patient Prescriptions"));
        
        //create present prescriptions panel
        JPanel panel2 = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
        panel2.setLayout(boxLayout2);
        panel2.add(new JLabel("Excisting Prescriptions:"));
        JScrollPane patientPrescriptionsPane = new JScrollPane(patientPrescriptions);
        panel2.add(patientPrescriptionsPane);
        
        //create add prescription panel
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(4,2));
        panel3.add(new JLabel("Medicine: "));
        panel3.add(medicineField);
        panel3.add(new JLabel("Dosage: "));
        panel3.add(dosageField);
        panel3.add(new JLabel("Quantity: "));
        panel3.add(quantityField);
        JButton addPrescriptionBtn = new JButton("Create");
        addPrescriptionBtn.addActionListener(new addPrescClickListener(searchField, patientPrescriptions, medicineField, dosageField, quantityField));
        panel3.add(addPrescriptionBtn);
        
        panel.add(panel2);
        panel.add(panel3);
        return panel;
    }

    public void setSchedule(List<String> s){
        this.doctorsSchedule.setListData(s.toArray());
    }
    
}
