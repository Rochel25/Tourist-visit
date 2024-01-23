package sample.view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.connexion.Connect;
import sample.model.Site;
import sample.model.Visiter;
import sample.model.Visiteur;
import sample.util.DateUtil;


import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class VisiterController {
    private Main mainApp;
    private ObservableList<Visiter> listevisiter;
    private Connect connect = new Connect();
    private Visiter liste;

    @FXML
    private TableView<Visiter> VisiterTable;
    @FXML
    private TableColumn<Visiter, String> VisiteurColumn;
    @FXML
    private TableColumn<Visiter, String> SiteColumn;
    @FXML
    private TableColumn<Visiter, String> NbjColumn;
    @FXML
    private TableColumn<Visiter, String> DateColumn;

    @FXML
    public void initialize() {
        Affichage();
    }

    @FXML
    private void BoutonAjouter() {
        Visiter tempVisiter = new Visiter();
        boolean okClicked = mainApp.AjoutVisiter(tempVisiter);
        if (okClicked) {
            Affichage();
        }
    }

    @FXML
    public void BoutonModifier(ActionEvent actionEvent) {
        int selectedIndex = VisiterTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Visiter tempVisiter = VisiterTable.getSelectionModel().getSelectedItem();
            boolean okClicked = mainApp.ModifierVisiter(tempVisiter);
            if (okClicked) {
                Affichage();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune visite selectionné");
            alert.setContentText("Veuillez sélectionner une visite");

            alert.showAndWait();
        }
    }

    private void Affichage(){
        listevisiter = FXCollections.observableArrayList();
        try {
            Connection connex= connect.getConnection();
            String sql= "Select * from visiter inner join visiteur on visiter.NUMVISITEUR=visiteur.NUMVISITEUR inner join site on site.NUMSITE=visiter.NUMSITE ";
            Statement state= connex.createStatement();
            ResultSet result=state.executeQuery(sql);
            while (result.next()){
                liste=new Visiter(result.getString("NUMVISITEUR"), result.getString("NOM"),result.getInt("NUMSITE"),result.getString("NOMSITE"),result.getInt("NBJOURS"), result.getObject("DATEVISITE")) ;
                listevisiter.add(liste);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        VisiterTable.setItems(listevisiter);
        VisiteurColumn.setCellValueFactory(
                cellData -> cellData.getValue().NOMProperty());
        SiteColumn.setCellValueFactory(
                cellData -> cellData.getValue().NOMSITEProprety());
        NbjColumn.setCellValueFactory(
                cellData -> cellData.getValue().NBJOURSProperty().asString());
        DateColumn.setCellValueFactory(
                cellData ->cellData.getValue().DATEVISITEProperty().asString());
    }

    public void btnSuppr(ActionEvent event) {
        int selectedIndex = VisiterTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression");
            alert.setContentText("Voulez-vous supprimer cette visite?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                Visiter visiter = VisiterTable.getSelectionModel().getSelectedItem();
                String index = visiter.getNUMVISITEUR();
                Integer index1 = visiter.getNUMSITE();
                Connection connex = connect.getConnection();
                String sql = "DELETE from visiter where NUMVISITEUR=? AND NUMSITE=?";
                PreparedStatement state = null;

                try {
                    state = connex.prepareStatement(sql);
                    state.setString(1, index);
                    state.setInt(2, index1);
                    state.execute();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Affichage();
            }
        }
            else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune visite selectionné");
            alert.setContentText("Veuillez sélectionner une visite");

            alert.showAndWait();
        }
    }

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;

    }


}
