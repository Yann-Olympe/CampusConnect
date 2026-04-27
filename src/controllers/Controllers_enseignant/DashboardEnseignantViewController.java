package controllers.Controllers_enseignant;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardEnseignantViewController {

    @FXML private Label nbCoursLabel, nbGroupesLabel, nbEtudiantsLabel, nbNotesAttenteLabel;
    @FXML private TableView<SeanceItem> seancesTable;
    @FXML private TableColumn<SeanceItem, String> colHeure, colCours, colGroupe, colSalle;

    public static class SeanceItem {
        private final String heure, cours, groupe, salle;
        public SeanceItem(String h, String c, String g, String s) { heure=h; cours=c; groupe=g; salle=s; }
        public String getHeure() { return heure; }
        public String getCours() { return cours; }
        public String getGroupe() { return groupe; }
        public String getSalle() { return salle; }
    }

    @FXML
    public void initialize() {
        nbCoursLabel.setText("6");
        nbGroupesLabel.setText("8");
        nbEtudiantsLabel.setText("186");
        nbNotesAttenteLabel.setText("23");

        colHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        colCours.setCellValueFactory(new PropertyValueFactory<>("cours"));
        colGroupe.setCellValueFactory(new PropertyValueFactory<>("groupe"));
        colSalle.setCellValueFactory(new PropertyValueFactory<>("salle"));

        seancesTable.setItems(FXCollections.observableArrayList(
            new SeanceItem("08:00-10:00", "POO Avancée", "TD2", "B204"),
            new SeanceItem("10:15-12:15", "Structures de Données", "TD2", "B205"),
            new SeanceItem("14:00-16:00", "Bases de Données", "TP1", "Lab1")
        ));
    }
}