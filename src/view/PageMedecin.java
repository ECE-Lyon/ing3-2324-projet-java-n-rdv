package view;

import controller.AffichageMedecinController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageMedecin implements ActionListener {
    private AffichageMedecinController controller;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
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
    private JFormattedTextField formattedTextField6;
    private JFormattedTextField formattedTextField7;
    private JButton validerButton2;
    private JPanel panel2;
    private JPanel JPanel4;
    private JLabel Spe;

    public PageMedecin(AffichageMedecinController controller)  {

        this.controller = controller ;

        this.Libre.addActionListener(this);
        this.Réservé.addActionListener(this);
        this.Archivé.addActionListener(this);
        this.comboBox1.addActionListener(this);
        this.tousLesRendezVousRadioButton.addActionListener(this);
        this.validerButton.addActionListener(this);
        this.validerButton1.addActionListener(this);

        this.voirDossierClientButton.addActionListener(this);
        this.historiqueDuClientButton.addActionListener(this);
        this.validerButton2.addActionListener(this);
        this.voirDossierClientButton1.addActionListener(this);
        this.historiqueDuClientButton1.addActionListener(this);

        try (Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/rdv_medical", "root", "")) {
            //affiche la spé du medecin
            String specialisation;
            specialisation = this.controller.getSpeMedecin(newConnection);
            this.Spe.setText(specialisation);


            List<String> list = new ArrayList<>() ;
            list = this.controller.getAllClinique(newConnection) ;
            for(int i = 0 ; i < list.size() ; i++){
                this.comboBox1.addItem(list.get(i));
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void main(String[] args, AffichageMedecinController control){
        JFrame frame =new JFrame("PageMedecin");
        frame.setContentPane(new PageMedecin(control).panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Libre)) {
            this.Libre.setName("Libre");
            uncheckBox(Libre);
        } else if (e.getSource().equals(Réservé)) {
            this.Réservé.setName("Reserve");
            uncheckBox(Réservé);
        } else if (e.getSource().equals(Archivé)) {
            this.Archivé.setName("Archive");
            uncheckBox(Archivé);
        }
    }

    public void uncheckBox(JCheckBox checkbox){
        this.Libre.setSelected(false);
        this.Réservé.setSelected(false);
        this.Archivé.setSelected(false);
        checkbox.setSelected(true);
    }

}
