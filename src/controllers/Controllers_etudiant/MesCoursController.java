package controllers.Controllers_etudiant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;

public class MesCoursController {

    @FXML private TableView<Cours> coursTable;
    @FXML private TableColumn<Cours, String> colCode;
    @FXML private TableColumn<Cours, String> colIntitule;
    @FXML private TableColumn<Cours, String> colDescription;
    @FXML private TableColumn<Cours, String> colHeures;
    @FXML private TableColumn<Cours, String> colEnseignant;
    @FXML private Label statusLabel;

    // Modèle de données
    public static class Cours {
        private final SimpleStringProperty code;
        private final SimpleStringProperty intitule;
        private final SimpleStringProperty description;
        private final SimpleStringProperty heures;
        private final SimpleStringProperty enseignant;
        

        public Cours(String code, String intitule, String description, String heures,String enseignant) {
            this.code = new SimpleStringProperty(code);
            this.intitule = new SimpleStringProperty(intitule);
            this.description = new SimpleStringProperty(description);
            this.heures = new SimpleStringProperty(heures);
            this.enseignant = new SimpleStringProperty(enseignant);
        }
        public String getCode() { return code.get(); }
        public String getIntitule() { return intitule.get(); }
        public String getDescription() { return description.get(); }
        public String getHeures() { return heures.get(); }
        public String getEnseignat() { return enseignant.get(); }
    }

    @FXML
    public void initialize() {
        // Lier les colonnes aux propriétés
        colCode.setCellValueFactory(cellData -> cellData.getValue().code);
        colIntitule.setCellValueFactory(cellData -> cellData.getValue().intitule);
        colDescription.setCellValueFactory(cellData -> cellData.getValue().description);
        colHeures.setCellValueFactory(cellData -> cellData.getValue().heures);
        colEnseignant.setCellValueFactory(cellData -> cellData.getValue().enseignant);
        // Données simulées (exemple)
        ObservableList<Cours> data = FXCollections.observableArrayList(
            new Cours("INF301", "POO Avancée", "Programmation orientée objet en Java", "45h","Dr.Noulapeu"),
            new Cours("INF302", "Réseaux", "Fondamentaux des réseaux informatiques", "30h","Dr.Malong"),
            new Cours("INF305", "Bases de données", "SQL, conception et optimisation", "36h","M.Dada Nelson"),
            new Cours("MATH301", "Mathématiques", "Algèbre et analyse pour l'informatique", "60h","Dr.Kikmo"),
            new Cours("INF304", "Algorithmique", "Structures de données avancées", "45h","M.Melvin")
        );
        coursTable.setItems(data);

        // Mettre à jour le statut
        statusLabel.setText(data.size() + " cours chargés");
    }
}