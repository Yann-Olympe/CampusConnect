package controllers.Controllers_admin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminProfilController {

    @FXML private Label nomLabel;
    @FXML private Label emailLabel;
    @FXML private Label roleLabel;
    @FXML private Label derniereConnexionLabel;

    @FXML private Label actionsJourLabel;
    @FXML private Label rapportsLabel;
    @FXML private Label conflitsResolusLabel;

    @FXML
    public void initialize() {
        nomLabel.setText("Nom : Admin CampusConnect");
        emailLabel.setText("Email : admin@campusconnect.edu");
        roleLabel.setText("Rôle : Administrateur principal");
        derniereConnexionLabel.setText("Dernière connexion : 27/04/2026 18:20");

        actionsJourLabel.setText("23");
        rapportsLabel.setText("7");
        conflitsResolusLabel.setText("4");
    }
}