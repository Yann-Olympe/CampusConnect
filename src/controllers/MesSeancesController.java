package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;

public class MesSeancesController {
    @FXML private TableView<SeanceItem> seancesTable;
    @FXML private ComboBox<String> filtreCours, filtreStatut;

    public static class SeanceItem {
        private final SimpleStringProperty date, heureDebut, heureFin, cours, type, groupe, salle, enseignant, statut;
        public SeanceItem(String d, String hd, String hf, String c, String t, String g, String s, String e, String st) {
            this.date = new SimpleStringProperty(d);
            this.heureDebut = new SimpleStringProperty(hd);
            this.heureFin = new SimpleStringProperty(hf);
            this.cours = new SimpleStringProperty(c);
            this.type = new SimpleStringProperty(t);
            this.groupe = new SimpleStringProperty(g);
            this.salle = new SimpleStringProperty(s);
            this.enseignant = new SimpleStringProperty(e);
            this.statut = new SimpleStringProperty(st);
        }
        // getters et property methods...
    }

    @FXML public void initialize() {
        seancesTable.setItems(FXCollections.observableArrayList(
            new SeanceItem("Lun, 08/04/2024", "10h00", "12h00", "POO", "TD", "TD-2", "B204", "M. Nzambwe", "Planifiée"),
            new SeanceItem("Mar, 09/04/2024", "08h00", "10h00", "Bases de Données", "CM", "CM-A", "A101", "Mme Fokam", "Confirmée"),
            new SeanceItem("Mar, 09/04/2024", "14h00", "16h00", "Réseaux", "TP", "TP-1", "Lab1", "M. Ngulmatia", "En cours"),
            new SeanceItem("Mer, 10/04/2024", "10h00", "12h00", "Algorithmique", "TD", "TD-1", "B201", "M. Kouam", "Planifiée"),
            new SeanceItem("Jeu, 11/04/2024", "14h00", "16h00", "Anglais Technique", "CM", "CM-B", "A102", "Mme Abega", "Planifiée"),
            new SeanceItem("Ven, 12/04/2024", "08h00", "10h00", "POO", "CM", "CM-A", "A101", "M. Nzambwe", "Planifiée"),
            new SeanceItem("Ven, 12/04/2024", "14h00", "16h00", "Bases de Données", "TD", "TD-1", "B201", "Mme Fokam", "Planifiée")
        ));
        filtreCours.setItems(FXCollections.observableArrayList("Tous", "POO", "BDD", "Réseaux", "Algorithmique"));
        filtreStatut.setItems(FXCollections.observableArrayList("Tous", "Planifiée", "Confirmée", "En cours", "Terminée"));
    }
}