package models;

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private int id;
    private String nom;
    private String code;
    private String niveau;
    private String filiere;
    private String departement;
    private String anneeAcademique;
    private List<Etudiant> etudiants;
    private List<Cours> cours;

    public Classe() {
        this.etudiants = new ArrayList<>();
        this.cours = new ArrayList<>();
    }

    public Classe(int id, String nom, String code, String niveau, String filiere, 
                  String departement, String anneeAcademique) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.niveau = niveau;
        this.filiere = filiere;
        this.departement = departement;
        this.anneeAcademique = anneeAcademique;
        this.etudiants = new ArrayList<>();
        this.cours = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public String getAnneeAcademique() { return anneeAcademique; }
    public void setAnneeAcademique(String anneeAcademique) { this.anneeAcademique = anneeAcademique; }

    public List<Etudiant> getEtudiants() { return etudiants; }
    public void setEtudiants(List<Etudiant> etudiants) { this.etudiants = etudiants; }

    public List<Cours> getCours() { return cours; }
    public void setCours(List<Cours> cours) { this.cours = cours; }

    public int getNombreEtudiants() {
        return etudiants.size();
    }
}