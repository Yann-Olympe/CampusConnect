package controllers.Controllers_etudiant;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MonProfilController {
    @FXML private TextField nomField, prenomField, emailField, telephoneField, adresseField, matriculeField, filiereField;
    @FXML private DatePicker dateNaissancePicker;
    @FXML private ComboBox<String> niveauCombo, semestreCombo, anneeCombo;

    @FXML public void initialize() {
        niveauCombo.getItems().setAll("L1", "L2", "L3", "M1", "M2");
        semestreCombo.getItems().setAll("S1", "S2");
        anneeCombo.getItems().setAll("2022-2023", "2023-2024", "2024-2025");
    }
}