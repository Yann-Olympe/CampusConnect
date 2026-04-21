package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Seance {
        private Groupe groupe;
        private Enseignant enseignant;
        private Salle  salle;
        private LocalDate date;
        private LocalTime heureDebut;
        private LocalTime heureFin;

        public Seance(LocalDate date, LocalTime heureDebut, LocalTime heureFin) {
                this.date = date;
                this.heureDebut = heureDebut;
                this.heureFin = heureFin;
            }
        
        public boolean chevauche (Seance autre) {
        	
        	return this.date.equals(autre.date) && this.heureDebut.isBefore(autre.heureFin)   && this.heureFin.isAfter(autre.heureDebut);    	
        }
        
        
	        // getters
	     public Groupe getGroupe() {
			return groupe;
		}
	     public LocalTime getHeureDebut() {
			return heureDebut;
		}
	     public LocalDate getDate() {
			return date;
		}
	     public LocalTime getHeureFIn() {
			return heureFin;
		}
	     public Enseignant getEnseignant() {
			return enseignant;
		}
	     public Salle getSalle() {
			return salle;
		}
	     // setters
	     public void setDate(LocalDate date) {
			this.date = date;
		}
	     public void setEnseignant(Enseignant enseignant) {
			this.enseignant = enseignant;
		}
	     public void setGroupe(Groupe groupe) {
			this.groupe = groupe;
		}
	     public void setHeureDebut(LocalTime heureDebut) {
			this.heureDebut = heureDebut;
		}
	     public void setHeureFIn(LocalTime heureFin) {
			this.heureFin = heureFin;
		}
	     public void setSalle(Salle salle) {
			this.salle = salle;
		}
        
        
}
