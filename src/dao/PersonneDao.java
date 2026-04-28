package dao;

import java.sql.*;
import java.util.*;

public class PersonneDAO {

    Connection con = DatabaseConnection.getConnection();

    public void save(int id, String nom, String prenom, String email, Date naissance) throws SQLException {
        String sql = "INSERT INTO Personne VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, nom);
        ps.setString(3, prenom);
        ps.setString(4, email);
        ps.setDate(5, naissance);
        ps.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Personne WHERE id_personne=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public ResultSet findById(int id) throws SQLException {
        String sql = "SELECT * FROM Personne WHERE id_personne=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeQuery();
    }

    public ResultSet findAll() throws SQLException {
        String sql = "SELECT * FROM Personne";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }
}