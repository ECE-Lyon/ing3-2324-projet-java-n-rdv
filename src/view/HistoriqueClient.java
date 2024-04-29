package view;

import model.Medecin;
import model.Rdv;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueClient extends JDialog {


    private JPanel contentPane;
    private JPanel rdvPasse;
    private JPanel rdvFutur;


    public HistoriqueClient(List<Rdv> rdv, List<Medecin> medecin) {
        this.setContentPane(contentPane);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.rdvFutur.setLayout(new GridLayout(0,1));
        this.rdvPasse.setLayout(new GridLayout(0,1));

        for(int i =0 ; i < rdv.size() ; i++){
            if(rdv.get(i).getDateTimeStamp().before(new Timestamp(System.currentTimeMillis()))) {
                this.rdvPasse.add(createPanelRdv(rdv.get(i), medecin.get(i))) ;
            }
            else {
                this.rdvFutur.add(createPanelRdv(rdv.get(i), medecin.get(i))) ;
            }
        }

        this.pack();
        this.setVisible(true);
    }


    public JPanel createPanelRdv(Rdv rdvv, Medecin med){
        JPanel panel = new JPanel() ;
        panel.setLayout(new FlowLayout());
        Border bord = BorderFactory.createEtchedBorder(EtchedBorder.RAISED) ;

        JPanel panel2 = new JPanel() ;
        panel2.setLayout(new BorderLayout(60, 10));
        panel2.setBorder(bord);
        panel2.add(new JLabel("Date: ", JLabel.CENTER) , BorderLayout.NORTH) ;
        panel2.add(new JLabel(rdvv.getDayString(), JLabel.CENTER), BorderLayout.CENTER) ;
        panel.add(panel2) ;

        JPanel panel3 = new JPanel() ;
        panel3.setLayout(new BorderLayout(60, 10));
        panel3.setBorder(bord);
        panel3.add(new JLabel("Heure: ", JLabel.CENTER) , BorderLayout.NORTH) ;
        panel3.add(new JLabel(rdvv.getHeureString(), JLabel.CENTER), BorderLayout.CENTER) ;
        panel.add(panel3) ;

        JPanel panel4 = new JPanel() ;
        panel4.setLayout(new BorderLayout(60, 10));
        panel4.setBorder(bord);
        panel4.add(new JLabel("Etat: ", JLabel.CENTER), BorderLayout.NORTH) ;
        panel4.add(new JLabel(rdvv.getEtat(), JLabel.CENTER), BorderLayout.CENTER) ;
        panel.add(panel4) ;

        JPanel panel5 = new JPanel() ;
        panel5.setLayout(new BorderLayout(60, 10));
        panel5.setBorder(bord);
        panel5.add(new JLabel("Medecin: ", JLabel.CENTER), BorderLayout.NORTH) ;
        panel5.add(new JLabel(med.getNom() + " "  + med.getPrenom(), JLabel.CENTER), BorderLayout.CENTER) ;
        panel.add(panel5) ;

        JPanel panel6 = new JPanel() ;
        panel6.setLayout(new BorderLayout(60, 10));
        panel6.setBorder(bord);
        panel6.add(new JLabel("Clinique: ", JLabel.CENTER), BorderLayout.NORTH) ;
        panel6.add(new JLabel(med.getCliniques().get(0).getLocalisation(), JLabel.CENTER), BorderLayout.CENTER) ;
        panel.add(panel6) ;

        //CHERCHER LE MEDECIN

        return panel ;
    }

}
