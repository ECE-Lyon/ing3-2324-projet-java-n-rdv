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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        this.scrollPanel.setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("Faire une recherche", JLabel.CENTER) ;
        label.setForeground(new Color(130, 145, 132, 255));
        this.scrollPanel.add(label) ;

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
        String nomMedecin = (String)comboBoxMedecin.getSelectedItem(), nomClinique = (String)comboBoxClinique.getSelectedItem() ;
        if(nomMedecin.equals("-----")){
            nomMedecin = null ;
        }
        if(nomClinique.equals("-----")){
            nomClinique = null ;
        }
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter  df =  DateTimeFormatter.ofPattern(pattern) ;
        Date parsedDate = new Date(this.datePicker.getDate().format(df)) ;
        Timestamp date = new Timestamp(parsedDate.getTime());
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
                String pattern = "yyyy-MM-dd";
                DateTimeFormatter  df =  DateTimeFormatter.ofPattern(pattern) ;
                System.out.println(datePicker.getDate().format(df));

    }
}
