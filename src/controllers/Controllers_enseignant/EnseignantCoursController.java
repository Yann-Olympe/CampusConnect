package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class EnseignantCoursController {

    @FXML private TableView<Cours> coursTable;
    @FXML private TableColumn<Cours, String> colCode, colIntitule, colDescription, colRole;
    @FXML private TableColumn<Cours, Integer> colVolume, colNbGroupes;
    @FXML private TableColumn<Cours, Void> colActions;

    public static class Cours {
        private final SimpleStringProperty code, intitule, description, role;
        private final SimpleIntegerProperty volumeHoraire, nbGroupes;
        public Cours(String code, String intitule, String description, int volume, String role, int nbGroupes) {
            this.code = new SimpleStringProperty(code);
            this.intitule = new SimpleStringProperty(intitule);
            this.description = new SimpleStringProperty(description);
            this.role = new SimpleStringProperty(role);
            this.volumeHoraire = new SimpleIntegerProperty(volume);
            this.nbGroupes = new SimpleIntegerProperty(nbGroupes);
        }
        public String getCode() { return code.get(); }
        public String getIntitule() { return intitule.get(); }
        public String getDescription() { return description.get(); }
        public String getRole() { return role.get(); }
        public int getVolumeHoraire() { return volumeHoraire.get(); }
        public int getNbGroupes() { return nbGroupes.get(); }
    }

    @FXML
    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colIntitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVolume.setCellValueFactory(new PropertyValueFactory<>("volumeHoraire"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colNbGroupes.setCellValueFactory(new PropertyValueFactory<>("nbGroupes"));

        colActions.setCellFactory(col -> new TableCell<Cours, Void>() {
            private final Button btn = new Button("Détails");
            { btn.setOnAction(e -> {
                Cours c = getTableView().getItems().get(getIndex());
                showDetails(c);
            }); }
            @Override protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        coursTable.setItems(FXCollections.observableArrayList(
            new Cours("INF301", "POO Avancée", "Java, héritage, polymorphisme", 60, "Responsable", 3),
            new Cours("INF302", "Réseaux", "Couches OSI, IP, routage", 45, "Intervenant", 2),
            new Cours("INF305", "Bases de Données", "SQL, conception, optimisation", 50, "Responsable", 2),
            new Cours("INF304", "Algorithmique", "Structures avancées, complexité", 60, "Intervenant", 4)
        ));
    }

    private void showDetails(Cours c) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du cours");
        alert.setHeaderText(c.getCode() + " - " + c.getIntitule());
        alert.setContentText("Description : " + c.getDescription() +
                "\nVolume horaire : " + c.getVolumeHoraire() + "h" +
                "\nRôle : " + c.getRole() +
                "\nGroupes associés : " + c.getNbGroupes());
        alert.showAndWait();
    }
}