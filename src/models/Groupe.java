package models;

import java.util.ArrayList;
import java.util.List;

public class Groupe {
    private int id;
    private String code;
    private String type; // CM, TD, TP
    private int capacite;
    private Cours cours;
    private Enseignant enseignant;
    private List<Inscription> inscriptions;
    private List<Seance> seances;

    public Groupe() {
        this.inscriptions = new ArrayList<>();
        this.seances = new ArrayList<>();
    }

    public Groupe(int id, String code, String type, int capacite, 
                  Cours cours, Enseignant enseignant) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.capacite = capacite;
        this.cours = cours;
        this.enseignant = enseignant;
        this.inscriptions = new ArrayList<>();
        this.seances = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public Cours getCours() { return cours; }
    public void setCours(Cours cours) { this.cours = cours; }

    public Enseignant getEnseignant() { return enseignant; }
    public void setEnseignant(Enseignant enseignant) { this.enseignant = enseignant; }

    public List<Inscription> getInscriptions() { return inscriptions; }
    public void setInscriptions(List<Inscription> inscriptions) { this.inscriptions = inscriptions; }

    public List<Seance> getSeances() { return seances; }
    public void setSeances(List<Seance> seances) { this.seances = seances; }

    public int getPlacesRestantes() {
        return capacite - inscriptions.size();
    }

    public boolean estPlein() {
        return inscriptions.size() >= capacite;
    }
}