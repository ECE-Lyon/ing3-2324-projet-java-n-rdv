package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AffichageMedecin {
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


    /*public AffichageMedecin() {
        buttonMedecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"deja pris rendez-vous");
            }
        });
    }*/

    public AffichageMedecin() {

        ajouterMedecinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AjouterMedecin ajouterMedecin = new AjouterMedecin();
                // Créer une fenêtre pour afficher le contenu de AjouterMedecin
                JFrame ajouterMedecinFrame = new JFrame("Ajouter Médecin");
                ajouterMedecinFrame.setContentPane(ajouterMedecin.panel1);
                ajouterMedecinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pour fermer seulement cette fenêtre
                ajouterMedecinFrame.pack();
                ajouterMedecinFrame.setVisible(true);
            }
        });
        ajoutezRendezVousButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterRendezVous ajouterRendezVous = new AjouterRendezVous();
                // Créer une fenêtre pour afficher le contenu de AjouterMedecin
                JFrame ajouterRendezVousFrame = new JFrame("Ajouter Rendez-vous");
                ajouterRendezVousFrame.setContentPane(ajouterRendezVous.panel1);
                ajouterRendezVousFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pour fermer seulement cette fenêtre
                ajouterRendezVousFrame.pack();
                ajouterRendezVousFrame.setVisible(true);

            }


        });

    }

    public static void main(String[] args){
        JFrame frame =new JFrame("AffichageMedecin");
        frame.setContentPane(new AffichageMedecin().panelMedecin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



}

