package controllers.Controllers_enseignant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MainEsgController {

    @FXML private StackPane contentArea;
    @FXML private Label pageTitleLabel;
    @FXML private Label topSubtitleLabel;
    @FXML private Label enseignantNameLabel;
    @FXML private Label enseignantRoleLabel;

    @FXML
    public void initialize() {
        enseignantNameLabel.setText("Kevin Tchoumi");
        enseignantRoleLabel.setText("Enseignant");
        showDashboard();
    }

    private void loadContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view/Dashboard_enseignant/" + fxmlPath));
            VBox content = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void showDashboard()      { loadContent("DashboardEnseignantView.fxml"); pageTitleLabel.setText("Tableau de bord enseignant"); }
    @FXML public void showCours()          { loadContent("EnseignantCoursView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showGroupes()        { loadContent("EnseignantGroupesView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showEtudiants()      { loadContent("EnseignantEtudiantsView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showPlanning()       { loadContent("EnseignantPlanningView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showSaisieNotes()    { loadContent("EnseignantSaisieNotesView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showNotesEnregistrees(){ loadContent("EnseignantNotesEnregistreesView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showMoyennes()       { loadContent("EnseignantMoyennesView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showNotifications()  { loadContent("EnseignantNotificationsView.fxml"); pageTitleLabel.setText("Espace enseignant"); }
    @FXML public void showProfil()         { loadContent("EnseignantProfilView.fxml"); pageTitleLabel.setText("Espace enseignant"); }

    @FXML
    public void logout() {
        try {
            javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/views/view/LoginView.fxml"));
            contentArea.getScene().setRoot(root);
        } catch (IOException e) { e.printStackTrace(); }
    }
}