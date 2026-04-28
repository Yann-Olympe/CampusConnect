package controllers.Controllers_etudiant;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML private BorderPane mainBorderPane;
    @FXML private VBox navContainer;
    @FXML private Label navDashboard;
    @FXML private Label navMesCours;
    @FXML private Label navPlanning;
    @FXML private Label navNotes;
    @FXML private Label navInscriptions;
    @FXML private Label navLogout;

    @FXML
    public void initialize() {
        // Charger la vue par défaut
        loadView("/views/view/DashboardView.fxml");
        
        // Événements de navigation
        navDashboard.setOnMouseClicked(e -> loadView("/views/view/DashboardView.fxml"));
        navMesCours.setOnMouseClicked(e -> loadView("/views/view/MesCoursView.fxml"));
        navPlanning.setOnMouseClicked(e -> loadView("/views/view/PlanningView.fxml"));
        navNotes.setOnMouseClicked(e -> loadView("/views/view/NotesView.fxml"));
        navInscriptions.setOnMouseClicked(e -> loadView("/views/view/InscriptionGroupesView.fxml"));
        navLogout.setOnMouseClicked(e -> {
            System.out.println("Déconnexion");
            // Vous pouvez fermer la fenêtre ou afficher une alerte
        });
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            VBox view = loader.load();
            mainBorderPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}