/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surgeryapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Dan
 */
public class addNoteClickListener implements ActionListener {

    private final JTextField searchField;
    private final JList patientNotes;
    private final JTextArea note;
    
    public addNoteClickListener(JTextField s, JList pNotes, JTextArea note) {
        this.searchField = s;
        this.patientNotes = pNotes;
        this.note = note;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get instance of Members to access Members Lists
        Members members = Members.getInstance();
        
        //check that a patient has been selected.
        if (searchField==null){
            //if no patient found, error dialog displayed.
            JOptionPane.showMessageDialog (null, "No patient selected", "Program Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if (note.getText().isEmpty()) {
                //if no note content found, an error dialog is displayed.
                JOptionPane.showMessageDialog (null, "No note information entered", "Program Error", JOptionPane.ERROR_MESSAGE);
            }
            else{ 
                //find person
                Person p = members.getPatients().findMemberByName(searchField.getText());

                //make person a patient to access patient methods
                Patient patient = (Patient)p;

                //create the new note and add it to the patient.
                Note n = new Note(note.getText());
                patient.addNote(n);

                //refresh the note JPanel
                patientNotes.setListData(patient.getFormattedNotes().toArray());
                
                //Clear the note
                note.setText(null);
            }
        }
    }   
}
