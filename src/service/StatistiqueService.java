package services;

import dao.*;
import models.*;
import java.util.*;

public class StatistiqueService {
    private EtudiantDao etudiantDao;
    private ClasseDao classeDao;
    private CoursDao coursDao;
    private NoteService noteService;

    public StatistiqueService() {
        this.etudiantDao = new EtudiantDao();
        this.classeDao = new ClasseDao();
        this.coursDao = new CoursDao();
        this.noteService = new NoteService();
    }

    public Map<String, Double> getStatistiquesClasse(int idClasse) {
        Map<String, Double> stats = new HashMap<>();
        List<Etudiant> etudiants = etudiantDao.findByClasse(idClasse);
        
        if (etudiants.isEmpty()) {
            stats.put("moyenne", 0.0);
            stats.put("tauxReussite", 0.0);
            stats.put("effectif", 0.0);
            return stats;
        }

        double sommeMoyennes = 0;
        int nbAdmis = 0;

        for (Etudiant e : etudiants) {
            double moyenneGenerale = calculerMoyenneGeneraleEtudiant(e.getId(), idClasse);
            sommeMoyennes += moyenneGenerale;
            if (moyenneGenerale >= 10) {
                nbAdmis++;
            }
        }

        double moyenneClasse = sommeMoyennes / etudiants.size();
        double tauxReussite = (double) nbAdmis / etudiants.size() * 100;

        stats.put("moyenne", Math.round(moyenneClasse * 100.0) / 100.0);
        stats.put("tauxReussite", Math.round(tauxReussite * 100.0) / 100.0);
        stats.put("effectif", (double) etudiants.size());
        stats.put("nbAdmis", (double) nbAdmis);

        return stats;
    }

    public double calculerMoyenneGeneraleEtudiant(int idEtudiant, int idClasse) {
        List<Cours> coursList = coursDao.findByClasse(idClasse);
        double sommePonderee = 0;
        int totalCoefficients = 0;

        for (Cours c : coursList) {
            double moyenneCours = noteService.calculerMoyenneFinaleCours(idEtudiant, c.getId());
            sommePonderee += moyenneCours * c.getCoefficient();
            totalCoefficients += c.getCoefficient();
            c.toString();
        }

        return totalCoefficients > 0 ? sommePonderee / totalCoefficients : 0;
    }

    public List<Map<String, Object>> getClassementClasse(int idClasse) {
        List<Etudiant> etudiants = etudiantDao.findByClasse(idClasse);
        List<Map<String, Object>> classement = new ArrayList<>();

        for (Etudiant e : etudiants) {
            double moyenne = calculerMoyenneGeneraleEtudiant(e.getId(), idClasse);
            Map<String, Object> entry = new HashMap<>();
            entry.put("etudiant", e.getNomComplet());
            entry.put("matricule", e.getMatricule());
            entry.put("moyenne", Math.round(moyenne * 100.0) / 100.0);
            classement.add(entry);
        }

        // Trier par moyenne décroissante
        classement.sort((a, b) -> Double.compare((Double) b.get("moyenne"), 
                                                  (Double) a.get("moyenne")));

        // Ajouter le rang
        for (int i = 0; i < classement.size(); i++) {
            classement.get(i).put("rang", i + 1);
        }

        return classement;
    }

    public Map<String, Integer> getRepartitionNiveaux() {
        Map<String, Integer> repartition = new HashMap<>();
        List<Classe> classes = classeDao.findAll();
        
        for (Classe c : classes) {
            String niveau = c.getNiveau();
            int effectif = etudiantDao.countByClasse(c.getId());
            repartition.put(niveau, repartition.getOrDefault(niveau, 0) + effectif);
        }
        
        return repartition;
    }

    public Map<String, Integer> getStatistiquesGlobales() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalEtudiants", etudiantDao.findAll().size());
        stats.put("totalClasses", classeDao.findAll().size());
        stats.put("totalCours", coursDao.findAll().size());
        return stats;
    }

    public double getTauxReussiteGlobal() {
        List<Classe> classes = classeDao.findAll();
        int totalEtudiants = 0;
        int totalAdmis = 0;

        for (Classe c : classes) {
            Map<String, Double> stats = getStatistiquesClasse(c.getId());
            totalEtudiants += stats.get("effectif").intValue();
            totalAdmis += stats.get("nbAdmis").intValue();
        }

        return totalEtudiants > 0 ? (double) totalAdmis / totalEtudiants * 100 : 0;
    }
}