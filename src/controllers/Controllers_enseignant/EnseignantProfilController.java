package controllers.Controllers_enseignant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EnseignantProfilController {

    @FXML private Label nomLabel, prenomLabel, emailLabel, statutLabel, departementLabel, dateNaissanceLabel, identifiantLabel, coursActifsLabel;

    @FXML
    public void initialize() {
        nomLabel.setText("Tchoumi");
        prenomLabel.setText("Kevin");
        emailLabel.setText("kevin.tchoumi@campusconnect.cm");
        statutLabel.setText("Permanent");
        departementLabel.setText("Informatique");
        dateNaissanceLabel.setText("12/06/1987");
        identifiantLabel.setText("ENS-0042");
        coursActifsLabel.setText("6");
    }
}