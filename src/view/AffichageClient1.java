package view;

import javax.swing.*;

public class AffichageClient1 {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton rechercherButton;


    public static void main(String[] args){
        JFrame frame = new JFrame("PageClient");
        frame.setContentPane(new AffichageClient1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
