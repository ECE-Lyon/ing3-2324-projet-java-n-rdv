package view;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import controller.AffichageMedecinController;
import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private JScrollPane resultatRdv;
    private JPanel rdvsMedecin;


    private JFormattedTextField mdpMed;
    private JFormattedTextField speMed;
    private JButton validerAjouterMedecin;
    private JPanel panel2;
    private JPanel JPanel4;
    private JLabel Spe;
    private DatePicker DateBox1;

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

    ///AJOUTER CRENEAUX
    private JButton validerBouttonCreneau;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private DatePicker datePickerCreneau;
    private JLabel labelReussi;


    public PageMedecin(AffichageMedecinController control)  {
        super("Page Médecin") ;
        this.controller = control ;
        Connection connection = MySql.getConnection() ;

        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panelClinique.setLayout(new GridLayout(0, 1));
        this.rdvsMedecin.setLayout(new GridBagLayout());
        this.scrollPanel.setLayout(new GridLayout(0, 1));
        JLabel label = new JLabel("Faire une recherche", JLabel.CENTER) ;
        label.setForeground(new Color(130, 145, 132, 255));
        this.scrollPanel.add(label) ;
        JLabel label2 = new JLabel("Faire une recherche", JLabel.CENTER) ;
        label2.setForeground(new Color(130, 145, 132, 255));
        this.rdvsMedecin.add(label2) ;

        //LISTENER
        this.Libre.addActionListener(this);
        this.Réservé.addActionListener(this);
        this.Archivé.addActionListener(this);
        this.ajouterUneAutreCliniqueButton.addActionListener(this);
        this.validerAjouterMedecin.addActionListener(this);
        this.validerButton1.addActionListener(this);
        this.validerBouttonCreneau.addActionListener(this);
        this.comboBox1.addActionListener(this);
        this.validerButton.addActionListener(this);
        this.datePickerCreneau.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent dateChangeEvent) {
                if(datePickerCreneau.getDate().isBefore(LocalDate.now())) {
                    datePickerCreneau.setDate(LocalDate.now());
                }
            }
        });


        this.controller.getMedecinAndAllClinique(connection) ;
        //affiche la spé du medecin
        String specialisation = this.controller.getSpeMedecin();
        this.Spe.setText(specialisation);


        //affichage des cliniques où bosse le medecin
        List<String> list = new ArrayList<>() ;
        list = this.controller.getMedecinClinique() ;
        for(int i = 0 ; i < list.size() ; i++){
            this.comboBox1.addItem(list.get(i));
            this.comboBox3.addItem(list.get(i)) ;
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

    public void checkBox(JCheckBox checkbox){
        this.Libre.setSelected(false);
        this.Réservé.setSelected(false);
        this.Archivé.setSelected(false);
        checkbox.setSelected(true);
    }
    public void uncheckBox(JCheckBox checkbox){
        checkbox.setSelected(false);
    }

    public void actualiserPageGestionClient(){
        this.panelClinique.revalidate();
        this.panelClinique.repaint();
    }

    ///Quand on appuie sur le boutton "Rechercher" de la page de Gestion CLient, on appelle cette fonction
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
            //Si les 3 TextField ne sont pas vides, on effectue la recherche dans la BDD en fonction des critères rentrés
            List<Client> clients = this.controller.getClientsRecherche(MySql.getConnection(), nom, prenom, mail) ;
            //Si la liste de clients que l'on récupère de la bdd contient un client à l'intérieur, on doit l'afficher
            if(clients.size() > 0) {
                for (int i = 0; i < clients.size(); i++) {
                    ///Pour chaque client dans la liste, on ajoute dans le JPanel un panel, qui correspond à un client
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

    public void createPanel(String nomClinique, Timestamp dateRdv, Client client, String etat, int i) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = i;
        constraints.weightx = 1;
        constraints.weighty = 4;
        constraints.fill = GridBagConstraints.BOTH;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(dateRdv);

        JLabel label1 = new JLabel(nomClinique, JLabel.CENTER);
        constraints.gridx = 0;
        this.rdvsMedecin.add(label1, constraints);

        JLabel label2 = new JLabel(dateString,JLabel.CENTER);
        constraints.gridx = 1;
        this.rdvsMedecin.add(label2, constraints);

        if (client != null){
            JLabel label3 = new JLabel(client.getPrenom() + " " + client.getNom(), JLabel.CENTER);
            constraints.gridx = 2;
            this.rdvsMedecin.add(label3, constraints);

            JLabel label4 = new JLabel(etat, JLabel.CENTER);
            constraints.gridx = 3;
            this.rdvsMedecin.add(label4, constraints);
        }
        else{
            JLabel label4 = new JLabel(etat, JLabel.CENTER);
            constraints.gridx = 2;
            this.rdvsMedecin.add(label4, constraints);
        }
    }


    /// La fonction pour ajouter les clients en résultat de recherche (et avoir l'historique)
    public JPanel ajouterBouttonDossierClient(String nom, String prenom, String mail, int idClient)  {
        JPanel panel = new JPanel() ;
        Border bord = BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ;

        panel.setLayout(new FlowLayout(0, 40, 0));
        panel.setBorder(bord);

        ///ICI, on rajoute le nom, prénom et mail du client
        panel.add(new JLabel(nom)) ;
        panel.add(new JLabel(prenom)) ;
        panel.add(new JLabel(mail)) ;

        ///A partir de là, on ajoute le boutton associé aux infos précédentes pour voir l'historique du client en question
        JButton bouttonHistorique = new JButton("Historique") ;
        ///Ici, on prend de la bdd l'ensemble des rdvs du client en question, avec l'id du client
        List<Rdv> rdvList = this.controller.getRdvsClient(MySql.getConnection(), idClient) ;
        List<Medecin> medecinList = new ArrayList<>() ;
        for(int i = 0 ; i < rdvList.size() ; i++){
            ///Pour chaque rdv, on a une IdJointure qui correspond au couple Medecin/Clinque où se passe/s'est passé le rdv
            ///On recupère donc l'id de la clinique et du médecin associé à chaque rdv avec l'idJointure
            ///Après, avec ces 2 ids, on peut enfin récupérer le medecin en question.
            ///A l'intérieur de chaque medecin, on insère aussi la clinique où le rdv est. (Elle est dans une liste, et il n'y a que 1 clinique dans cette liste
            int[] tabId = this.controller.getIdMedecinCliniqueByIdJointure(MySql.getConnection(), rdvList.get(i).getIdJointure()) ;
            medecinList.add(this.controller.getMedecinAndOneClinique(MySql.getConnection(), tabId)) ;
        }
        ///Quand on appuie sur le boutton, on rentre les 2 listes (de rdv et de medecin); Un pop-up s'affiche
        bouttonHistorique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoriqueClient historique = new HistoriqueClient(rdvList, medecinList) ;
            }
        });

        panel.add(bouttonHistorique) ;
        return panel ;
    }

    public void validerAjoutCreneau(){
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter df =  DateTimeFormatter.ofPattern(pattern) ;

        LocalDate date = null ;
        if(this.datePickerCreneau.getDate() != null) {
            date = LocalDate.parse(this.datePickerCreneau.getDate().format(df)) ;
        }
        else {
            date = LocalDate.now() ;
            this.datePickerCreneau.setDate(date);
        }
        Date sqlDate = Date.valueOf(date) ;
        String clinique = (String) this.comboBox3.getSelectedItem() ;
        String heure = (String) this.comboBox4.getSelectedItem() ;
        String duree = (String) this.comboBox5.getSelectedItem() ;
        String[] tab = new String[2] ;
        String[] tab2 = new String[1] ;
        tab = heure.split(":") ;
        tab2 = duree.split("h") ;
        int heureLibre = Integer.parseInt(tab[0]) ;
        int dureeLibre = Integer.parseInt(tab2[0]) ;
        this.controller.creerCreneauLibre(MySql.getConnection(), clinique, heureLibre, sqlDate, dureeLibre);

        this.labelReussi.setText("Créneaux insérés avec succès !");
        this.labelReussi.setForeground(new Color(0, 255, 0));
    }


    public Date getDateHeure(){
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter df =  DateTimeFormatter.ofPattern(pattern) ;
        LocalDate date = null ;
        if(this.DateBox1.getDate() != null) {
            date = LocalDate.parse(this.DateBox1.getDate().format(df)) ;
            Date sqlDate = Date.valueOf(date) ;
            return sqlDate;
        }
        else {
            return null;
        }
    }

    public void validerBoutonFiltreRdv(String etatFiltre) {
        this.rdvsMedecin.removeAll();
        Object valeur = comboBox1.getSelectedItem();
        String nomClinique = valeur.toString();


        if(etatFiltre == null){
            JLabel label = new JLabel("Veuillez remplir un filtre d'état du RDV !", JLabel.CENTER) ;
            label.setForeground(new Color(255, 0, 0));
            this.rdvsMedecin.add(label) ;
            actualiserPageGestionClient();
        }
        else if (etatFiltre != "Libre") {
            List<Rdv> rdvs = this.controller.getListRdvFlitre(MySql.getConnection(), etatFiltre, getDateHeure(), this.controller.getIdJointureByNomClinique(MySql.getConnection(), nomClinique));
            if (!rdvs.isEmpty()) {
                createTitle(true);
                for (int i = 0; i < rdvs.size(); i++) {
                    List<Client> listInfoClient = this.controller.getInfoClient(MySql.getConnection(), rdvs.get(i).getIdRdv());
                    for (int j = 0; j < listInfoClient.size(); j++) {
                        createPanel(nomClinique, rdvs.get(i).getDateTimeStamp(), listInfoClient.get(j), etatFiltre, i+1) ;
                    }
                }
            } else {
                JLabel label = new JLabel("Aucun rendez-vous trouvé avec ces critères !", JLabel.CENTER);
                label.setForeground(new Color(159, 0, 13));
                this.rdvsMedecin.add(label);
            }
        }
        else {
            List<Rdv> rdvs = this.controller.getListRdvFlitre(MySql.getConnection(), etatFiltre, getDateHeure(), this.controller.getIdJointureByNomClinique(MySql.getConnection(), nomClinique));
            List<Creneau> creneauLibre = this.controller.getRdvLibre(MySql.getConnection(), nomClinique, getDateHeure()) ;
            if(!creneauLibre.isEmpty()){
                createTitle(false);
                for (int i = 0; i< creneauLibre.size(); i++){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Timestamp timestamp = null ;
                    try {
                        timestamp = new Timestamp(dateFormat.parse(creneauLibre.get(i).getDateAndTimeString()).getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    createPanel(nomClinique, timestamp , null, etatFiltre, i+1);
                }
            }
            else {
                JLabel label = new JLabel("Aucun rendez-vous trouvé avec ces critères !", JLabel.CENTER);
                label.setForeground(new Color(159, 0, 13));
                this.rdvsMedecin.add(label) ;
            }
        }
        this.rdvsMedecin.revalidate();
        this.rdvsMedecin.repaint();
    }

    public void createTitle(boolean client){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.33;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;

        JLabel label = new JLabel("Clinique :", JLabel.CENTER) ;
        label.setForeground(new Color(87, 120, 147));
        this.rdvsMedecin.add(label, constraints) ;

        constraints.gridx = 1 ;
        JLabel label2 = new JLabel("Date :", JLabel.CENTER) ;
        label2.setForeground(new Color(87, 120, 147));
        this.rdvsMedecin.add(label2, constraints) ;

        if(client == true) {
            constraints.gridx = 2;
            JLabel label3 = new JLabel("Client :", JLabel.CENTER);
            label3.setForeground(new Color(87, 120, 147));
            this.rdvsMedecin.add(label3, constraints);

            constraints.gridx = 3;
            JLabel label4 = new JLabel("Etat :", JLabel.CENTER);
            label4.setForeground(new Color(87, 120, 147));
            this.rdvsMedecin.add(label4, constraints);

            JSeparator line4 = new JSeparator(SwingConstants.HORIZONTAL);
            constraints.gridy = 1;
            constraints.gridx = 3;
            this.rdvsMedecin.add(line4, constraints) ;
        }
        else{
            constraints.gridx = 2;
            JLabel label3 = new JLabel("Client :", JLabel.CENTER);
            label3.setForeground(new Color(87, 120, 147));
            this.rdvsMedecin.add(label3, constraints);
        }

        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        JSeparator line2 = new JSeparator(SwingConstants.HORIZONTAL);
        JSeparator line3 = new JSeparator(SwingConstants.HORIZONTAL);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        this.rdvsMedecin.add(line, constraints) ;
        constraints.gridx = 1;
        this.rdvsMedecin.add(line2, constraints) ;
        constraints.gridx = 2;
        this.rdvsMedecin.add(line3, constraints) ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(Libre)) {
            this.Libre.setName("Libre");
            if(this.Libre.isSelected()){
                checkBox(this.Libre);
            }

        }
        else if (e.getSource().equals(Réservé)) {
            this.Réservé.setName("Reserve");
            if(this.Réservé.isSelected()){
                checkBox(this.Réservé);
            }
        }
        else if (e.getSource().equals(Archivé)) {
            this.Archivé.setName("Archive");
            if(this.Archivé.isSelected()){
                checkBox(this.Archivé);
            }
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
        else if(e.getSource().equals(this.validerBouttonCreneau)){
            validerAjoutCreneau();
        }
        else if (e.getSource().equals(this.validerButton)){
            if (this.Libre.isSelected()){
                validerBoutonFiltreRdv(this.Libre.getName());
            } else if (this.Réservé.isSelected()) {
                validerBoutonFiltreRdv(this.Réservé.getName());
            } else if (this.Archivé.isSelected()) {
                validerBoutonFiltreRdv(this.Archivé.getName());
            }
            else {
                validerBoutonFiltreRdv(null);
            }
        }
    }

}
