package dao;

import model.Clinique;
import model.Medecin;
import model.Rdv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JointureDaoImpl implements JointureDao {

    private Connection connection;
    public JointureDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Integer> getIdJointures(int idMedecin, int idClinique) throws SQLException{
        List<Integer> idJointures = new ArrayList<>() ;
        try(PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM medecin_clinique WHERE " +
                "idMedecin = COALESCE(?, idMedecin) AND idClinique = COALESCE(?, idClinique)")){
            if(idMedecin == -999){
                preparedStatement1.setString(1, null);
            }
            else{ preparedStatement1.setInt(1, idMedecin); }
            if(idClinique == -999){
                preparedStatement1.setString(2, null);
            }
            else {preparedStatement1.setInt(2, idClinique);}
            ResultSet resultSet = preparedStatement1.executeQuery();
            while(resultSet.next()){
                idJointures.add(resultSet.getInt("idJointure"));
            }
        }
        return idJointures;
    }

    @Override
    public void addJointure(List<Clinique> list, Medecin med) throws SQLException {
        for(int i = 0 ; i < list.size() ; i++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO medecin_clinique(idClinique, idMedecin, qualification)" +
                    " VALUES (?, ?, ?)")) {
                preparedStatement.setInt(1, list.get(i).getIdClinique());
                preparedStatement.setInt(2, med.getIdMedecin());
                preparedStatement.setString(3, med.getSpecification());
                preparedStatement.execute();
            }
        }
    }

    public int[] getMedecinByIdJointure(int idJointure) throws SQLException{
        int[] ids = new int[2] ;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medecin_clinique where idJointure = ?")){
            preparedStatement.setInt(1, idJointure);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ids[0] = resultSet.getInt("idMedecin");
                ids[1] = resultSet.getInt("idClinique");
            }
            return ids ;
        }
    }
    public int getIdMedecinByIdClinique(int idClinique) throws SQLException{
        int idMedecin = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM medecin_clinique where idClinique = ?")){
            preparedStatement.setInt(1, idClinique);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                idMedecin = resultSet.getInt("idMedecin");
            }
            return idMedecin ;
        }
    }
}
