package dao;

import model.Creneau;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public interface AgendaDao {
    public List<Creneau> getCreneauLibre(List<Integer> idJointures, Date date) throws SQLException ;
    public boolean supprimerCreneauById(int idCreneau) throws SQLException ;
    public void addCreneau(int heure, Date date, int idJointure) throws SQLException ;
}
