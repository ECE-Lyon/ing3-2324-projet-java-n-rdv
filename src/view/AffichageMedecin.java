package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AffichageMedecin {
    private JButton buttonMedecin;
    private JPanel panelMedecin;
    private JFormattedTextField rendezVousDeLaFormattedTextField;
    private JCheckBox afficherCheckBox;
    private JCheckBox cacherCheckBox;
    private JFormattedTextField etatDesRendezVousFormattedTextField;
    private JCheckBox libreCheckBox;
    private JCheckBox reservéCheckBox;
    private JCheckBox annuléCheckBox;
    private JCheckBox suppriméCheckBox;
    private JCheckBox archivéCheckBox;
    private JCheckBox afficherUniquementCesRdvCheckBox;
    private JFormattedTextField DUFormattedTextField;
    private JPasswordField passwordField1;
    private JFormattedTextField auFormattedTextField;
    private JPasswordField passwordField2;
    private JButton appliquerButton;
    private JTextArea gestionDesRendezVousTextArea;
    private JFormattedTextField nombreDesRendezVousFormattedTextField;
    private JSpinner spinner1;
    private JButton créerDesRendezVousButton;
    private JButton supprimerButton;
    private JButton reProposerButton;
    private JButton archiverButton;


    public AffichageMedecin() {
        buttonMedecin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"deja pris rendez-vous");
            }
        });
    }

    public static void main(String[] args){
        JFrame frame =new JFrame("AffichageMedecin1");
        frame.setContentPane(new AffichageMedecin().panelMedecin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

