package services;

import dao.ClasseDao;
import dao.EtudiantDao;
import dao.CoursDao;
import models.Classe;
import models.Etudiant;
import models.Cours;
import java.util.List;

public class ClasseService {
    private ClasseDao classeDao;
    private EtudiantDao etudiantDao;
    private CoursDao coursDao;

    public ClasseService() {
        this.classeDao = new ClasseDao();
        this.etudiantDao = new EtudiantDao();
        this.coursDao = new CoursDao();
    }

    public boolean creerClasse(Classe classe) {
        if (classe.getCode() == null || classe.getNom() == null) {
            return false;
        }
        return classeDao.create(classe);
    }

    public Classe trouverParId(int id) {
        return classeDao.findById(id);
    }

    public List<Classe> trouverToutes() {
        return classeDao.findAll();
    }

    public List<Classe> trouverParNiveau(String niveau) {
        return classeDao.findByNiveau(niveau);
    }

    public List<Classe> trouverParAnnee(String annee) {
        return classeDao.findByAnneeAcademique(annee);
    }

    public boolean modifierClasse(Classe classe) {
        return classeDao.update(classe);
    }

    public boolean supprimerClasse(int id) {
        return classeDao.delete(id);
    }

    public List<Etudiant> getEtudiantsClasse(int idClasse) {
        return etudiantDao.findByClasse(idClasse);
    }

    public List<Cours> getCoursClasse(int idClasse) {
        return coursDao.findByClasse(idClasse);
    }

    public int getEffectifClasse(int idClasse) {
        return etudiantDao.countByClasse(idClasse);
    }

    public Classe getClasseComplete(int idClasse) {
        Classe classe = classeDao.findById(idClasse);
        if (classe != null) {
            classe.setEtudiants(etudiantDao.findByClasse(idClasse));
            classe.setCours(coursDao.findByClasse(idClasse));
        }
        return classe;
    }
}