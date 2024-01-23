package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.connexion.Connect;
import sample.model.Visiter;

import java.sql.*;


public class ModifierVisiter {
    private Stage dialogStage;
    private boolean okClicked = false;
    private Visiter visiter;
    private Connect connect = new Connect();
    private ObservableList<String> listeV = FXCollections.observableArrayList();
    private ObservableList<String> listeS = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        Liste_Visiteur();
        Liste_Site();

    }

    @FXML
    private ComboBox listevisiteur;
    @FXML
    private ComboBox listesite;
    @FXML
    private TextField Nbj;


    public void setVisiter(Visiter visiter) {
        this.visiter = visiter;
        listevisiteur.setValue(visiter.getNUMVISITEUR()+"-"+visiter.getNOM());
        listesite.setValue(visiter.getNUMSITE()+"-"+visiter.getNOMSITE());
        Nbj.setText(String.valueOf(visiter.getNBJOURS()));

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    private void Liste_Visiteur() {
        Connection connex = connect.getConnection();
        String sql = new String("select * from visiteur");
        Statement statement = null;
        ResultSet resultat = null;

        try {
            statement = connex.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                listeV.add(resultat.getString("NUMVISITEUR") + "-" + resultat.getString("NOM"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        listevisiteur.setValue(listeV.get(0));
        listevisiteur.setItems(listeV);
    }

    private void Liste_Site() {
        Connection connex = connect.getConnection();
        String sql = new String("select * from site");
        Statement statement = null;
        ResultSet resultat = null;

        try {
            statement = connex.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                listeS.add(resultat.getInt("NUMSITE") + "-" + resultat.getString("NOMSITE"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        listesite.setValue(listeS.get(0));
        listesite.setItems(listeS);
    }


    public void OK(ActionEvent actionEvent) {
        if (inputValid()) {
            String valeur_visiteur = (String) listevisiteur.getValue();
            String[] tabValeurV = valeur_visiteur.split("-");
            int NUMV = Integer.valueOf(tabValeurV[0]).intValue();

            //Pour avoir l'identifiant de SITE
            String valeur_site = (String) listesite.getValue();
            String[] tabValeurS = valeur_site.split("-");
            int NUMSI = Integer.valueOf(tabValeurS[0]).intValue();

            Connection connex = connect.getConnection();
            String sql = "update visiter set NBJOURS=? WHERE NUMVISITEUR=? AND NUMSITE=?";
            PreparedStatement statement = null;
            try {
                statement = connex.prepareStatement(sql);

                statement.setInt(1, Integer.parseInt(Nbj.getText()));
                statement.setString(2, String.valueOf(NUMV));
                statement.setInt(3, NUMSI);
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
        if(Nbj.getText() == null || Nbj.getText().length() == 0) {
            messageErreur += "Nombre de jours!";
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
