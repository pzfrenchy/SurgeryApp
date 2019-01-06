/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Dan
 */
public class prtPatientClickListener implements ActionListener, Printable {

    private final JTextField name;
    
    public prtPatientClickListener(JTextField name) {
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (name.getText().isEmpty()){
            //if no patient found an error dialog is displayed.
            JOptionPane.showMessageDialog (null, "No matching patients", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            boolean ok = job.printDialog();
            if (ok) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                /* The job did not successfully complete */
                }
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        //Get instance of printReports to access print methods
        Reports reports = Reports.getInstance(); 
        
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        //find person
        Person p = members.getPatients().findMemberByName(name.getText());

        //make person a patient to access patient methods
        Patient patient = (Patient)p;
        
        //create new Graphics2D(used to control page layout based on coordinates)
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
 
        // render the document
        g.drawString("Patient Report", 100, 100);
        for (int i = 0; i < reports.getPatientRecord(patient).size(); i++) {
            int j = 120; //set top margin
            String line = reports.getPatientRecord(patient).get(i);
            g.drawString(line, 100, j+i*20); 
        }
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
    
}
