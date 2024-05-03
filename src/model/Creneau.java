package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Creneau {
    private int idCreneau ;
    private Date date ;
    private int time ;
    private int idJointure ;

    public Creneau(){}

    public Creneau(int idd,Date d, int t, int id){
        this.idCreneau = idd ;
        this.date = d ;
        this.time = t;
        this.idJointure = id ;
    }

    public int getIdCreneau() {return this.idCreneau ;}

    public int getIdJointure() { return this.idJointure; }
    public void setIdJointure(int i) { this.idJointure = i; }

    public Date getDate() {
        return this.date;
    }
    public String getDateString() {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern) ;
        return df.format(this.date) ;
    }

    public int getTime() {
        return this.time;
    }
}
