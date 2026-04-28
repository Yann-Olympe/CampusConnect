package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController {

    @FXML private TextField fieldIdentifiant;
    @FXML private PasswordField fieldPassword;
    @FXML private Label labelError;
    @FXML private Button btnLogin;
    @FXML private Label lblReturn;

    // Identifiants admin fictifs
    private final String ADMIN_ID = "admin";
    private final String ADMIN_PWD = "admin123";

    @FXML
    private void handleLogin() {
        String identifiant = fieldIdentifiant.getText().trim();
        String password = fieldPassword.getText().trim();

        // Vérification des champs vides
        if (identifiant.isEmpty() || password.isEmpty()) {
            showError("Veuillez remplir tous les champs.");
            return;
        }

        // Vérification des identifiants admin
        if (!identifiant.equals(ADMIN_ID) || !password.equals(ADMIN_PWD)) {
            showError("Identifiant ou mot de passe administrateur incorrect.");
            return;
        }

        // Connexion réussie : redirection vers DashboardAdminView
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/view/Dashboard_admin/MainAdminView.fxml"));
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tableau de bord Administrateur");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erreur de chargement du tableau de bord.");
        }
    }

    @FXML
    private void handleReturn() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/view/LoginView.fxml"));
            Stage stage = (Stage) lblReturn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("CampusConnect - Connexion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        labelError.setText(message);
        labelError.setVisible(true);
        labelError.setManaged(true);
    }
}