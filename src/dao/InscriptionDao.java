package dao;

import java.sql.*;

import util.DatabaseConnection;

public class InscriptionDao {

    Connection con = DatabaseConnection.getConnection();

    public void inscrire(int idEtudiant, int idGroupe) throws SQLException {
        String sql = "INSERT INTO Inscription (id_etudiant, id_groupe) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEtudiant);
        ps.setInt(2, idGroupe);
        ps.executeUpdate();
    }
}