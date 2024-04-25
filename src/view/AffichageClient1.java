package view;

import javax.swing.*;

public class AffichageClient1 {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton rechercheButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JButton prendreCeRendezVousButton;
    private JRadioButton radioButton4;
    private JButton voirNoteButton;
    private JButton voirNoteButton1;
    private JButton voirNoteButton2;
    private JButton voirNoteButton3;


    public static void main(String[] args){
        JFrame frame = new JFrame("PageClient");
        frame.setContentPane(new AffichageClient1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
