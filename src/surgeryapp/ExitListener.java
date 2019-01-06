/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * method to exit the application when the actionListener is called.
 * @author Dan
 */
public class ExitListener implements ActionListener {

    public ExitListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    
}
