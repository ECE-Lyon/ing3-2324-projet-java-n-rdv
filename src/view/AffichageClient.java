package view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import controller.AffichageClientController;
import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.sql.Timestamp;

import static java.awt.SystemColor.control;

/** VOIR POUR LES COMBOBOXS , QUAND TU SELECTIONNES UN MEDECIN, TU AURAS DIRECTEMENT QUE LES CLINIQUES OU IL BOSSE, ET INVERSEMENT*/

public class AffichageClient extends JFrame implements ActionListener, DateChangeListener {
    private AffichageClientController controller ;
    private JPanel panel;
    private JTabbedPane tabbedPane1;

    //Onglet recherche
    private JComboBox comboBoxMedecin;
    private JComboBox comboBoxClinique;
    private JPanel scrollPanel;
    private JButton rechercherButton;
    private DatePicker datePicker;

    private JButton prendreCeRendezVousButton;
    private JPanel historiquePanel;
    private JButton voirNoteButton;
    private JComboBox comboBoxDate;

    public AffichageClient(AffichageClientController control){
        super("Page Client") ;

        Connection connection = MySql.getConnection() ;

        this.controller = control ;
        this.controller.getClientConnecte(connection) ;
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ouvrir page note
       /* buttonNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientNote buttonNote = new ClientNote(control);
                buttonNote.setVisible(true);
            }
        });*/
        //afficher heure


        this.scrollPanel.setLayout(new GridBagLayout());
        this.historiquePanel.setLayout(new GridLayout(0,1));

        JLabel label = new JLabel("Faire une recherche", JLabel.CENTER) ;
        label.setForeground(new Color(130, 145, 132, 255));
        this.scrollPanel.add(label) ;

        this.datePicker.addDateChangeListener(this);
        this.rechercherButton.addActionListener(this);

        //Ajouter les elements aux combo boxs
        List<String> list = new ArrayList<>() ;
        List<String> list1 = new ArrayList<>() ;
        list = this.controller.getAllClinique(MySql.getConnection()) ;
        list.add(0, "-----") ;
        for(int i = 0 ; i < list.size() ; i++){
            this.comboBoxClinique.addItem(list.get(i));
        }
        list = this.controller.getAllMedecin(MySql.getConnection()) ;
        list.add(0, "-----") ;
        for(int i = 0 ; i < list.size() ; i++){
            this.comboBoxMedecin.addItem(list.get(i));
        }
        list = this.controller.getHeure(MySql.getConnection()) ;

        List<Integer> rdvList= this.controller.getidRDV(MySql.getConnection()) ;

        List<String> listNote = new ArrayList<>();
        for(int i = 0 ; i < rdvList.size() ; i++){
            listNote = this.controller.getNote(MySql.getConnection(),rdvList.get(i));
            this.historiquePanel.add(createPanel(list.get(i), listNote.get(0)));
            afficherNomMedecinPourRdv();
        }
        this.pack();
        this.setVisible(true);
    }

    public JPanel createPanel(String date, String note) {


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(0, 40, 0));

        JLabel label1 = new JLabel("date: " + date);
        panel.add(label1);

        JLabel label2 = new JLabel("note: " + note);
        panel.add(label2);
        return panel;
    }




    public void validerRecherche(){
        this.scrollPanel.removeAll();
        String nomMedecin = (String)comboBoxMedecin.getSelectedItem(), nomClinique = (String)comboBoxClinique.getSelectedItem() ;
        if(nomMedecin.equals("-----")){
            nomMedecin = null ;
        }
        if(nomClinique.equals("-----")){
            nomClinique = null ;
        }
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter  df =  DateTimeFormatter.ofPattern(pattern) ;

        LocalDate date = null ;
        if(this.datePicker.getDate() != null) {
            date = LocalDate.parse(this.datePicker.getDate().format(df)) ;
        }
        else {
            date = LocalDate.now() ;
            this.datePicker.setDate(date);
        }
        Date sqlDate = Date.valueOf(date) ;


        List<Creneau> creneaux = this.controller.getRdvLibre(MySql.getConnection(), nomMedecin, nomClinique, sqlDate) ;
        creneaux.sort(Comparator.comparingInt(Creneau::getTime));
        List<Medecin> medecins = this.controller.getMedecinByIdWithCreneauxLibre(MySql.getConnection(), creneaux) ;
        creerPanelRecherche(creneaux, medecins);

        this.scrollPanel.revalidate();
        this.scrollPanel.repaint();
    }

    public void creerPanelRecherche(List<Creneau> c, List<Medecin> m){
        createTopSideOfPanel(c.size());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0); ;
        constraints.weighty = 2;
        constraints.weightx = 1;
        for(int i = 0 ; i < c.size() ; i++){
            this.scrollPanel.add(new JLabel(m.get(i).getNom() + " " + m.get(i).getPrenom(), JLabel.LEFT), constraints) ;
            constraints.gridx = 1;
            this.scrollPanel.add(new JLabel(m.get(i).getCliniques().get(0).getNom(), JLabel.LEFT), constraints) ;
            constraints.gridx = 2;
            this.scrollPanel.add(new JLabel(c.get(i).getTime() + ":00", JLabel.LEFT), constraints) ;
            constraints.gridx = 3;
            JButton bouttonRDV = new JButton("Prendre RDV") ;
            this.scrollPanel.add(bouttonRDV, constraints) ;
            constraints.gridx = 0;
            constraints.gridy ++ ;
            int k = i ;
            bouttonRDV.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ConfirmerRDV popUpConfirmer = new ConfirmerRDV() ;
                    boolean resultat = popUpConfirmer.resultat() ;
                    if(resultat){
                        controller.prendreRdv(MySql.getConnection(), c.get(k));
                        scrollPanel.removeAll();
                        JLabel label = new JLabel("Rendez-vous pris avec succès!", JLabel.CENTER) ;
                        label.setForeground(new Color(126, 252, 88, 255));
                        scrollPanel.add(label) ;
                        scrollPanel.revalidate();
                        scrollPanel.repaint();
                    }
                }
            });
        }
    }

    public void createTopSideOfPanel(int size){
        if(size>0){
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 1;
            constraints.weighty = 0.5;
            constraints.fill = GridBagConstraints.BOTH;

            Font font = new Font("Arial", Font.BOLD, 15);
            JLabel label = new JLabel("Medecin :", JLabel.CENTER) ;
            label.setFont(font);
            label.setForeground(new Color(87, 120, 147));
            this.scrollPanel.add(label, constraints) ;

            constraints.gridx = 1 ;
            JLabel label2 = new JLabel("Clinique :", JLabel.CENTER) ;
            label2.setForeground(new Color(87, 120, 147));
            label2.setFont(font);
            this.scrollPanel.add(label2, constraints) ;

            constraints.gridx = 2;
            JLabel label3 = new JLabel("Heure :", JLabel.CENTER) ;
            label3.setForeground(new Color(87, 120, 147));
            label3.setFont(font);
            this.scrollPanel.add(label3, constraints) ;

            JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
            JSeparator line2 = new JSeparator(SwingConstants.HORIZONTAL);
            JSeparator line3 = new JSeparator(SwingConstants.HORIZONTAL);
            JSeparator line4 = new JSeparator(SwingConstants.HORIZONTAL);
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.weightx = 1;
            constraints.insets = new Insets(5, 0, 10, 0);
            this.scrollPanel.add(line, constraints) ;
            constraints.gridx = 1;
            this.scrollPanel.add(line2, constraints) ;
            constraints.gridx = 2;
            this.scrollPanel.add(line3, constraints) ;
            constraints.gridx = 3;
            this.scrollPanel.add(line4, constraints) ;
        }
        else {
            JLabel label = new JLabel("Aucun rendez-vous libre trouvé !", JLabel.CENTER) ;
            label.setForeground(new Color(133, 89, 89, 255));
            this.scrollPanel.add(label) ;
        }
    }


    public void afficherNomMedecinPourRdv() {
        Client client = this.controller.getClientConnecte(MySql.getConnection());
        int idClient = client.getIdClient();

        List<Integer> idJointure = this.controller.getIdJointure(MySql.getConnection(), idClient);
        for(int i=0 ; i < idJointure.size() ; i++){
            int[] tabId = this.controller.getIdMedecinCliniqueByIdJointure(MySql.getConnection(), idJointure.get(i));
            Medecin medecin = this.controller.getMedecinAndOneClinique(MySql.getConnection(), tabId);

            // Affichage du nom du médecin à côté de l'heure
            String nomMedecin = medecin.getNom() + " " + medecin.getPrenom();
            //setText("Médecin : " + nomMedecin);
        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.rechercherButton)){
            validerRecherche();
        }
    }


    @Override
    public void dateChanged(DateChangeEvent dateChangeEvent) {
        LocalDate today = LocalDate.now();
        if(this.datePicker.getDate() != null) {
            if (this.datePicker.getDate().isBefore(today)) {
                this.datePicker.setDate(today);
            }
        }
    }
}
