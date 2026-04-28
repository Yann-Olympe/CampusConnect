package services;

import dao.NoteDao;
import dao.GroupeDao;
import dao.CoursDao;
import models.Note;
import models.Groupe;
import models.Cours;
import models.Etudiant;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NoteService {
    private NoteDao noteDao;
    private GroupeDao groupeDao;
    private CoursDao coursDao;

    public NoteService() {
        this.noteDao = new NoteDao();
        this.groupeDao = new GroupeDao();
        this.coursDao = new CoursDao();
    }

    public boolean saisirNote(Etudiant etudiant, Groupe groupe, double valeur, 
                              boolean isSN, LocalDate dateEvaluation) {
        Note note = new Note();
        note.setEtudiant(etudiant);
        note.setGroupe(groupe);
        note.setValeur(valeur);
        note.setSn(isSN);
        note.setDateEvaluation(dateEvaluation);
        note.setDispo(false);
        return noteDao.create(note);
    }

    public boolean modifierNote(Note note, double nouvelleValeur) {
        note.setValeur(nouvelleValeur);
        return noteDao.update(note);
    }

    public Note trouverParId(int id) {
        return noteDao.findById(id);
    }

    public List<Note> trouverToutes() {
        return noteDao.findAll();
    }

    public List<Note> trouverParEtudiant(int idEtudiant) {
        return noteDao.findByEtudiant(idEtudiant);
    }

    public List<Note> trouverParEtudiantEtGroupe(int idEtudiant, int idGroupe) {
        return noteDao.findByEtudiantAndGroupe(idEtudiant, idGroupe);
    }

    public boolean supprimerNote(int id) {
        return noteDao.delete(id);
    }

    public boolean rendreNotesDisponibles(int idGroupe) {
        return noteDao.rendreDisponible(idGroupe);
    }

    public double calculerMoyenneCC(int idEtudiant, int idGroupe) {
        List<Note> notes = noteDao.findByEtudiantAndGroupe(idEtudiant, idGroupe);
        double somme = 0;
        int count = 0;
        for (Note n : notes) {
            if (!n.isSn() && n.getValeur() != null) {
                somme += n.getValeur();
                count++;
            }
        }
        return count > 0 ? somme / count : 0;
    }

    public double getNoteSN(int idEtudiant, int idGroupe) {
        List<Note> notes = noteDao.findByEtudiantAndGroupe(idEtudiant, idGroupe);
        for (Note n : notes) {
            if (n.isSn() && n.getValeur() != null) {
                return n.getValeur();
            }
        }
        return 0;
    }

    public double calculerMoyenneFinaleCours(int idEtudiant, int idCours) {
        List<Groupe> groupes = groupeDao.findByCoursAndEtud(idCours, idEtudiant);
        double sommeCC = 0;
        int countCC = 0;
        double noteSN = 0;
        boolean hasSN = false;

        for (Groupe g : groupes) {
            List<Note> notes = noteDao.findByEtudiantAndGroupe(idEtudiant, g.getId());
            for (Note n : notes) {
                if (n.getValeur() != null) {
                    if (n.isSn()) {
                        noteSN = n.getValeur();
                        hasSN = true;
                    } else {
                        sommeCC += n.getValeur();
                        countCC++;
                    }
                }
            }
        }

        double moyenneCC = countCC > 0 ? sommeCC / countCC : 0;
        return hasSN ? (moyenneCC * 0.3 + noteSN * 0.7) : 0;
    }

    public Map<String, Double> getReleveComplet(int idEtudiant) {
        Map<String, Double> releve = new HashMap<>();
        List<Note> toutesNotes = noteDao.findByEtudiant(idEtudiant);
        
        // Grouper par cours via les groupes
        Map<Integer, List<Note>> notesParCours = new HashMap<>();
        for (Note n : toutesNotes) {
            int idCours = n.getGroupe().getCours().getId();
            if (!notesParCours.containsKey(idCours)) {
                notesParCours.put(idCours, new ArrayList<>());
            }
            notesParCours.get(idCours).add(n);
        }

        // Calculer moyenne pour chaque cours
        for (Map.Entry<Integer, List<Note>> entry : notesParCours.entrySet()) {
            int idCours = entry.getKey();
            List<Note> notes = entry.getValue();
            
            double sommeCC = 0;
            int countCC = 0;
            double noteSN = 0;
            boolean hasSN = false;

            for (Note n : notes) {
                if (n.getValeur() != null) {
                    if (n.isSn()) {
                        noteSN = n.getValeur();
                        hasSN = true;
                    } else {
                        sommeCC += n.getValeur();
                        countCC++;
                    }
                }
            }

            double moyenneCC = countCC > 0 ? sommeCC / countCC : 0;
            double moyenneFinale = hasSN ? (moyenneCC * 0.3 + noteSN * 0.7) : 0;
            
            Cours cours = coursDao.findById(idCours);
            releve.put(cours.getIntitule(), moyenneFinale);
        }

        return releve;
    }
}