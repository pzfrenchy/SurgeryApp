/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Dan
 */
public class LogoutListener implements ActionListener {

    public LogoutListener() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainJFrame.changeState(ViewState.INITIAL_STATE);
    }
    
}
