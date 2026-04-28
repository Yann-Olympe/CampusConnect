package models;

import java.time.LocalDate;

public class Note {
    private int id;
    private boolean sn; // true = SN, false = CC
    private Double valeur;
    private LocalDate dateEvaluation;
    private boolean dispo;
    private Etudiant etudiant;
    private Groupe groupe;

    public Note() {}

    public Note(int id, boolean sn, Double valeur, LocalDate dateEvaluation, 
                boolean dispo, Etudiant etudiant, Groupe groupe) {
        this.id = id;
        this.sn = sn;
        this.valeur = valeur;
        this.dateEvaluation = dateEvaluation;
        this.dispo = dispo;
        this.etudiant = etudiant;
        this.groupe = groupe;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isSn() { return sn; }
    public void setSn(boolean sn) { this.sn = sn; }

    public Double getValeur() { return valeur; }
    public void setValeur(Double valeur) { this.valeur = valeur; }

    public LocalDate getDateEvaluation() { return dateEvaluation; }
    public void setDateEvaluation(LocalDate dateEvaluation) { this.dateEvaluation = dateEvaluation; }

    public boolean isDispo() { return dispo; }
    public void setDispo(boolean dispo) { this.dispo = dispo; }

    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }

    public Groupe getGroupe() { return groupe; }
    public void setGroupe(Groupe groupe) { this.groupe = groupe; }

    public String getType() {
        return sn ? "SN" : "CC";
    }
}