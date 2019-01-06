/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;

/**
 *
 * @author Dan
 */
public class ScheduleSelectListener implements MouseListener {

    private JList schedule = new JList();
    
    public ScheduleSelectListener(JList s) {
        this.schedule = s;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DoctorPanel.scheduleIndex = schedule.locationToIndex(e.getPoint());
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
