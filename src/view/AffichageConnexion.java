package view;
import javax.swing.* ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import controller.AffichageConnexionController ;
import model.Client;
import model.Compte;
import model.MySql;


/** Vérifier pour  */

public class AffichageConnexion extends JFrame implements WindowListener, ActionListener, Observer {

    private AffichageConnexionController controller ;
    //Elements de la fenêtre de connexion
    private JLabel titre = new JLabel("CONNEXION", JLabel.CENTER) ;
    private JTextField entrerMail = new JTextField(50) ;
    private JTextField entrerMdp = new JTextField(50) ;
    private JButton boutton = new JButton("Entrer") ;
    private JButton pasDeCompteBoutton = new JButton("Je n'ai pas de compte") ;
    private JLabel mdpIncorrecte = new JLabel("", JLabel.CENTER) ;

    //Elements de la fenêtre de création de compte
    JDialog popUpCopy = new JDialog() ;
    private JTextField mail = new JTextField(50) ;
    private JTextField mdp = new JTextField(50) ;
    private JTextField nom = new JTextField(50) ;
    private JTextField prenom = new JTextField(50) ;
    private JButton confirmer = new JButton("Entrer") ;

    public AffichageConnexion(AffichageConnexionController newController){
        super("Test Projet") ;
        this.controller = newController ;

        //Ajout de Listeners
        this.boutton.addActionListener(this);
        this.pasDeCompteBoutton.addActionListener(this);
        this.addWindowListener(this);

        //Creation de l'affichage de la fenêtre
        this.setMinimumSize(new Dimension(700, 500));
        this.setLayout(new BorderLayout(0, 50));
        titre.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        //Ajout des composants dans la fenêtre
        this.add(createNorth(mdpIncorrecte), BorderLayout.NORTH) ;
        this.add(createCenter(), BorderLayout.CENTER) ;
        JPanel panelBoutton = new JPanel(new FlowLayout()) ;
        panelBoutton.add(boutton) ;
        this.add(panelBoutton, BorderLayout.SOUTH) ;


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JPanel createNorth(JLabel messageMDP){
        JPanel panelNorth = new JPanel(new GridLayout(2, 1)) ;
        messageMDP.setForeground(Color.red);
        panelNorth.add(titre) ;
        panelNorth.add(messageMDP) ;
        return  panelNorth ;
    }

    public JPanel createCenter() {
        JPanel panel = new JPanel(new GridLayout(3, 1)) ;
        JPanel panel1 = new JPanel(new FlowLayout()) ;
        JPanel panel2 = new JPanel(new FlowLayout()) ;
        JPanel panel3 = new JPanel(new FlowLayout()) ;

        panel1.add(new JLabel("E-mail :")) ;
        panel1.add(entrerMail) ;
        panel2.add(new JLabel("Password :")) ;
        panel2.add(entrerMdp) ;
        panel3.add(pasDeCompteBoutton) ;
        panel.add(panel1) ;
        panel.add(panel2) ;
        panel.add(panel3) ;

        return  panel ;
    }
    
    public void creerCompte() {
        JDialog popUp = new JDialog(this, "Création de commte", true) ;
        popUp.setLayout(new GridLayout(5, 1, 0, 30));
        this.popUpCopy = popUp ;
        JPanel panel1 = new JPanel(new FlowLayout()) ;
        JPanel panel2 = new JPanel(new FlowLayout()) ;
        JPanel panel3 = new JPanel(new FlowLayout()) ;
        JPanel panel4 = new JPanel(new FlowLayout()) ;
        JPanel panel5 = new JPanel(new FlowLayout()) ;

        panel1.add(new JLabel("Nom :")) ;
        panel1.add(nom) ;
        panel2.add(new JLabel("Prénom :")) ;
        panel2.add(prenom) ;
        panel3.add(new JLabel("E-mail :")) ;
        panel3.add(mail) ;
        panel4.add(new JLabel("Password :")) ;
        panel4.add(mdp) ;
        panel5.add(confirmer) ;
        confirmer.addActionListener(this); ;

        popUp.add(panel1) ;
        popUp.add(panel2) ;
        popUp.add(panel3) ;
        popUp.add(panel4) ;
        popUp.add(panel5) ;

        popUp.pack();
        popUp.setLocationRelativeTo(this);
        popUp.setVisible(true);
    }

    public void display(boolean resultatConnexion) {
        if(resultatConnexion){
            this.setVisible(false);
        }
        else {
            this.mdpIncorrecte.setText("Email ou mot de passe incorrecte !");
            this.mdpIncorrecte.setForeground(Color.red);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(boutton)) {
            try {
                this.controller.entrerConnexion(MySql.getConnection(), entrerMail.getText(), entrerMdp.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource().equals(pasDeCompteBoutton)) {
            creerCompte();
        }
        else if(e.getSource().equals(confirmer)) {
            try{
                this.controller.creerClient(MySql.getConnection(), new Compte(nom.getText(), prenom.getText(), mail.getText(), mdp.getText()));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.popUpCopy.dispose();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        display((boolean) arg);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
