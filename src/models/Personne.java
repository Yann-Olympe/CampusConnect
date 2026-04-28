package models;

import java.time.LocalDate;

public abstract class Personne {
    protected int id;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String email;
    protected LocalDate dateNaissance;
    protected String motDePasse;

    public Personne() {}

    public Personne(int id, String matricule, String nom, String prenom, 
                    String email, LocalDate dateNaissance, String motDePasse) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.motDePasse = motDePasse;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public abstract String getRole();
}