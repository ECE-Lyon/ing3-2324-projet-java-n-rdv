package view;
import javax.swing.* ;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import controller.AffichageConnexionController ;

public class AffichageConnexion extends JFrame implements ActionListener, Observer {

    private AffichageConnexionController controller ;
    private Connection connection ;

    private boolean connexionAccepte = false ;

    //Elements de la fenêtre
    private JLabel titre = new JLabel("CONNEXION", JLabel.CENTER) ;
    private JTextField entrerMail = new JTextField(50) ;
    private JTextField entrerMdp = new JTextField(50) ;
    private JButton boutton = new JButton("Enter") ;

    public AffichageConnexion(Connection connection, AffichageConnexionController newController){
        super("Test Projet") ;
        this.controller = newController ;
        this.connection = connection ;

        //Ajout d'un Listener sur notre boutton
        this.boutton.addActionListener(this);

        //Creation de l'affichage de la fenêtre
        this.setMinimumSize(new Dimension(700, 500));
        this.setLayout(new BorderLayout(0, 50));
        titre.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        this.add(titre, BorderLayout.NORTH) ;
        this.add(createCenter(), BorderLayout.CENTER) ;

        JPanel panelBoutton = new JPanel(new FlowLayout()) ;
        panelBoutton.add(boutton) ;
        this.add(panelBoutton, BorderLayout.SOUTH) ;


        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JPanel createCenter() {
        JPanel panel = new JPanel(new GridLayout(2, 1)) ;
        JPanel panel1 = new JPanel(new FlowLayout()) ;
        JPanel panel2 = new JPanel(new FlowLayout()) ;
        panel1.add(new JLabel("E-mail :")) ;
        panel1.add(entrerMail) ;
        panel2.add(new JLabel("Password :")) ;
        panel2.add(entrerMdp) ;

        panel.add(panel1) ;
        panel.add(panel2) ;

        return  panel ;
    }

    public void display(boolean resultatConnexion) {
        JDialog popUp = new JDialog(this, "Connexion...", true) ;
        popUp.setLayout(new GridLayout(1, 1, 0, 30));
        String afficher;

        if(resultatConnexion){
            afficher = "Connexion en cours !" ;

        }
        else { afficher = "Email ou mot de passe faux !" ;}
        JLabel label = new JLabel(afficher, JLabel.CENTER);
        popUp.add(label);
        popUp.pack();
        popUp.setLocationRelativeTo(this);
        popUp.setVisible(true);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(boutton)) {
            try (Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost/rdv_medical", "root", "root")) {
                try {
                    controller.entrerConnexion(newConnection, entrerMail.getText(), entrerMdp.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }
    }

    @Override
    public void update(Observable o, Object arg) {
        display((boolean) arg);
    }
}
