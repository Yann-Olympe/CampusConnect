package controllers.Controllers_etudiant;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

public class DashboardViewController {

    @FXML private Label nbCoursLabel, nbGroupesLabel, nbSeancesLabel, nbNotesLabel, moyenneLabel;
    @FXML private Label nextCoursLabel, nextTypeLabel, nextGroupeLabel, nextDateLabel, nextHeureLabel, nextSalleLabel, nextEnseignantLabel;
    @FXML private ListView<String> notificationsListView;
    @FXML private TableView<?> miniPlanningTableView;

    @FXML
    public void initialize() {
        // KPI
        nbCoursLabel.setText("6");
        nbGroupesLabel.setText("12");
        nbSeancesLabel.setText("4");
        nbNotesLabel.setText("3");
        moyenneLabel.setText("14.25");

        // Prochaine séance
        nextCoursLabel.setText("Programmation Orientée Objet (POO)");
        nextTypeLabel.setText("TD");
        nextGroupeLabel.setText("TD-2");
        nextDateLabel.setText("Lundi 22 Avril 2026");
        nextHeureLabel.setText("12h00 - 15h30");
        nextSalleLabel.setText("15BS1");
        nextEnseignantLabel.setText("M. Léo");

        // Notifications
        notificationsListView.getItems().addAll(
            "Nouvelle note publiée pour POO",
            "Inscription aux groupes ouverte",
            "Séance de TD reportée au mercredi"
        );
    }
}