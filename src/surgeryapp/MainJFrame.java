/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Main JFrame class to create application GUI. Single instance of JFrame created and
 * multiple JPanels used to display correct information to the user type.
 * 
 * An enum holds possible states and a changeState method is used to change the JPanel 
 * based on the selected state(enum).
 * LoginPanel is the initial JPanel and states can be changed based on the user type entered
 * in the login JTextField.
 * @author Dan
 */
public class MainJFrame extends JFrame{
    
    private static ViewState viewState;
    private static JPanel mpanel;
    private static JPanel loginPanel;
    private static JPanel secretaryPanel;
    private static PatientPanel patientPanel;
    private static JPanel doctorPanel;
    private static JPanel pharmacistPanel;
    
    public MainJFrame(){
        super();
        init();
    }
    
    public MainJFrame(String title){
        super(title);
        init();
    }
    
    private void init(){
        //Set JFrame name, exit button and size
        this.setTitle("Surgery Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(820, 810));
        
        //create and add menu bar
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        
        //create file menu
        JMenu fileMenu = new JMenu ("File");
        menu.add(fileMenu);
        
        //Create logout menu item
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(new LogoutListener());
        fileMenu.add(logoutItem);
        
        //Create exit program menu item
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ExitListener());
        fileMenu.add(exitItem);
        
        /**
         * create JPanels that will be used in the application,
         * mpanel is the filler panel on load, no content required.
         */
        mpanel = new JPanel();
        secretaryPanel = SecretaryPanel.getInstance();
        patientPanel = PatientPanel.getInstance();
        loginPanel = LoginPanel.getInstance();
        doctorPanel = DoctorPanel.getInstance();
        pharmacistPanel = PharmacistPanel.getInstance();
        
        //set the initial state which will display the loginPanel
        changeState(ViewState.INITIAL_STATE);
        
        //add filler JPanel and frame options
        this.add(mpanel);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }
      
    /**
     * method for changing the JPanel on display in the JFrame based on the viewState field
     * values are defined in the ViewState enum and changed in the LoginPanel based on the type
     * of user entered (patient, doctor, secretary and pharmacist).
     * 
     * @param state the enum ViewState value. 
     */
    public static void changeState(ViewState state){
        viewState = state;
        
        switch (state){
            case INITIAL_STATE:
                mpanel.removeAll();
                mpanel.add(loginPanel);
                mpanel.revalidate();
                mpanel.repaint();
                break;
            case SECRETARY:
                mpanel.removeAll();
                mpanel.add(secretaryPanel);
                mpanel.revalidate();
                mpanel.repaint();
                break; 
            case DOCTOR:
                mpanel.removeAll();
                mpanel.add(doctorPanel);
                mpanel.revalidate();
                mpanel.repaint();
                break;                 
            case PATIENT:
                mpanel.removeAll();
                mpanel.add(patientPanel);
                mpanel.revalidate();
                mpanel.repaint();
                break;                 
            case PHARMACIST:
                mpanel.removeAll();
                mpanel.add(pharmacistPanel);
                mpanel.revalidate();
                mpanel.repaint();
                break;                 
        }
    }
}
