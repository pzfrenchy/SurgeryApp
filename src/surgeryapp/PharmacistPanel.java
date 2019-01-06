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
public class PharmacistPanel extends JPanel{
    
    private static final PharmacistPanel INSTANCE = new PharmacistPanel();
    private final JTextField searchField = new JTextField();
    private final JList prescriptionsList = new JList();
    private final JTextField nameField = new JTextField();
    private final JTextField phoneField = new JTextField();
    private final JTextField houseField = new JTextField();
    private final JTextField dobField = new JTextField();
    private final JTextField roadField = new JTextField();
    private final JTextField doctorField = new JTextField();
    private final JTextField townField = new JTextField();
    private final JTextField genderField = new JTextField();
    private final JTextField postcodeField = new JTextField(); 
    
    private PharmacistPanel(){
        init();
    }
    
    private void init(){
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(headingPanel());
        this.add(patientSearchPanel());
        this.add(patientDetailsPanel());
        this.add(prescriptionPanel());
    }
    
    public static PharmacistPanel getInstance(){
        return INSTANCE;
    }
    
    //create the panel heading.
    public JPanel headingPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(780, 50));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.setLayout(new BorderLayout());
        JLabel headingLabel = new JLabel(("Pharmacist Dashboard"),SwingConstants.CENTER);
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
        searchBtn.addActionListener(new PharmSearchClickListener(searchField, nameField,
                phoneField, houseField, dobField, roadField, doctorField, townField,
                genderField, postcodeField, prescriptionsList));
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
    
        public JPanel prescriptionPanel(){
        JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(500, 200));
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        panel.setBorder(BorderFactory.createTitledBorder("Patients Prescriptions"));
        JScrollPane appPane = new JScrollPane(prescriptionsList);
        panel.add(appPane);
        return panel;
    }
}
