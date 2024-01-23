package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.connexion.Connect;
import sample.model.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifierSite {
    private Stage dialogStage;
    private boolean okClicked = false;
    private Site site;
    private Connect connect= new Connect();

    @FXML
    private TextField Num;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Lieu;
    @FXML
    private TextField Tarif;

    @FXML
    private void initialize() {
    }
    public void setSite(Site site) {
        this.site = site;
        Num.setText(site.getNUMSITE());
        Nom.setText(site.getNOMSITE());
        Lieu.setText(site.getLIEU());
        Tarif.setText(site.getTARIF());

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void Modifier(ActionEvent event) {
        if (inputValid()) {
            Connection connex = connect.getConnection();
            String sql = "update site set NOMSITE=?, LIEU=?, TARIF=? where NUMSITE=?";
            PreparedStatement statement = null;
            try {
                statement = connex.prepareStatement(sql);
                statement.setString(1, Nom.getText());
                statement.setString(2, Lieu.getText());
                statement.setInt(3, Integer.parseInt(Tarif.getText()));
                statement.setInt(4, Integer.parseInt(site.getNUMSITE()));
                statement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            okClicked = true;
            dialogStage.close();

        }
    }

    private boolean inputValid() {
        String messageErreur = new String("");
        if(Nom.getText() == null || Nom.getText().length() == 0) {
            messageErreur += "nom !";
        }
        if (Lieu.getText() == null || Lieu.getText().length() == 0) {
            messageErreur += "lieu !";
        }
        if (Tarif.getText() == null || Tarif.getText().length() == 0)  {
            messageErreur += "Tarif !";
        }
        if (Tarif.getText() == null || Tarif.getText().matches("[0-9]"))  {
            messageErreur += "Tarif !";
        }
        if(messageErreur.length() == 0) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Champs invalide");
            alert.setContentText(messageErreur);
            alert.showAndWait();

            return false;
        }
    }

    public void annuler(ActionEvent actionEvent) {
        dialogStage.close();
    }

}
