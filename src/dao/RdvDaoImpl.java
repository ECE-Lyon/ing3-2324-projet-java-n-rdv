package dao;

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
            preparedStatement.execute();
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
            /*preparedStatement.setDate(3,rdv.getDate());*/
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


}
