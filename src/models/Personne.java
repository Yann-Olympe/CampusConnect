package models;

import java.time.LocalDate;
import java.util.Date;

public abstract class Personne {
	protected  String id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected LocalDate dateNaissance;
    
    public Personne (String id, String nom,String prenom,String email,LocalDate dateNaissance) {
    	this.id = id;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.email = email;
    	this.dateNaissance = dateNaissance;
    }
    
    // getters
    public String getId() {return id;}
    public String getNom() {return nom;}
    public String getPrenom() {return prenom;}
    public String getEmail() {return email;}
    public LocalDate getDateNaissance() {return dateNaissance;}
    
    //setters
    public void setId(String id) {this.id = id;}
    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setEmail(String email) {this.email = email;}
    public void setDateNaissance(LocalDate dateNaissance) {this.dateNaissance = dateNaissance;}
  
    //public abstract void afficherDetails();
   
	
	

}
