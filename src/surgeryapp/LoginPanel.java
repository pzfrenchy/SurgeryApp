/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Login JPanel to display the user login interface, single JButton with associated 
 * click listener to check the user type and change viewState.
 * @author Dan
 */
public class LoginPanel extends JPanel{
    
    private static final LoginPanel INSTANCE = new LoginPanel();
    private JTextField userName;
    
    private LoginPanel(){
        init();
    }
    
    private void init(){
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.add(headingPanel());
        this.add(makeLoginPanel());
    }
    
    public static LoginPanel getInstance(){
        return INSTANCE;
    }
    
    public JPanel headingPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(780, 50));
        panel.setBorder(BorderFactory.createTitledBorder(""));
        panel.setLayout(new BorderLayout());
        JLabel headingLabel = new JLabel(("Surgery App - Welcome"),SwingConstants.CENTER);
        panel.add(headingLabel, BorderLayout.CENTER);
        return panel;    
    }
    
    public JPanel makeLoginPanel(){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Login"));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add((new JLabel("Name: ")));
        userName = new JTextField();
        userName.setColumns(20);
        panel.add((userName));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginClickListener(userName));
        panel.add(loginButton);
        return panel;
    }
    
    public void clearLoginButton(){
        this.userName.setText("");
    }
}
