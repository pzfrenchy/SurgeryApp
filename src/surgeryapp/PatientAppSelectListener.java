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
 *
 * @author Dan
 */
public class PatientAppSelectListener implements MouseListener {

    private final JList list;
    private final JTextField appointmentNo;
    
    public PatientAppSelectListener(JList list, JTextField aNum) {
        this.list = list;
        this.appointmentNo = aNum;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //get click index from jlist
        int index = list.locationToIndex(e.getPoint());

        //update fields with appointment information
        appointmentNo.setText(Integer.toString(index));
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
