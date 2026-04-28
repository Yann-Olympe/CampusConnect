package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

public class EnseignantPlanningController {

    @FXML private TableView<Seance> planningTable;
    @FXML private TableColumn<Seance, String> colJour, colDate, colDebut, colFin, colCours, colGroupe, colSalle, colType;

    public static class Seance {
        private final SimpleStringProperty jour, date, debut, fin, cours, groupe, salle, type;
        public Seance(String jour, String date, String debut, String fin, String cours, String groupe, String salle, String type) {
            this.jour = new SimpleStringProperty(jour);
            this.date = new SimpleStringProperty(date);
            this.debut = new SimpleStringProperty(debut);
            this.fin = new SimpleStringProperty(fin);
            this.cours = new SimpleStringProperty(cours);
            this.groupe = new SimpleStringProperty(groupe);
            this.salle = new SimpleStringProperty(salle);
            this.type = new SimpleStringProperty(type);
        }
        public String getJour() { return jour.get(); }
        public String getDate() { return date.get(); }
        public String getDebut() { return debut.get(); }
        public String getFin() { return fin.get(); }
        public String getCours() { return cours.get(); }
        public String getGroupe() { return groupe.get(); }
        public String getSalle() { return salle.get(); }
        public String getType() { return type.get(); }
    }

    @FXML
    public void initialize() {
        colJour.setCellValueFactory(new PropertyValueFactory<>("jour"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDebut.setCellValueFactory(new PropertyValueFactory<>("debut"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("fin"));
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colSalle.setCellValueFactory(new PropertyValueFactory<>("salle"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

        planningTable.setItems(FXCollections.observableArrayList(
            new Seance("Lundi", "28/04/2025", "08:00", "10:00", "POO Avancée", "TD2", "B204", "TD"),
            new Seance("Mardi", "29/04/2025", "10:00", "12:00", "Réseaux", "CM-A", "A101", "CM"),
            new Seance("Mercredi", "30/04/2025", "14:00", "16:00", "Bases de Données", "TP1", "Lab1", "TP")
        ));
    }
}