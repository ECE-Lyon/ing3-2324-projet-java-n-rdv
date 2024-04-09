package view;

import controller.AffichageConnexionController;
import controller.AffichageMedecinController;
import model.Clinique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class AffichageMedecin implements ActionListener {

    private AffichageMedecinController controller ;

    private JButton buttonMedecin;
    private JPanel panelMedecin;
    private JCheckBox libreCheckBox;
    private JCheckBox réservéCheckBox;
    private JCheckBox annuléCheckBox;
    private JCheckBox suppriméCheckBox;
    private JCheckBox archivéCheckBox;
    private JRadioButton tousLesRendezVousRadioButton;
    private JFormattedTextField formattedTextField3;
    private JFormattedTextField formattedTextField4;
    private JButton supprimerButton;
    private JButton voirDossierClientButton1;
    private JButton historiqueDuPatientButton1;
    private JComboBox comboBox1;
    private JButton ajouterMedecinButton;
    private JButton ajoutezRendezVousButton;
    private JButton button1;
    private JButton button2;

    public AffichageMedecin(AffichageMedecinController controller)  {

        this.controller = controller ;
        this.ajouterMedecinButton.addActionListener(this);
        this.ajoutezRendezVousButton.addActionListener(this);

        this.libreCheckBox.addActionListener(this);
        this.annuléCheckBox.addActionListener(this);
        this.suppriméCheckBox.addActionListener(this);
        this.réservéCheckBox.addActionListener(this);
        this.archivéCheckBox.addActionListener(this);

        this.comboBox1.addActionListener(this);

        this.ajoutezRendezVousButton.addActionListener(this);
        this.ajoutezRendezVousButton.addActionListener(this);


        try (Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/rdv_medical", "root", "root")) {
            List<String> list = new ArrayList<>() ;
            list = this.controller.getAllClinique(newConnection) ;
            for(int i = 0 ; i < list.size() ; i++){
                this.comboBox1.addItem(list.get(i));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args, AffichageMedecinController control) {
        JFrame frame = new JFrame("AffichageMedecin");
        frame.setContentPane(new AffichageMedecin(control).panelMedecin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(ajouterMedecinButton)) {
            this.ajouterMedecinPopUp();
        } else if (e.getSource().equals(ajoutezRendezVousButton)) {
            this.ajouterRDVPopUp();
        } else if (e.getSource().equals(libreCheckBox)) {
            uncheckBox(libreCheckBox);
        } else if (e.getSource().equals(annuléCheckBox)) {
            uncheckBox(annuléCheckBox);
        } else if (e.getSource().equals(suppriméCheckBox)) {
            uncheckBox(suppriméCheckBox);
        } else if (e.getSource().equals(réservéCheckBox)) {
            uncheckBox(réservéCheckBox);
        } else if (e.getSource().equals(archivéCheckBox)) {
            uncheckBox(archivéCheckBox);
        }
    }


    public String[] getNomClinique(List<Clinique> l){
        String[] noms = new String[l.size()] ;
        for(int i = 0 ; i < l.size(); i++) {
            noms[i] = l.get(i).getNom() ;
        }
        return noms ;
    }

    public void uncheckBox(JCheckBox checkbox){
        this.libreCheckBox.setSelected(false);
        this.réservéCheckBox.setSelected(false);
        this.annuléCheckBox.setSelected(false);
        this.suppriméCheckBox.setSelected(false);
        this.archivéCheckBox.setSelected(false);
        checkbox.setSelected(true);
    }

    public void ajouterRDVPopUp() {
        /** Creer un JDialog à la place */
        AjouterRendezVous ajouterRendezVous = new AjouterRendezVous();
        // Créer une fenêtre pour afficher le contenu de AjouterMedecin
        JFrame ajouterRendezVousFrame = new JFrame("Ajouter caca-vous");
        ajouterRendezVousFrame.setContentPane(ajouterRendezVous.panel1);
        ajouterRendezVousFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pour fermer seulement cette fenêtre
        ajouterRendezVousFrame.pack();
        ajouterRendezVousFrame.setVisible(true);
    }


        public void ajouterMedecinPopUp(){
        /** Creer un JDialog à la place */
        AjouterMedecin ajouterMedecin = new AjouterMedecin();
        // Créer une fenêtre pour afficher le contenu de AjouterMedecin
        JFrame ajouterMedecinFrame = new JFrame("Ajouter Médecin");
        ajouterMedecinFrame.setContentPane(ajouterMedecin.panel1);
        ajouterMedecinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pour fermer seulement cette fenêtre
        ajouterMedecinFrame.pack();
        ajouterMedecinFrame.setVisible(true);
    }

}