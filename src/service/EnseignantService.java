package services;

import dao.EnseignantDao;
import dao.CoursDao;
import dao.GroupeDao;
import models.Enseignant;
import models.Cours;
import models.Groupe;
import java.util.List;

public class EnseignantService {
    private EnseignantDao enseignantDao;
    private CoursDao coursDao;
    private GroupeDao groupeDao;

    public EnseignantService() {
        this.enseignantDao = new EnseignantDao();
        this.coursDao = new CoursDao();
        this.groupeDao = new GroupeDao();
    }

    public boolean creerEnseignant(Enseignant enseignant) {
        if (enseignant.getMatricule() == null || enseignant.getEmail() == null) {
            return false;
        }
        return enseignantDao.create(enseignant);
    }

    public Enseignant trouverParId(int id) {
        return enseignantDao.findById(id);
    }

    public List<Enseignant> trouverTous() {
        return enseignantDao.findAll();
    }

    public List<Enseignant> trouverParDepartement(String departement) {
        return enseignantDao.findByDepartement(departement);
    }

    public boolean modifierEnseignant(Enseignant enseignant) {
        return enseignantDao.update(enseignant);
    }

    public boolean supprimerEnseignant(int id) {
        return enseignantDao.delete(id);
    }

    public List<Cours> getCoursEnseignant(int idEnseignant) {
        return coursDao.findByEnseignant(idEnseignant);
    }

    public List<Groupe> getGroupesEnseignant(int idEnseignant) {
        return groupeDao.findByEnseignant(idEnseignant);
    }

    public int getNombreCours(int idEnseignant) {
        return coursDao.findByEnseignant(idEnseignant).size();
    }

    public int getNombreGroupes(int idEnseignant) {
        return groupeDao.findByEnseignant(idEnseignant).size();
    }
}