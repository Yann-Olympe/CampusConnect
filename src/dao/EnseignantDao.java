package dao;

import java.sql.*;

import util.DatabaseConnection;

public class EnseignantDao {

    Connection con = DatabaseConnection.getConnection();

    public void save(String statut, String dept, int idPersonne) throws SQLException {
        String sql = "INSERT INTO Enseignant (statut, departement, id_personne) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, statut);
        ps.setString(2, dept);
        ps.setInt(3, idPersonne);
        ps.executeUpdate();
    }

    public ResultSet findAll() throws SQLException {
        return con.prepareStatement("SELECT * FROM Enseignant").executeQuery();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Enseignant WHERE id_enseignant=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}