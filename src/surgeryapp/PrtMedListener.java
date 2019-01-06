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

/**
 *
 * @author Dan
 */
public class PrtMedListener implements ActionListener, Printable {

    public PrtMedListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        
        //get instance of reports
        Reports reports = Reports.getInstance(); 
        
        //create new Graphics2D(used to control page layout based on coordinates)
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
 
        // render the document
        g.drawString("Medicine Prescribed", 100, 100);
        //iterate through the list and print each string
        for (int i = 0; i < reports.getAllPrescriptions().size(); i++) {
            String rep = reports.getAllPrescriptions().get(i);
            int j = 120; //set top margin
            g.drawString(rep, 100, j+i*20);
        }
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;    
    }
    
}
