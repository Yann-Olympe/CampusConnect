package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;

public class MesGroupesController {
    @FXML private TableView<GroupeItem> groupesTable;

    public static class GroupeItem {
        private final SimpleStringProperty cours, type, groupe, enseignant, salle, creneau;
        public GroupeItem(String c, String t, String g, String e, String s, String cr) {
            this.cours = new SimpleStringProperty(c);
            this.type = new SimpleStringProperty(t);
            this.groupe = new SimpleStringProperty(g);
            this.enseignant = new SimpleStringProperty(e);
            this.salle = new SimpleStringProperty(s);
            this.creneau = new SimpleStringProperty(cr);
        }
        // getters et property methods...
    }

    @FXML public void initialize() {
        groupesTable.setItems(FXCollections.observableArrayList(
            new GroupeItem("POO", "TD", "TD-2", "M. Nzambwe", "B204", "Lun 10h-12h"),
            new GroupeItem("BDD", "CM", "CM-A", "Mme Fokam", "A101", "Mer 08h-10h"),
            new GroupeItem("Réseaux", "TP", "TP-1", "M. Ngulmatia", "Lab1", "Mar 14h-16h")
        ));
    }
}