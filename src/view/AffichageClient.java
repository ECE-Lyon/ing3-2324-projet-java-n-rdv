package view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import controller.AffichageClientController;
import model.Client;
import model.MySql;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/** VOIR POUR LES COMBOBOXS , QUAND TU SELECTIONNES UN MEDECIN, TU AURAS DIRECTEMENT QUE LES CLINIQUES OU IL BOSSE, ET INVERSEMENT*/

public class AffichageClient extends JFrame implements ActionListener, DateChangeListener {
    private AffichageClientController controller ;
    private JPanel panel;
    private JTabbedPane tabbedPane1;

    //Onglet recherche
    private JComboBox comboBoxMedecin;
    private JComboBox comboBoxClinique;
    private JPanel scrollPanel;
    private JButton rechercherButton;
    private DatePicker datePicker;

    private JButton prendreCeRendezVousButton;
    private JButton voirNoteButton;
    private JButton voirNoteButton1;
    private JButton voirNoteButton2;
    private JButton voirNoteButton3;

    public AffichageClient(AffichageClientController control){
        super("Page Client") ;
        this.controller = control ;
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        List<String> list = new ArrayList<>() ;
        list = this.controller.getAllClinique(MySql.getConnection()) ;
        list.add(0, "-----") ;
        for(int i = 0 ; i < list.size() ; i++){
            this.comboBoxClinique.addItem(list.get(i));
        }
        list = this.controller.getAllMedecin(MySql.getConnection()) ;
        list.add(0, "-----") ;
        for(int i = 0 ; i < list.size() ; i++){
            this.comboBoxMedecin.addItem(list.get(i));
        }
        this.pack();
        this.setVisible(true);
    }



    public void validerRechercheGestionClient(){
        this.scrollPanel.removeAll();
        int nbNull = 0;
        String nomMedecin = (String)comboBoxMedecin.getSelectedItem(), prenom = (String)comboBoxClinique.getSelectedItem() ;
        if(cutString(nomMedecin).isEmpty()){
            nomMedecin = null ;
            nbNull++ ;
        }
        if(cutString(prenom).isEmpty()){
            prenom = null ;
            nbNull++ ;
        }
    }

    public String cutString(String mot){
        return mot.replace(" ", "") ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.rechercherButton)){

        }
    }

    @Override
    public void dateChanged(DateChangeEvent dateChangeEvent) {
        LocalDate today = LocalDate.now();
        if (this.datePicker.getDate().isBefore(today)) {
            this.datePicker.setDate(today);

        }
                /*
                String pattern = "yyyy-MM-dd";
                DateTimeFormatter  df =  DateTimeFormatter.ofPattern(pattern) ;
                System.out.println(datePicker.getDate().format(df));
                */
    }
}
