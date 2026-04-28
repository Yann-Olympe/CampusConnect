package models;

import java.time.LocalDate;

public class Inscription {
    private int id;
    private Etudiant etudiant;
    private Groupe groupe;
    private LocalDate dateInscription;

    public Inscription() {}

    public Inscription(int id, Etudiant etudiant, Groupe groupe, LocalDate dateInscription) {
        this.id = id;
        this.etudiant = etudiant;
        this.groupe = groupe;
        this.dateInscription = dateInscription;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }

    public Groupe getGroupe() { return groupe; }
    public void setGroupe(Groupe groupe) { this.groupe = groupe; }

    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }
}