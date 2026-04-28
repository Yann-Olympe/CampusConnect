package controllers.Controllers_enseignant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class DashboardEnseignantController {

    @FXML private StackPane contentArea;
    @FXML private Label pageTitleLabel;
    @FXML private Label enseignantNameLabel;
    @FXML private Label enseignantRoleLabel;
    @FXML private Label topSubtitleLabel; // facultatif

    @FXML private Button btnDashboard;
    @FXML private Button btnMesCours;
    @FXML private Button btnMesGroupes;
    @FXML private Button btnMesEtudiants;
    @FXML private Button btnPlanning;
    @FXML private Button btnSaisieNotes;
    @FXML private Button btnNotesEnregistrees;
    @FXML private Button btnMoyennes;
    @FXML private Button btnNotifications;
    @FXML private Button btnMonProfil;
    @FXML private Button btnLogout;

    @FXML
    public void initialize() {
        // Les labels existent bien dans le FXML
        enseignantNameLabel.setText("Kevin Tchoumi");
        enseignantRoleLabel.setText("Enseignant");
        showDashboard();
    }

    private void loadView(String fxml, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view/Dashboard_enseignant/" + fxml));
            VBox view = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
            pageTitleLabel.setText(titre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void showDashboard()      { loadView("DashboardEnseignantView.fxml", "Tableau de bord"); }
    @FXML public void showMesCours()       { loadView("EnseignantCoursView.fxml", "Mes cours"); }
    @FXML public void showMesGroupes()     { loadView("EnseignantGroupesView.fxml", "Mes groupes"); }
    @FXML public void showMesEtudiants()   { loadView("EnseignantEtudiantsView.fxml", "Mes étudiants"); }
    @FXML public void showPlanning()       { loadView("EnseignantPlanningView.fxml", "Mon planning"); }
    @FXML public void showSaisieNotes()    { loadView("EnseignantSaisieNotesView.fxml", "Saisie des notes"); }
    @FXML public void showNotesEnregistrees(){ loadView("EnseignantNotesEnregistreesView.fxml", "Notes enregistrées"); }
    @FXML public void showMoyennes()       { loadView("EnseignantMoyennesView.fxml", "Moyennes / Résultats"); }
    @FXML public void showNotifications()  { loadView("EnseignantNotificationsView.fxml", "Notifications"); }
    @FXML public void showMonProfil()      { loadView("EnseignantProfilView.fxml", "Mon profil"); }

    @FXML
    public void handleLogout() {
        try {
            javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/views/view/LoginView.fxml"));
            contentArea.getScene().setRoot(root);
        } catch (IOException e) { e.printStackTrace(); }
    }
}