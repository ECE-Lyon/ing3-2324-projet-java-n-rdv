package view;
import com.github.lgooddatepicker.components.DatePicker;
import controller.AffichageMedecinController;
import model.Client;
import model.Medecin;
import model.MySql;
import model.Rdv;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageMedecin extends JFrame implements ActionListener {
    private AffichageMedecinController controller;


    //FENETRE TOTAL
    private JPanel panel1;
    private JTabbedPane tabbedPane1;

    //GESTION RDV
    private JCheckBox Libre;
    private JCheckBox Réservé;
    private JCheckBox Archivé;
    private JComboBox comboBox1;
    private JRadioButton tousLesRendezVousRadioButton;
    private JButton validerButton;
    private JFormattedTextField mdpMed;
    private JFormattedTextField speMed;
    private JButton validerAjouterMedecin;
    private JPanel panel2;
    private JPanel JPanel4;
    private JLabel Spe;
    private DatePicker DateBox1;
    private DatePicker DateBox2;
    private JComboBox comboBox3;

    //GESTION CLIENT
    private JPanel gestionClient;
    private JButton validerButton1;
    private JPanel scrollPanel;
    private JFormattedTextField nomGestionClient;
    private JFormattedTextField prenomGestionClient;
    private JFormattedTextField mailGestionClient;

    //AJOUTER MEDECIN
    private JPanel panelClinique;
    private JComboBox comboBox2;
    private JButton ajouterUneAutreCliniqueButton;
    private JFormattedTextField EntrerQualification;
    private JFormattedTextField nomMed;
    private JFormattedTextField prenomMed;
    private JFormattedTextField mailMed;
    private JPanel southPanel;
    private JLabel resultatAjout;


    public PageMedecin(AffichageMedecinController control)  {
        super("Page Médecin") ;
        this.controller = control ;
        Connection connection = MySql.getConnection() ;

        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panelClinique.setLayout(new GridLayout(0, 1));
        this.scrollPanel.setLayout(new GridLayout(0, 1));
        JLabel label = new JLabel("Faire une recherche", JLabel.CENTER) ;
        label.setForeground(new Color(130, 145, 132, 255));
        this.scrollPanel.add(label) ;

        //LES SEULS UTILES POUR L'INSTANT
        this.Libre.addActionListener(this);
        this.Réservé.addActionListener(this);
        this.Archivé.addActionListener(this);
        this.ajouterUneAutreCliniqueButton.addActionListener(this);
        this.validerAjouterMedecin.addActionListener(this);
        this.validerButton1.addActionListener(this);

        this.comboBox1.addActionListener(this);
        this.tousLesRendezVousRadioButton.addActionListener(this);
        this.validerButton.addActionListener(this);



        this.controller.getMedecinAndAllClinique(connection) ;
        //affiche la spé du medecin
        String specialisation = this.controller.getSpeMedecin();
        this.Spe.setText(specialisation);




        //affichage des cliniques où bosse le medecin
        List<String> list = new ArrayList<>() ;
        list = this.controller.getMedecinClinique() ;
        for(int i = 0 ; i < list.size() ; i++){
            this.comboBox1.addItem(list.get(i));
        }
        //affichage de toute les cliniques
        list = this.controller.getAllClinique(connection) ;
        for(int i = 0 ; i < list.size() ; i++){
            this.comboBox2.addItem(list.get(i));
        }

        this.pack();
        this.setVisible(true);
    }

    public String cutString(String mot){
        return mot.replace(" ", "") ;
    }


    public void addComboBoxAjouterClinique(){
        JComboBox comboBox3 = new JComboBox() ;
        JFormattedTextField tf = new JFormattedTextField("Qualification") ;
        tf.setBackground(new Color(240,248,255));
        for(int i = 0 ; i < this.comboBox2.getItemCount() ; i++){
            comboBox3.addItem(this.comboBox2.getItemAt(i));
        }
        this.panelClinique.add(comboBox3) ;
        this.panelClinique.add(tf) ;

        ///Actualiser la page
        actualiserPageGestionClient();
    }

    public void addMedecin() {
        int nbClinique = this.panelClinique.getComponentCount()/2 ;
        List<String> cliniques = new ArrayList<>() ;
        for(int i = 0 ; i < nbClinique ; i++){
            JComboBox c = (JComboBox)this.panelClinique.getComponent(2*i) ;
            String nomClinique = (String)c.getSelectedItem() ;
            if(verifierExistence(cliniques, nomClinique)){
                cliniques.add(nomClinique) ;
            }
        }
        if(this.controller.addMedecin(MySql.getConnection(), new Medecin(-999, speMed.getText(), nomMed.getText(), prenomMed.getText(), mailMed.getText(), mdpMed.getText()), cliniques)){
            this.resultatAjout.setText("Medecin ajouté avec succès !");
        }
    }

    public boolean verifierExistence(List<String> list, String nom){
        for(int i = 0 ; i < list.size(); i++){
            if(list.get(i) == nom) {
                return false;
            }
        }
        return true ;
    }

    public void uncheckBox(JCheckBox checkbox){
        this.Libre.setSelected(false);
        this.Réservé.setSelected(false);
        this.Archivé.setSelected(false);
        checkbox.setSelected(true);
    }

    public void actualiserPageGestionClient(){
        this.panelClinique.revalidate();
        this.panelClinique.repaint();
    }

    public void validerRechercheGestionClient(){
        this.scrollPanel.removeAll();
        int nbNull = 0;
        String nom = nomGestionClient.getText(), prenom = prenomGestionClient.getText(), mail = mailGestionClient.getText() ;
        if(cutString(nom).isEmpty()){
            nom = null ;
            nbNull++ ;
        }
        if(cutString(prenom).isEmpty()){
            prenom = null ;
            nbNull++ ;
        }
        if(cutString(mail).isEmpty()){
            mail = null ;
            nbNull++ ;
        }
        if(nbNull == 3){
            JLabel label = new JLabel("Veuillez remplir les critères !", JLabel.CENTER) ;
            label.setForeground(new Color(255, 0, 0));
            this.scrollPanel.add(label) ;
            actualiserPageGestionClient();
        }
        else{
            List<Client> clients = this.controller.getClientsRecherche(MySql.getConnection(), nom, prenom, mail) ;
            if(clients.size() > 0) {
                for (int i = 0; i < clients.size(); i++) {
                    this.scrollPanel.add(ajouterBouttonDossierClient(clients.get(i).getNom(), clients.get(i).getPrenom(), clients.get(i).getMail(), clients.get(i).getIdClient()));
                }
            }
            else {
                JLabel label = new JLabel("Les critères ne correspondent à aucun client !", JLabel.CENTER) ;
                label.setForeground(new Color(255, 0, 0));
                this.scrollPanel.add(label) ;
            }
            actualiserPageGestionClient();
        }
    }

    public JPanel ajouterBouttonDossierClient(String nom, String prenom, String mail, int idClient)  {
        JPanel panel = new JPanel() ;
        Border bord = BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ;

        panel.setLayout(new FlowLayout(0, 40, 0));
        panel.setBorder(bord);

        panel.add(new JLabel(nom)) ;
        panel.add(new JLabel(prenom)) ;
        panel.add(new JLabel(mail)) ;
        JButton bouttonHistorique = new JButton("Historique") ;

        List<Rdv> rdvList = this.controller.getRdvsClient(MySql.getConnection(), idClient) ;
        List<Medecin> medecinList = new ArrayList<>() ;
        for(int i = 0 ; i < rdvList.size() ; i++){
            int[] tabId = this.controller.getIdMedecinCliniqueByIdJointure(MySql.getConnection(), rdvList.get(i).getIdJointure()) ;
            medecinList.add(this.controller.getMedecinAndOneClinique(MySql.getConnection(), tabId)) ;
        }
        bouttonHistorique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoriqueClient historique = new HistoriqueClient(rdvList, medecinList) ;
            }
        });

        panel.add(bouttonHistorique) ;
        return panel ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Libre)) {
            this.Libre.setName("Libre");
            uncheckBox(Libre);
        }
        else if (e.getSource().equals(Réservé)) {
            this.Réservé.setName("Reserve");
            uncheckBox(Réservé);
        }
        else if (e.getSource().equals(Archivé)) {
            this.Archivé.setName("Archive");
            uncheckBox(Archivé);
        }
        else if(e.getSource().equals(this.ajouterUneAutreCliniqueButton)){
            addComboBoxAjouterClinique();
        }
        else if (e.getSource().equals(this.validerAjouterMedecin)) {
            if (!cutString(speMed.getText()).isEmpty() && !cutString(nomMed.getText()).isEmpty() && !cutString(prenomMed.getText()).isEmpty() && !cutString(mailMed.getText()).isEmpty() && !cutString(mdpMed.getText()).isEmpty()) {
                addMedecin();
            }
            else {
                this.resultatAjout.setText("Veuillez remplir les cases !");
            }
        }
        else if(e.getSource().equals(this.validerButton1)){
            validerRechercheGestionClient();
        }
    }

}
