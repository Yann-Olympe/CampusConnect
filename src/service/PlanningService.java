package service;

import java.util.ArrayList;
import java.util.List;

import exception.CapaciteSalleInsuffisante;
import exception.ConflitHoraireException;
import models.Seance;

public class PlanningService {
	
	private List<Seance> seances = new ArrayList<Seance>();
	
	public void verifierConflits(Seance nouvelle) throws ConflitHoraireException {
		for (Seance existante : seances) {
			
			if(!existante.chevauche(nouvelle)) continue; // on skips a une autre iteration si la seance actuel ne chevauche aucune autre
			        verifierSalle(existante, nouvelle);
			        verifierEnseignant(existante, nouvelle);
			        verifierGroupe(existante, nouvelle);
			    
			}

	}

	public void verifierSalle(Seance a , Seance b) throws ConflitHoraireException {
		if (a.getSalle().equals(b.getSalle())) {
			throw new ConflitHoraireException("Salle Occupée");
		}
	}
	public void verifierGroupe(Seance a , Seance b) throws ConflitHoraireException {
		if (a.getGroupe().equals(b.getGroupe())) {
			throw new ConflitHoraireException("Ce Groupe a deja une seance");
		}
		
		public void verifierEnseignant(Seance a , Seance b) throws ConflitHoraireException {
			if (a.getSalle().equals(b.getSalle())) {
				throw new ConflitHoraireException("Cet enseignant a deja une seance programmé");
			}
		
	}
	public void ajouterSeance (Seance seance) {
		verifierConflits(seance);
		seances.add(seance);
	}

}

