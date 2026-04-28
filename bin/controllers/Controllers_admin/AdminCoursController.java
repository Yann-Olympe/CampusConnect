package controllers.Controllers_admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminCoursController {

    @FXML private TextField codeField;
    @FXML private TextField intituleField;
    @FXML private TextField volumeHoraireField;
    @FXML private ComboBox<String> semestreCombo;
    @FXML private ComboBox<String> filiereCombo;
    @FXML private ComboBox<String> niveauCombo;
    @FXML private ComboBox<String> enseignantResponsableCombo;
    @FXML private TextArea descriptionArea;

    @FXML private TextField searchField;
    @FXML private ComboBox<String> filterFiliereCombo;
    @FXML private ComboBox<String> filterSemestreCombo;

    @FXML private TableView<CoursItem> coursTable;
    @FXML private TableColumn<CoursItem, String> colCode;
    @FXML private TableColumn<CoursItem, String> colIntitule;
    @FXML private TableColumn<CoursItem, String> colDescription;
    @FXML private TableColumn<CoursItem, Integer> colVolumeHoraire;
    @FXML private TableColumn<CoursItem, String> colSemestre;
    @FXML private TableColumn<CoursItem, String> colFiliere;
    @FXML private TableColumn<CoursItem, String> colNiveau;
    @FXML private TableColumn<CoursItem, String> colResponsable;

    private final ObservableList<CoursItem> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colIntitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colVolumeHoraire.setCellValueFactory(new PropertyValueFactory<>("volumeHoraire"));
        colSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        colFiliere.setCellValueFactory(new PropertyValueFactory<>("filiere"));
        colNiveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        colResponsable.setCellValueFactory(new PropertyValueFactory<>("responsable"));

        fillCombos();
        loadMockData();

        FilteredList<CoursItem> filtered = new FilteredList<>(masterData, p -> true);

        Runnable apply = () -> {
            String keyword = searchField.getText() == null ? "" : searchField.getText().toLowerCase().trim();
            String filiere = filterFiliereCombo.getValue();
            String semestre = filterSemestreCombo.getValue();

            filtered.setPredicate(c -> {
                boolean matchSearch = keyword.isEmpty()
                        || c.getCode().toLowerCase().contains(keyword)
                        || c.getIntitule().toLowerCase().contains(keyword)
                        || c.getDescription().toLowerCase().contains(keyword);

                boolean matchFiliere = filiere == null || filiere.equals("Toutes") || c.getFiliere().equalsIgnoreCase(filiere);
                boolean matchSemestre = semestre == null || semestre.equals("Tous") || c.getSemestre().equalsIgnoreCase(semestre);

                return matchSearch && matchFiliere && matchSemestre;
            });
        };

        searchField.textProperty().addListener((obs, o, n) -> apply.run());
        filterFiliereCombo.valueProperty().addListener((obs, o, n) -> apply.run());
        filterSemestreCombo.valueProperty().addListener((obs, o, n) -> apply.run());

        coursTable.setItems(filtered);

        coursTable.getSelectionModel().selectedItemProperty().addListener((obs, o, s) -> {
            if (s != null) {
                codeField.setText(s.getCode());
                intituleField.setText(s.getIntitule());
                volumeHoraireField.setText(String.valueOf(s.getVolumeHoraire()));
                semestreCombo.setValue(s.getSemestre());
                filiereCombo.setValue(s.getFiliere());
                niveauCombo.setValue(s.getNiveau());
                enseignantResponsableCombo.setValue(s.getResponsable());
                descriptionArea.setText(s.getDescription());
            }
        });
    }

    private void fillCombos() {
        semestreCombo.setItems(FXCollections.observableArrayList("S1", "S2"));
        filiereCombo.setItems(FXCollections.observableArrayList("Génie Logiciel", "Réseaux", "Informatique", "Systèmes d'Information"));
        niveauCombo.setItems(FXCollections.observableArrayList("L1", "L2", "L3", "M1", "M2"));
        enseignantResponsableCombo.setItems(FXCollections.observableArrayList(
                "Kevin Tchoumi", "Marc Lelouche", "Alice Mendo", "Jean Nkoa"
        ));

        filterFiliereCombo.setItems(FXCollections.observableArrayList("Toutes", "Génie Logiciel", "Réseaux", "Informatique", "Systèmes d'Information"));
        filterFiliereCombo.setValue("Toutes");

        filterSemestreCombo.setItems(FXCollections.observableArrayList("Tous", "S1", "S2"));
        filterSemestreCombo.setValue("Tous");
    }

    private void loadMockData() {
        masterData.setAll(
                new CoursItem("INF301", "POO Avancée", "Java, héritage, polymorphisme", 60, "S1", "Génie Logiciel", "L3", "Kevin Tchoumi"),
                new CoursItem("INF302", "Réseaux WAN", "Routage, VLAN, architecture WAN", 45, "S1", "Réseaux", "L3", "Marc Lelouche"),
                new CoursItem("INF305", "Bases de Données", "SQL, normalisation, optimisation", 50, "S2", "Informatique", "L3", "Jean Nkoa"),
                new CoursItem("INF401", "Architecture Logicielle", "Patterns, MVC, couches", 40, "S2", "Génie Logiciel", "M1", "Kevin Tchoumi")
        );
    }

    @FXML private void handleAjouter() { showInfo("Ajout cours", "Cours ajouté (simulation)."); }
    @FXML private void handleModifier() { showInfo("Modification cours", "Cours modifié (simulation)."); }
    @FXML private void handleSupprimer() { showInfo("Suppression cours", "Cours supprimé (simulation)."); }

    @FXML
    private void handleReset() {
        codeField.clear();
        intituleField.clear();
        volumeHoraireField.clear();
        semestreCombo.setValue(null);
        filiereCombo.setValue(null);
        niveauCombo.setValue(null);
        enseignantResponsableCombo.setValue(null);
        descriptionArea.clear();
        coursTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRefresh() {
        searchField.clear();
        filterFiliereCombo.setValue("Toutes");
        filterSemestreCombo.setValue("Tous");
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static class CoursItem {
        private final String code, intitule, description, semestre, filiere, niveau, responsable;
        private final Integer volumeHoraire;

        public CoursItem(String code, String intitule, String description, Integer volumeHoraire,
                         String semestre, String filiere, String niveau, String responsable) {
            this.code = code;
            this.intitule = intitule;
            this.description = description;
            this.volumeHoraire = volumeHoraire;
            this.semestre = semestre;
            this.filiere = filiere;
            this.niveau = niveau;
            this.responsable = responsable;
        }

        public String getCode() { return code; }
        public String getIntitule() { return intitule; }
        public String getDescription() { return description; }
        public Integer getVolumeHoraire() { return volumeHoraire; }
        public String getSemestre() { return semestre; }
        public String getFiliere() { return filiere; }
        public String getNiveau() { return niveau; }
        public String getResponsable() { return responsable; }
    }
}