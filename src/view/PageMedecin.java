package view;

import controller.AffichageMedecinController;
import model.Medecin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class PageMedecin extends JFrame implements ActionListener {
    private AffichageMedecinController controller;


    //FENETRE TOTAL
    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    //GESTION RDV
    private JCheckBox Libre;
    private JCheckBox Réservé;
    private JCheckBox Archivé;
    private JComboBox comboBox1;
    private JRadioButton tousLesRendezVousRadioButton;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JButton validerButton;
    private JFormattedTextField formattedTextField3;
    private JFormattedTextField formattedTextField4;
    private JFormattedTextField formattedTextField5;
    private JButton validerButton1;
    private JButton voirDossierClientButton;
    private JButton historiqueDuClientButton;
    private JButton voirDossierClientButton1;
    private JButton historiqueDuClientButton1;
    private JFormattedTextField mdpMed;
    private JFormattedTextField speMed;
    private JButton validerAjouterMedecin;
    private JPanel panel2;
    private JPanel JPanel4;
    private JLabel Spe;

    //AJOUTER MEDECIN
    private JPanel panelClinique;
    private JComboBox comboBox2;
    private JButton ajouterUneAutreCliniqueButton;
    private JFormattedTextField EntrerQualification;
    private JFormattedTextField nomMed;
    private JFormattedTextField prenomMed;
    private JFormattedTextField mailMed;
    private JPanel southPanel;
    private JLabel resultatAjout;

    public PageMedecin(AffichageMedecinController control)  {
        super("Page Médecin") ;
        this.controller = control ;

        panelClinique.setLayout(new GridLayout(0, 1));

        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LES SEULS UTILES POUR L'INSTANT
        this.Libre.addActionListener(this);
        this.Réservé.addActionListener(this);
        this.Archivé.addActionListener(this);

        //ET LUI
        this.ajouterUneAutreCliniqueButton.addActionListener(this);
        this.validerAjouterMedecin.addActionListener(this);

        this.comboBox1.addActionListener(this);
        this.tousLesRendezVousRadioButton.addActionListener(this);
        this.validerButton.addActionListener(this);
        this.validerButton1.addActionListener(this);
        this.voirDossierClientButton.addActionListener(this);
        this.historiqueDuClientButton.addActionListener(this);
        this.voirDossierClientButton1.addActionListener(this);
        this.historiqueDuClientButton1.addActionListener(this);


        try (Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/rdv_medical", "root", "root")) {
            this.controller.getMedecin(newConnection) ;

            //affiche la spé du medecin
            String specialisation = this.controller.getSpeMedecin(newConnection);
            this.Spe.setText(specialisation);

            //affichage des cliniques où bosse le medecin
            List<String> list = new ArrayList<>() ;
            list = this.controller.getMedecinClinique(newConnection) ;
            for(int i = 0 ; i < list.size() ; i++){
                this.comboBox1.addItem(list.get(i));
            }
            //affichage de toute les cliniques
            list = this.controller.getAllClinique(newConnection) ;
            for(int i = 0 ; i < list.size() ; i++){
                this.comboBox2.addItem(list.get(i));
            }

        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Libre)) {
            this.Libre.setName("Libre");
            uncheckBox(Libre);
        }
        else if (e.getSource().equals(Réservé)) {
            this.Réservé.setName("Reserve");
            uncheckBox(Réservé);
        }
        else if (e.getSource().equals(Archivé)) {
            this.Archivé.setName("Archive");
            uncheckBox(Archivé);
        }
        else if(e.getSource().equals(this.ajouterUneAutreCliniqueButton)){
            addComboBoxAjouterClinique();
        }
        else if (e.getSource().equals(this.validerAjouterMedecin)) {
            if (!cutString(speMed.getText()).isEmpty() && !cutString(nomMed.getText()).isEmpty() && !cutString(prenomMed.getText()).isEmpty() && !cutString(mailMed.getText()).isEmpty() && !cutString(mdpMed.getText()).isEmpty()) {
                try {
                    addMedecin();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                this.resultatAjout.setText("Veuillez remplir les cases !");
            }
        }
    }

    public String cutString(String mot){
        return mot.replace(" ", "") ;
    }


    public void addComboBoxAjouterClinique(){
        JComboBox comboBox3 = new JComboBox() ;
        JFormattedTextField tf = new JFormattedTextField("Qualification") ;
        tf.setBackground(new Color(240,248,255));
        for(int i = 0 ; i < this.comboBox2.getItemCount() ; i++){
            comboBox3.addItem(this.comboBox2.getItemAt(i));
        }
        this.panelClinique.add(comboBox3) ;
        this.panelClinique.add(tf) ;
    }

    public void addMedecin() throws SQLException {
        int nbClinique = this.panelClinique.getComponentCount()/2 ;
        List<String> cliniques = new ArrayList<>() ;
        for(int i = 0 ; i < nbClinique ; i++){
            JComboBox c = (JComboBox)this.panelClinique.getComponent(2*i) ;
            String nomClinique = (String)c.getSelectedItem() ;
            if(verifierExistence(cliniques, nomClinique)){
                cliniques.add(nomClinique) ;
            }
        }
        try (Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/rdv_medical", "root", "root")) {
            if(this.controller.addMedecin(newConnection, new Medecin(-999, speMed.getText(), nomMed.getText(), prenomMed.getText(), mailMed.getText(), mdpMed.getText()), cliniques)){
                this.resultatAjout.setText("Medecin ajouté avec succès !");
            }
        }
    }

    public boolean verifierExistence(List<String> list, String nom){
        for(int i = 0 ; i < list.size(); i++){
            if(list.get(i) == nom) {
                return false;
            }
        }
        return true ;
    }

    public void uncheckBox(JCheckBox checkbox){
        this.Libre.setSelected(false);
        this.Réservé.setSelected(false);
        this.Archivé.setSelected(false);
        checkbox.setSelected(true);
    }

}
