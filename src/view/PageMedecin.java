package view;

import javax.swing.*;

public class PageMedecin {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox5;
    private JCheckBox checkBox4;
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


    public static void main(String[] args){
        JFrame frame =new JFrame("PageMedecin");
        frame.setContentPane(new PageMedecin().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
