package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Cours;
import models.Classe;
import models.Enseignant;

public class CoursDao implements IDao<Cours> {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public boolean create(Cours c) {
        conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Cours (code, intitule, description, coefficient, id_classe, id_enseignant) VALUES (?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getCode());
            stmt.setString(2, c.getIntitule());
            stmt.setString(3, c.getDescription());
            stmt.setInt(4, c.getCoefficient());
            stmt.setInt(5, c.getClasse().getId());
            stmt.setInt(6, c.getEnseignant().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    @Override
    public Cours findById(int id) {
        conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT c.*, cl.nom as classe_nom, cl.code as classe_code, cl.niveau, cl.filiere, " +
                     "e.matricule as ens_matricule, e.nom as ens_nom, e.prenom as ens_prenom " +
                     "FROM Cours c " +
                     "LEFT JOIN Classe cl ON c.id_classe = cl.id " +
                     "LEFT JOIN Enseignant e ON c.id_enseignant = e.id " +
                     "WHERE c.id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapCours(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return null;
    }

    @Override
    public List<Cours> findAll() {
        conn = DBConnection.getInstance().getConnection();
        List<Cours> cours = new ArrayList<>();
        String sql = "SELECT c.*, cl.nom as classe_nom, cl.code as classe_code, cl.niveau, cl.filiere, " +
                     "e.matricule as ens_matricule, e.nom as ens_nom, e.prenom as ens_prenom " +
                     "FROM Cours c " +
                     "LEFT JOIN Classe cl ON c.id_classe = cl.id " +
                     "LEFT JOIN Enseignant e ON c.id_enseignant = e.id";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cours.add(mapCours(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return cours;
    }

    @Override
    public boolean update(Cours c) {
        conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Cours SET code=?, intitule=?, description=?, coefficient=?, id_classe=?, id_enseignant=? WHERE id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getCode());
            stmt.setString(2, c.getIntitule());
            stmt.setString(3, c.getDescription());
            stmt.setInt(4, c.getCoefficient());
            stmt.setInt(5, c.getClasse().getId());
            stmt.setInt(6, c.getEnseignant().getId());
            stmt.setInt(7, c.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    @Override
    public boolean delete(int id) {
        conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Cours WHERE id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public List<Cours> findByClasse(int idClasse) {
        conn = DBConnection.getInstance().getConnection();
        List<Cours> cours = new ArrayList<>();
        String sql = "SELECT c.*, cl.nom as classe_nom, cl.code as classe_code, cl.niveau, cl.filiere, " +
                     "e.matricule as ens_matricule, e.nom as ens_nom, e.prenom as ens_prenom " +
                     "FROM Cours c " +
                     "LEFT JOIN Classe cl ON c.id_classe = cl.id " +
                     "LEFT JOIN Enseignant e ON c.id_enseignant = e.id " +
                     "WHERE c.id_classe = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idClasse);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cours.add(mapCours(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return cours;
    }

    public List<Cours> findByEnseignant(int idEnseignant) {
        conn = DBConnection.getInstance().getConnection();
        List<Cours> cours = new ArrayList<>();
        String sql = "SELECT c.*, cl.nom as classe_nom, cl.code as classe_code, cl.niveau, cl.filiere, " +
                     "e.matricule as ens_matricule, e.nom as ens_nom, e.prenom as ens_prenom " +
                     "FROM Cours c " +
                     "LEFT JOIN Classe cl ON c.id_classe = cl.id " +
                     "LEFT JOIN Enseignant e ON c.id_enseignant = e.id " +
                     "WHERE c.id_enseignant = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEnseignant);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cours.add(mapCours(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return cours;
    }

    private Cours mapCours(ResultSet rs) throws SQLException {
        Cours c = new Cours();
        c.setId(rs.getInt("id"));
        c.setCode(rs.getString("code"));
        c.setIntitule(rs.getString("intitule"));
        c.setDescription(rs.getString("description"));
        c.setCoefficient(rs.getInt("coefficient"));

        // Classe
        Classe cl = new Classe();
        cl.setId(rs.getInt("id_classe"));
        cl.setNom(rs.getString("classe_nom"));
        cl.setCode(rs.getString("classe_code"));
        cl.setNiveau(rs.getString("niveau"));
        cl.setFiliere(rs.getString("filiere"));
        c.setClasse(cl);

        // Enseignant
        Enseignant e = new Enseignant();
        e.setId(rs.getInt("id_enseignant"));
        e.setMatricule(rs.getString("ens_matricule"));
        e.setNom(rs.getString("ens_nom"));
        e.setPrenom(rs.getString("ens_prenom"));
        c.setEnseignant(e);
        return c;
    }
}