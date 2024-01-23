package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.connexion.Connect;
import sample.model.Visiteur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class ModifierVisiteur {
    private Stage dialogStage;
    private boolean okClicked = false;
    private Visiteur visiteur;
    private Connect connect= new Connect();

    @FXML
    private void initialize() {
    }
    @FXML
    private TextField Num;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Adresse;

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
        Num.setText(visiteur.getNUMVISITEUR());
        Nom.setText(visiteur.getNOM());
        Adresse.setText(visiteur.getADRESSE());

    }

    public boolean isOkClicked() {
        return okClicked;
    }


    public void Modifier(ActionEvent actionEvent) {
        if (inputValid()) {
            Connection connex = connect.getConnection();
            String[] tabIndexV = visiteur.getNUMVISITEUR().split("-");
            int visiteur = Integer.valueOf(tabIndexV[1]).intValue();
            ;
            String sql = "update visiteur set NOM=?, ADRESSE=? where NUMVISITEUR=?";
            PreparedStatement statement = null;
            try {
                statement = connex.prepareStatement(sql);
                statement.setString(1, Nom.getText());
                statement.setString(2, Adresse.getText());
                statement.setString(3, String.valueOf(visiteur));
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
        if (Adresse.getText() == null || Adresse.getText().length() == 0) {
            messageErreur += "adresse !";
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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
