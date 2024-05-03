package view;

import controller.AffichageClientController;
import model.MySql;

import javax.swing.*;
import java.awt.event.*;

public class ConfirmerRDV extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private boolean resultatt ;

    public ConfirmerRDV() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setLocationRelativeTo(null);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        this.pack();
        this.setVisible(true);
    }

    private void onOK() {
        this.resultatt = true ;
        dispose();
    }

    private void onCancel() {
        this.resultatt = false ;
        dispose();
    }

    public boolean resultat(){ return this.resultatt ; }
}
