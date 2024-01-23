package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.connexion.Connect;
import sample.model.Effect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Effectif {

    @FXML
    private TableView<Effect> TableEffectif;
    @FXML
    private TableColumn<Effect, String> site;
    @FXML
    private TableColumn<Effect, String> effectif;
    @FXML
    private TableColumn<Effect, String> total;
    @FXML
    private ComboBox an;

    private Main mainApp;
    private ObservableList<Effect> ListeE;
    private Connect connect = new Connect();
    private Effect effec;
    private ObservableList<String> listeA = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        Liste_Année();
    }

    private void Liste_Année() {
        Connection connex = connect.getConnection();
        String sql = new String("select distinct year(DATEVISITE) as an from visiter");
        Statement statement = null;
        ResultSet resultat = null;

        try {
            statement = connex.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                listeA.add(String.valueOf(resultat.getObject("an")));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        an.setItems(listeA);
    }

    public void Effectif(Object An) {
        ListeE= FXCollections.observableArrayList();

        try {
            Connection connex = connect.getConnection();
            String query = "SELECT site.NOMSITE as nom, count(visiteur.NUMVISITEUR) as effectif, sum(site.TARIF*visiter.NBJOURS) as total from visiter inner join visiteur on visiter.NUMVISITEUR=visiteur.NUMVISITEUR inner join site on site.NUMSITE=visiter.NUMSITE  where year(visiter.DATEVISITE)='"+An+"' GROUP BY site.NOMSITE ";
            Statement state = connex.createStatement();
            ResultSet result = state.executeQuery(query);

            while (result.next()) {
                effec=new Effect(result.getString("nom"),result.getString("effectif"),result.getString("total")+" "+"Ar") ;
                ListeE.add(effec);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        TableEffectif.setItems(ListeE);
        site.setCellValueFactory(cellData -> cellData.getValue().NOMSITEProperty());
        effectif.setCellValueFactory(cellData -> cellData.getValue().EFFECTIFProperty());
        total.setCellValueFactory(cellData -> cellData.getValue().TOTALProperty());



    }


    public void setMain(Main mainApp) {
        this.mainApp = mainApp;

    }

    public void btnsearch(ActionEvent actionEvent) {
        Object ANNEE = an.getValue();
        if(ANNEE == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Année invalide");
            alert.setContentText("Veuillez selectionner une année");
            alert.showAndWait();
        }
        else {
            Effectif(ANNEE);
        }
    }
}
