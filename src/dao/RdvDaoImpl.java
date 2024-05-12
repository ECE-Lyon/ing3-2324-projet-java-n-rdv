package dao;

import com.github.lgooddatepicker.components.DatePicker;
import model.Clinique;
import model.Medecin;
import model.Rdv;
import model.Client;
import model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RdvDaoImpl implements RdvDao{
    private Connection connection;
    public RdvDaoImpl(Connection connection){this.connection = connection;}
    @Override
    public int getIdJointure(Rdv rdv, int idRdv) throws SQLException{
        int idJointure = 0;
        try(PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT idJointure FROM medecin_clinique " +
                "where idRdv = ?")){
            preparedStatement1.setInt(1, rdv.getIdRdv());
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()){
                idJointure = resultSet.getInt(5);
            }
        }
        return idJointure;
    }
    @Override
    public void addRdv(Creneau creneau, int idClient) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(creneau.getDate());
        calendar.set(Calendar.HOUR, creneau.getTime()) ;
        Timestamp time = new Timestamp(calendar.getTimeInMillis()) ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rdv(note, heure, idClient, idJointure, etat)" +
                " VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, "RDV à venir");
            preparedStatement.setTimestamp(2, time);
            preparedStatement.setInt(3, idClient);
            preparedStatement.setInt(4, creneau.getIdJointure());
            preparedStatement.setString(5, "Reserve");
    }
    }

    @Override
    public Rdv getRdv(String etat) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT (idJointure, heure) FROM rdv where Etat = ?")){
            preparedStatement.setString(1, etat);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int i = resultSet.getInt(5);
            }
            return null;
        }
    }
    @Override
    public void deleteRdv(int id) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM rdv where idRdv = ?")){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }
    @Override
    public void updateRdv (Rdv rdv) throws SQLException{
        try(PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rdv SET idRdv = ?, note = ?, heure = ?")){
            preparedStatement.setInt(1,rdv.getIdRdv());
            preparedStatement.setString(2, rdv.getNote());
            preparedStatement.setDate(3,null);
            preparedStatement.execute();
        }
    }

    @Override
    public List<Rdv> getRdvByClientId(int id) throws SQLException {
        List<Rdv> rdv = new ArrayList<>() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rdv where idClient = ?")){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idRdv = resultSet.getInt("idRdv");
                int idJointure = resultSet.getInt("idJointure");
                String note = resultSet.getString("note");
                String etat = resultSet.getString("etat") ;
                Timestamp time = resultSet.getTimestamp("heure") ;
                rdv.add(new Rdv(idRdv, idJointure, note, etat, time)) ;
            }
            return rdv;
        }
    }


    public List<Rdv> getRdvLibreAvecFiltre(String medecin, String clinique, Date date) throws SQLException {
        List<Rdv> rdv = new ArrayList<>() ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rdv where idMedecin")){
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int idRdv = resultSet.getInt("idRdv");
                int idJointure = resultSet.getInt("idJointure");
                String note = resultSet.getString("note");
                String etat = resultSet.getString("etat") ;
                Timestamp time = resultSet.getTimestamp("heure") ;
                rdv.add(new Rdv(idRdv, idJointure, note, etat, time)) ;
            }
            return rdv;
        }
    }

    public List<Rdv> getRdvFiltres(String etat, Timestamp date, List<Integer> listIdJointure) throws SQLException {
        List<Rdv> listRdv = new ArrayList<>();
        for (int i = 0; i < listIdJointure.size(); i++) {
            try (PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM rdv WHERE " +
                    "Etat = COALESCE(?, Etat) AND heure = COALESCE(?, heure) AND idJointure = COALESCE(?, idJointure)")) {
                if (etat == null) {
                    preparedStatement1.setString(1, null);
                } else {
                    preparedStatement1.setString(1, etat);
                }
                if (date == null) {
                    preparedStatement1.setString(2, null);
                } else {
                    preparedStatement1.setTimestamp(2, date);
                }
                if (listIdJointure == null) {
                    preparedStatement1.setString(3, null);
                } else {
                    preparedStatement1.setInt(3, listIdJointure.get(i));
                }
                ResultSet resultSet = preparedStatement1.executeQuery();
                while (resultSet.next()) {
                    listRdv.add(new Rdv(resultSet.getInt("idRdv"), resultSet.getInt("idJointure"), resultSet.getString("note"), resultSet.getString("Etat"), resultSet.getTimestamp("heure")));
                }
            }
        }
        return listRdv;
    }


}
