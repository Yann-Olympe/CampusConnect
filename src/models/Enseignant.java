package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Enseignant extends Personne {
    private String statut;
    private String departement;
    private List<Cours> cours;
    private List<Groupe> groupes;

    public Enseignant() {
        super();
        this.cours = new ArrayList<>();
        this.groupes = new ArrayList<>();
    }

    public Enseignant(int id, String matricule, String nom, String prenom, 
                      String email, LocalDate dateNaissance, String motDePasse, 
                      String statut, String departement) {
        super(id, matricule, nom, prenom, email, dateNaissance, motDePasse);
        this.statut = statut;
        this.departement = departement;
        this.cours = new ArrayList<>();
        this.groupes = new ArrayList<>();
    }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public List<Cours> getCours() { return cours; }
    public void setCours(List<Cours> cours) { this.cours = cours; }

    public List<Groupe> getGroupes() { return groupes; }
    public void setGroupes(List<Groupe> groupes) { this.groupes = groupes; }

    @Override
    public String getRole() {
        return "ENSEIGNANT";
    }

    public int getNombreCours() {
        return cours.size();
    }

    public int getNombreGroupes() {
        return groupes.size();
    }
}