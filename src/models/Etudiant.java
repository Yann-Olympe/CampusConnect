package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Etudiant extends Personne implements NoteCalculator {
    private Classe classe;
    private List<Inscription> inscriptions;
    private List<Note> notes;

    public Etudiant() {
        super();
        this.inscriptions = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    public Etudiant(int id, String matricule, String nom, String prenom, 
                    String email, LocalDate dateNaissance, String motDePasse, Classe classe) {
        super(id, matricule, nom, prenom, email, dateNaissance, motDePasse);
        this.classe = classe;
        this.inscriptions = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public List<Inscription> getInscriptions() { return inscriptions; }
    public void setInscriptions(List<Inscription> inscriptions) { this.inscriptions = inscriptions; }

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }

    @Override
    public String getRole() {
        return "ETUDIANT";
    }

    @Override
    public double calculerMoyenneCC() {
        if (notes.isEmpty()) return 0;
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

    @Override
    public double calculerMoyenneCours() {
        double moyenneCC = 0;
        double noteSN = 0;
        int countCC = 0;
        boolean hasSN = false;

        for (Note n : notes) {
            if (n.isSn() && n.getValeur() != null) {
                noteSN = n.getValeur();
                hasSN = true;
            } else if (!n.isSn() && n.getValeur() != null) {
                moyenneCC += n.getValeur();
                countCC++;
            }
        }

        if (countCC > 0) moyenneCC /= countCC;
        return hasSN ? (moyenneCC * 0.3 + noteSN * 0.7) : 0;
    }

    @Override
    public double calculerMoyenneGenerale() {
        // À implémenter avec les données complètes
        return 0;
    }
}