package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminMoyennesController {

    @FXML private ComboBox<String> filterFiliereCombo;
    @FXML private ComboBox<String> filterDecisionCombo;

    @FXML private TableView<MoyenneItem> moyennesTable;
    @FXML private TableColumn<MoyenneItem, String> colMatricule;
    @FXML private TableColumn<MoyenneItem, String> colEtudiant;
    @FXML private TableColumn<MoyenneItem, String> colFiliere;
    @FXML private TableColumn<MoyenneItem, Double> colMoyenne;
    @FXML private TableColumn<MoyenneItem, String> colDecision;
    @FXML private TableColumn<MoyenneItem, Integer> colRang;

    private final ObservableList<MoyenneItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colEtudiant.setCellValueFactory(new PropertyValueFactory<>("etudiant"));
        colFiliere.setCellValueFactory(new PropertyValueFactory<>("filiere"));
        colMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
        colDecision.setCellValueFactory(new PropertyValueFactory<>("decision"));
        colRang.setCellValueFactory(new PropertyValueFactory<>("rang"));

        filterFiliereCombo.setItems(FXCollections.observableArrayList("Toutes", "Génie Logiciel", "Réseaux", "Informatique", "Systèmes d'Information"));
        filterDecisionCombo.setItems(FXCollections.observableArrayList("Toutes", "Validé", "Rattrapage", "Échec"));

        filterFiliereCombo.setValue("Toutes");
        filterDecisionCombo.setValue("Toutes");

        loadMockData();

        FilteredList<MoyenneItem> filtered = new FilteredList<>(masterData, p -> true);

        Runnable apply = () -> {
            String filiere = filterFiliereCombo.getValue();
            String decision = filterDecisionCombo.getValue();

            filtered.setPredicate(m -> {
                boolean mf = filiere == null || filiere.equals("Toutes") || m.getFiliere().equalsIgnoreCase(filiere);
                boolean md = decision == null || decision.equals("Toutes") || m.getDecision().equalsIgnoreCase(decision);
                return mf && md;
            });
        };

        filterFiliereCombo.valueProperty().addListener((obs, o, n) -> apply.run());
        filterDecisionCombo.valueProperty().addListener((obs, o, n) -> apply.run());

        moyennesTable.setItems(filtered);
    }

    private void loadMockData() {
        masterData.setAll(
                new MoyenneItem("CC2026004", "FOUDA Linda", "Génie Logiciel", 16.10, "Validé", 1),
                new MoyenneItem("CC2026001", "NGONO Paul", "Génie Logiciel", 14.50, "Validé", 2),
                new MoyenneItem("CC2026002", "MBOA Sarah", "Réseaux", 9.75, "Rattrapage", 8),
                new MoyenneItem("CC2026003", "KAMDEM Kevin", "Informatique", 7.90, "Échec", 12)
        );
    }

    @FXML
    private void handleRefresh() {
        filterFiliereCombo.setValue("Toutes");
        filterDecisionCombo.setValue("Toutes");
    }

    public static class MoyenneItem {
        private final String matricule, etudiant, filiere, decision;
        private final Double moyenne;
        private final Integer rang;

        public MoyenneItem(String matricule, String etudiant, String filiere, Double moyenne, String decision, Integer rang) {
            this.matricule = matricule;
            this.etudiant = etudiant;
            this.filiere = filiere;
            this.moyenne = moyenne;
            this.decision = decision;
            this.rang = rang;
        }

        public String getMatricule() { return matricule; }
        public String getEtudiant() { return etudiant; }
        public String getFiliere() { return filiere; }
        public Double getMoyenne() { return moyenne; }
        public String getDecision() { return decision; }
        public Integer getRang() { return rang; }
    }
}