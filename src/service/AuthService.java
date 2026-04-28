package services;

import dao.EtudiantDao;
import dao.EnseignantDao;
import models.Etudiant;
import models.Enseignant;
import models.Personne;

public class AuthService {
    private EtudiantDao etudiantDao;
    private EnseignantDao enseignantDao;

    public AuthService() {
        this.etudiantDao = new EtudiantDao();
        this.enseignantDao = new EnseignantDao();
    }

    public Personne login(String email, String password) {
        // Essayer connexion étudiant
        Etudiant etudiant = etudiantDao.login(email, password);
        if (etudiant != null) {
            return etudiant;
        }

        // Essayer connexion enseignant
        Enseignant enseignant = enseignantDao.login(email, password);
        if (enseignant != null) {
            return enseignant;
        }

        return null;
    }

    public String getRole(Personne personne) {
        if (personne instanceof Etudiant) {
            return "ETUDIANT";
        } else if (personne instanceof Enseignant) {
            return "ENSEIGNANT";
        }
        return "INCONNU";
    }

    public boolean changerMotDePasseEtudiant(int idEtudiant, String oldPassword, String newPassword) {
        Etudiant etudiant = etudiantDao.findById(idEtudiant);
        if (etudiant != null && etudiant.getMotDePasse().equals(oldPassword)) {
            etudiant.setMotDePasse(newPassword);
            return etudiantDao.update(etudiant);
        }
        return false;
    }

    public boolean changerMotDePasseEnseignant(int idEnseignant, String oldPassword, String newPassword) {
        Enseignant enseignant = enseignantDao.findById(idEnseignant);
        if (enseignant != null && enseignant.getMotDePasse().equals(oldPassword)) {
            enseignant.setMotDePasse(newPassword);
            return enseignantDao.update(enseignant);
        }
        return false;
    }
}