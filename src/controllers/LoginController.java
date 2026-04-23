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

public class LoginController {

    @FXML private Button studentRoleButton;
    @FXML private Button studentRoleButton1;
    @FXML private TextField fieldIdentifiant;
    @FXML private PasswordField fieldPassword;
    @FXML private Label labelError;
    @FXML private Button btnLogin;
    @FXML private Label lblForgotPassword;
    @FXML private Button btnAdminAccess;

    private String selectedRole = "etudiant";

    // Identifiants fictifs pour test
    private final String ETUDIANT_ID = "etudiant";
    private final String ETUDIANT_PWD = "pass123";
    private final String ENSEIGNANT_ID = "prof";
    private final String ENSEIGNANT_PWD = "pass456";

    @FXML
    private void selectStudentRole() {
        selectedRole = "etudiant";
        studentRoleButton.setStyle("-fx-background-color: #4a9edd26;-fx-border-radius:10 ; -fx-border-color: #4a9edd; -fx-border-width: 1.5px;");
        studentRoleButton1.setStyle("-fx-background-color: #ffffff0d;-fx-border-radius:10 ; -fx-border-color: #4a9edd4d; -fx-border-width: 1.5px;");
    }

    @FXML
    private void selectEnseignantRole() {
        selectedRole = "enseignant";
        studentRoleButton1.setStyle("-fx-background-color: #4a9edd26;-fx-border-radius:10 ; -fx-border-color: #4a9edd; -fx-border-width: 1.5px;");
        studentRoleButton.setStyle("-fx-background-color: #ffffff0d; -fx-border-radius:10 ;-fx-border-color: #4a9edd4d; -fx-border-width: 1.5px;");
    }

    @FXML
    private void handleLogin() {
        String identifiant = fieldIdentifiant.getText().trim();
        String password = fieldPassword.getText().trim();

        // Vérification des champs vides
        if (identifiant.isEmpty() || password.isEmpty()) {
            showError("Veuillez remplir tous les champs.");
            return;
        }

        // Vérification des identifiants selon le rôle
        boolean isValid = false;
        if ("etudiant".equals(selectedRole)) {
            if (identifiant.equals(ETUDIANT_ID) && password.equals(ETUDIANT_PWD)) {
                isValid = true;
            }
        } else {
            if (identifiant.equals(ENSEIGNANT_ID) && password.equals(ENSEIGNANT_PWD)) {
                isValid = true;
            }
        }

        if (!isValid) {
            showError("Identifiant ou mot de passe incorrect.");
            return;
        }

        // Connexion réussie : redirection vers le dashboard correspondant
        try {
            String fxml;
            String title;
            if ("etudiant".equals(selectedRole)) {
                fxml = "/views/view/Dashboard_etudiant/MainView.fxml";
                title = "Tableau de bord Étudiant";
            } else {
                fxml = "/views/view/DashboardEnseignantView.fxml";
                title = "Tableau de bord Enseignant";
            }
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Erreur de chargement du tableau de bord.");
        }
    }

    private void showError(String message) {
        labelError.setText(message);
        labelError.setVisible(true);
        labelError.setManaged(true);
    }

    @FXML
    private void handleForgotPassword() {
        System.out.println("Mot de passe oublié - à implémenter");
    }

    @FXML
    private void handleAdminAccess() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/view/AdminLoginView.fxml"));
            Stage stage = (Stage) btnAdminAccess.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Administration - Connexion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}