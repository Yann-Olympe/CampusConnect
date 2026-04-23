package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class DashboardEtudiantController {

    @FXML private StackPane contentArea;
    @FXML private Label pageTitleLabel;               
    @FXML private Button btnDashboard, btnMesCours, btnInscriptionGroupes, btnMesGroupes, btnPlanning, btnMesSeances, btnMesNotes, btnMonReleve, btnNotifications, btnMonProfil, btnParametres, btnLogout;
    @FXML private Label sidebarStudentNameLabel, sidebarMatriculeLabel, sidebarClasseLabel;

    @FXML
    public void initialize() {
        sidebarStudentNameLabel.setText("Kurain Nagal");
        sidebarMatriculeLabel.setText("Matricule : 21INF1234");
        sidebarClasseLabel.setText("Licence 3 - Informatique");

        showDashboardView(); // Le titre sera mis à jour automatiquement
    }

    @FXML public void showDashboardView()            { loadView("DashboardView.fxml", "Tableau de bord"); }
    @FXML public void showMesCoursPlaceholder()      { loadView("MesCoursView.fxml", "Mes cours"); }
    @FXML public void showInscriptionGroupesView()   { loadView("InscriptionGroupesView.fxml", "Inscription aux groupes"); }
    @FXML public void showMesGroupesPlaceholder()    { loadView("MesGroupesView.fxml", "Mes groupes"); }
    @FXML public void showPlanningView()             { loadView("PlanningView.fxml", "Mon planning"); }
    @FXML public void showMesSeancesPlaceholder()    { loadView("MesSeancesView.fxml", "Mes séances"); }
    @FXML public void showMesNotesPlaceholder()      { loadView("NotesView.fxml", "Mes notes"); }
    @FXML public void showMonRelevePlaceholder()     { loadView("MonReleveView.fxml", "Mon relevé de notes"); }
    @FXML public void showNotificationsPlaceholder() { loadView("NotificationsView.fxml", "Notifications"); }
    @FXML public void showMonProfilPlaceholder()     { loadView("MonProfilView.fxml", "Mon profil"); }
    @FXML public void showParametresPlaceholder()    { loadView("ParametresView.fxml", "Paramètres"); }

    @FXML
    public void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view/LoginView.fxml"));
            contentArea.getScene().setRoot(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadView(String fxmlFileName, String title) {
        try {
            String path = "/views/view/Dashboard_etudiant/" + fxmlFileName;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(loader.load());
            pageTitleLabel.setText(title);            // ← Mise à jour du titre
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}