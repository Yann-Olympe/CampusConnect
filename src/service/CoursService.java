package services;

import dao.CoursDao;
import dao.GroupeDao;
import models.Cours;
import models.Groupe;
import java.util.List;

public class CoursService {
    private CoursDao coursDao;
    private GroupeDao groupeDao;

    public CoursService() {
        this.coursDao = new CoursDao();
        this.groupeDao = new GroupeDao();
    }

    public boolean creerCours(Cours cours) {
        if (cours.getCode() == null || cours.getIntitule() == null) {
            return false;
        }
        return coursDao.create(cours);
    }

    public Cours trouverParId(int id) {
        return coursDao.findById(id);
    }

    public List<Cours> trouverTous() {
        return coursDao.findAll();
    }

    public List<Cours> trouverParClasse(int idClasse) {
        return coursDao.findByClasse(idClasse);
    }

    public List<Cours> trouverParEnseignant(int idEnseignant) {
        return coursDao.findByEnseignant(idEnseignant);
    }

    public boolean modifierCours(Cours cours) {
        return coursDao.update(cours);
    }

    public boolean supprimerCours(int id) {
        return coursDao.delete(id);
    }

    public List<Groupe> getGroupesCours(int idCours) {
        return groupeDao.findByCours(idCours);
    }

    public Cours getCoursComplet(int idCours) {
        Cours cours = coursDao.findById(idCours);
        if (cours != null) {
            cours.setGroupes(groupeDao.findByCours(idCours));
        }
        return cours;
    }

    public int getTotalCoefficients(int idClasse) {
        List<Cours> coursList = coursDao.findByClasse(idClasse);
        int total = 0;
        for (Cours c : coursList) {
            total += c.getCoefficient();
        }
        return total;
    }
}