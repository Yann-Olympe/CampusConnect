package dao;

import java.sql.*;

import util.DatabaseConnection;

public class GroupeDao {

    Connection con = DatabaseConnection.getConnection();

    public void save(String type, int capacite, int idCours, int idEns) throws SQLException {
        String sql = "INSERT INTO Groupe VALUES (NULL, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, type);
        ps.setInt(2, capacite);
        ps.setInt(3, idCours);
        ps.setInt(4, idEns);
        ps.executeUpdate();
    }
}