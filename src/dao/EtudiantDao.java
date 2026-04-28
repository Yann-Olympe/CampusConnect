package dao;

import java.sql.*;

import util.DatabaseConnection;

public class EtudiantDaoS {

    Connection con = DatabaseConnection.getConnection();

    public void save(String matricule, String annee, String filiere, int idPersonne) throws SQLException {
        String sql = "INSERT INTO Etudiant (matricule, annee, filiere, id_personne) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, matricule);
        ps.setString(2, annee);
        ps.setString(3, filiere);
        ps.setInt(4, idPersonne);
        ps.executeUpdate();
    }

    public ResultSet findByMatricule(String matricule) throws SQLException {
        String sql = "SELECT * FROM Etudiant WHERE matricule=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, matricule);
        return ps.executeQuery();
    }

    public ResultSet findAll() throws SQLException {
        return con.prepareStatement("SELECT * FROM Etudiant").executeQuery();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Etudiant WHERE id_etudiant=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}