package models;

import java.util.ArrayList;
import java.util.List;

public class Cours {
    private int id;
    private String code;
    private String intitule;
    private String description;
    private int coefficient;
    private Classe classe;
    private Enseignant enseignant;
    private List<Groupe> groupes;

    public Cours() {
        this.groupes = new ArrayList<>();
    }

    public Cours(int id, String code, String intitule, String description, 
                 int coefficient, Classe classe, Enseignant enseignant) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
        this.description = description;
        this.coefficient = coefficient;
        this.classe = classe;
        this.enseignant = enseignant;
        this.groupes = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCoefficient() { return coefficient; }
    public void setCoefficient(int coefficient) { this.coefficient = coefficient; }

    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }

    public Enseignant getEnseignant() { return enseignant; }
    public void setEnseignant(Enseignant enseignant) { this.enseignant = enseignant; }

    public List<Groupe> getGroupes() { return groupes; }
    public void setGroupes(List<Groupe> groupes) { this.groupes = groupes; }
}