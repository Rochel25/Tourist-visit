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

public class AjoutSiteController {
    private Stage dialogStage;
    private boolean okClicked = false;
    private Site site;
    private Connect connect= new Connect();

    @FXML
    private void initialize() {
    }

    @FXML
    private TextField Nom;
    @FXML
    private TextField Lieu;
    @FXML
    private TextField Tarif;

    public void setSite(Site site) {
        this.site = site;


    }

    public boolean isOkClicked() {
        return okClicked;
    }



    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void AjouterSite(ActionEvent actionEvent) {
        if (inputValid()) {
            Connection connex = connect.getConnection();
            String sql = "insert into site ( NOMSITE, LIEU, TARIF) values(?,?,?)";
            PreparedStatement statement = null;
            try {
                statement = connex.prepareStatement(sql);
                // statement.setInt(1, Integer.parseInt(Num.getText()));
                statement.setString(1, Nom.getText());
                statement.setString(2, Lieu.getText());
                statement.setString(3, Tarif.getText());
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
        if (Tarif.getText() == null || Tarif.getText().length() == 0) {
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
            alert.setHeaderText("Champs non valider");
            alert.setContentText(messageErreur);
            alert.showAndWait();

            return false;
        }
    }

    public void annuler(ActionEvent actionEvent) {
        dialogStage.close();
    }

}
