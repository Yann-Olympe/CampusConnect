package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EnseignantGroupesController {

    @FXML private TextField searchField;
    @FXML private ComboBox<String> coursFilter;
    @FXML private TableView<Groupe> groupesTable;
    @FXML private TableColumn<Groupe, String> colCours, colGroupe, colType, colSalle, colStatut;
    @FXML private TableColumn<Groupe, Integer> colCapacite, colInscrits;
    @FXML private TableColumn<Groupe, Void> colActions;

    public static class Groupe {
        private final SimpleStringProperty cours, groupe, type, salle, statut;
        private final SimpleIntegerProperty capacite, inscrits;
        public Groupe(String cours, String groupe, String type, int capacite, int inscrits, String salle, String statut) {
            this.cours = new SimpleStringProperty(cours);
            this.groupe = new SimpleStringProperty(groupe);
            this.type = new SimpleStringProperty(type);
            this.capacite = new SimpleIntegerProperty(capacite);
            this.inscrits = new SimpleIntegerProperty(inscrits);
            this.salle = new SimpleStringProperty(salle);
            this.statut = new SimpleStringProperty(statut);
        }
        public String getCours() { return cours.get(); }
        public String getGroupe() { return groupe.get(); }
        public String getType() { return type.get(); }
        public int getCapacite() { return capacite.get(); }
        public int getInscrits() { return inscrits.get(); }
        public String getSalle() { return salle.get(); }
        public String getStatut() { return statut.get(); }
    }

    @FXML
    public void initialize() {
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        colInscrits.setCellValueFactory(new PropertyValueFactory<>("inscrits"));
        colSalle.setCellValueFactory(new PropertyValueFactory<>("salle"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        colActions.setCellFactory(col -> new TableCell<Groupe, Void>() {
            private final Button btn = new Button("Voir étudiants");
            { btn.setOnAction(e -> {
                Groupe g = getTableView().getItems().get(getIndex());
                showEtudiants(g);
            }); }
           
        });

        ObservableList<Groupe> data = FXCollections.observableArrayList(
            new Groupe("POO Avancée", "CM-A", "CM", 80, 72, "A101", "Disponible"),
            new Groupe("POO Avancée", "TD-2", "TD", 30, 28, "B204", "Disponible"),
            new Groupe("Réseaux", "CM-A", "CM", 80, 65, "A102", "Disponible"),
            new Groupe("Bases de Données", "CM-A", "CM", 80, 80, "A101", "Complet"),
            new Groupe("Algorithmique", "TD-1", "TD", 30, 30, "B205", "Complet")
        );
        groupesTable.setItems(data);
    }

    private void showEtudiants(Groupe g) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Étudiants du groupe " + g.getGroupe());
        alert.setHeaderText(g.getCours() + " - " + g.getGroupe());
        alert.setContentText("Effectif : " + g.getInscrits() + "/" + g.getCapacite());
        alert.showAndWait();
    }
}