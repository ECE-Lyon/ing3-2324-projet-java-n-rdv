package model;
import model.Client;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Rdv {
    private LocalDateTime dateHeure;
    private Date date;
    private String note;
    private int idRdv;
    public Rdv(int idRdv, String note, LocalDateTime dateHeure){
        this.idRdv = idRdv;
        this.note = note;
        this.dateHeure =dateHeure;
    }
    public Rdv(int idRdv, String note, Date date){
        this.idRdv = idRdv;
        this.note = note;
        this.date = date;
    }
    public int getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(int idRdv) {
        this.idRdv = idRdv;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getdateHeure() {
        return dateHeure;
    }

    public void setHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
