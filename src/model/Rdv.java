package model;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Rdv {
    private Timestamp dateHeure;
    private String note;
    private String etatRdv;
    private int idRdv;
    private int idJointure;
    private Client client  ;


    public Rdv(int idRdv, int idJointure, String note,  String etat,Timestamp dateHeure){
        this.idRdv = idRdv;
        this.idJointure = idJointure;
        this.note = note;
        this.etatRdv = etat ;
        this.dateHeure =dateHeure;
    }

    public int getIdJointure() { return idJointure; }

    public void setEtat(String etat){ this.etatRdv = etat ;}
    public String getEtat(){ return this.etatRdv ;}

    public int getIdRdv() {
        return idRdv;
    }
    public void setIdRdv(int idRdv) {
        this.idRdv = idRdv;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String getNote() {
        return note;
    }

    public void setDate(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }
    public Timestamp getDateTimeStamp() {
        return dateHeure;
    }


    public String getDateString() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern) ;
        return df.format(this.dateHeure) ;
    }

    public String getHeureString() {
        String pattern = "HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern) ;
        return df.format(this.dateHeure) ;
    }


    public String getDayString() {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern) ;
        return df.format(this.dateHeure) ;
    }

    public void setRdvClient(Client client) {
        this.client = client;
    }
    public Client getRdvClient(){ return this.client ;}
}
