package services;

import dao.InscriptionDao;
import dao.GroupeDao;
import models.Inscription;
import models.Groupe;
import models.Etudiant;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class InscriptionService {
    private InscriptionDao inscriptionDao;
    private GroupeDao groupeDao;

    public InscriptionService() {
        this.inscriptionDao = new InscriptionDao();
        this.groupeDao = new GroupeDao();
    }

    public boolean inscrireEtudiant(Etudiant etudiant, Groupe groupe) {
        // Vérifier si le groupe n'est pas plein
        int inscrits = groupeDao.countInscrits(groupe.getId());
        if (inscrits >= groupe.getCapacite()) {
            return false;
        }

        // Vérifier si déjà inscrit
        List<Inscription> inscriptions = inscriptionDao.findByEtudiant(etudiant.getId());
        for (Inscription i : inscriptions) {
            if (i.getGroupe().getId() == groupe.getId()) {
                return false; // Déjà inscrit
            }
        }

        Inscription inscription = new Inscription();
        inscription.setEtudiant(etudiant);
        inscription.setGroupe(groupe);
        inscription.setDateInscription(LocalDate.now());
        return inscriptionDao.create(inscription);
    }

    public boolean desinscrireEtudiant(int idEtudiant, int idGroupe) {
        return inscriptionDao.deleteByEtudiantAndGroupe(idEtudiant, idGroupe);
    }

    public Inscription trouverParId(int id) {
        return inscriptionDao.findById(id);
    }

    public List<Inscription> trouverToutes() {
        return inscriptionDao.findAll();
    }

    public List<Inscription> trouverParEtudiant(int idEtudiant) {
        return inscriptionDao.findByEtudiant(idEtudiant);
    }

    public List<Inscription> trouverParGroupe(int idGroupe) {
        return inscriptionDao.findByGroupe(idGroupe);
    }

    public boolean supprimerInscription(int id) {
        return inscriptionDao.delete(id);
    }

    public List<Etudiant> getEtudiantsGroupe(int idGroupe) {
        List<Inscription> inscriptions = inscriptionDao.findByGroupe(idGroupe);
        List<Etudiant> etudiants = new ArrayList<>();
        for (Inscription i : inscriptions) {
            etudiants.add(i.getEtudiant());
        }
        return etudiants;
    }

    public List<Groupe> getGroupesEtudiant(int idEtudiant) {
        List<Inscription> inscriptions = inscriptionDao.findByEtudiant(idEtudiant);
        List<Groupe> groupes = new ArrayList<>();
        for (Inscription i : inscriptions) {
            groupes.add(i.getGroupe());
        }
        return groupes;
    }

    public boolean peutInscrire(int idGroupe) {
        Groupe groupe = groupeDao.findById(idGroupe);
        if (groupe == null) return false;
        int inscrits = groupeDao.countInscrits(idGroupe);
        return inscrits < groupe.getCapacite();
    }
}