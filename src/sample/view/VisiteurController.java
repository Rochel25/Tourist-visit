package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.connexion.Connect;
import sample.model.Visiteur;

import java.sql.*;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VisiteurController {
    private Main mainApp;
    private ObservableList<Visiteur>listevisiteur;
    private ObservableList<Visiteur>listerech;
    private Connect connect= new Connect();
    private Visiteur liste;
    private Visiteur listeV;


    @FXML
    private TableView<Visiteur> VisiteurTable;
    @FXML
    private TableColumn<Visiteur, String> NumColumn;
    @FXML
    private TableColumn<Visiteur, String> NomColumn;
    @FXML
    private TableColumn<Visiteur, String> AdresseColumn;
    @FXML
    private TextField search;


    @FXML
    public void initialize(){

        Affichage();
        recherche();
        }


    @FXML
    private void BoutonAjouter() {
        Visiteur tempVisiteur = new Visiteur();
        boolean okClicked = mainApp.AjoutVisiteur(tempVisiteur);
        if (okClicked) {
        Affichage();
        }
    }

    public void list_rech(String numV,String nomV) {
        listerech = FXCollections.observableArrayList();

        try {
            Connection connex = connect.getConnection();
            String sql = new String("Select concat ('VIS-', NUMVISITEUR) as NUMVI, NOM, ADRESSE  from visiteur where NUMVISITEUR regexp '"+(numV)+"' or NOM regexp '"+(nomV)+"'");
            Statement state = connex.createStatement();
            ResultSet resultat = state.executeQuery(sql);
            while (resultat.next()) {
                listeV=new Visiteur(resultat.getString("NUMVI"),resultat.getString("NOM"),resultat.getString("ADRESSE"));
                listerech.add(listeV);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        VisiteurTable.setItems(listerech);
        NumColumn.setCellValueFactory(
                cellData -> cellData.getValue().NUMVISITEURProperty());
        NomColumn.setCellValueFactory(
                cellData -> cellData.getValue().NOMProperty());
        AdresseColumn.setCellValueFactory(
                cellData -> cellData.getValue().ADRESSEProperty());

    }

    public void recherche(){
        search.setOnKeyPressed(b->{
            String num= search.getText().toUpperCase(Locale.ROOT);
            String rech= search.getText().toUpperCase(Locale.ROOT);

            if(rech.equals("")) {
                Affichage();
            }
            else {
                list_rech(num,rech);
            }
        });
    }


    private void Affichage(){
        listevisiteur = FXCollections.observableArrayList();
        try {
            Connection connex= connect.getConnection();
            String sql= "Select NUMVISITEUR, NOM, ADRESSE  from visiteur";
            Statement state= connex.createStatement();
            ResultSet result=state.executeQuery(sql);
            while (result.next()){
                liste=new Visiteur("VIS-"+result.getString("NUMVISITEUR"),result.getString("NOM"),result.getString("ADRESSE"));
                listevisiteur.add(liste);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    VisiteurTable.setItems(listevisiteur);
        NumColumn.setCellValueFactory(
                cellData -> cellData.getValue().NUMVISITEURProperty());
        NomColumn.setCellValueFactory(
                cellData -> cellData.getValue().NOMProperty());
        AdresseColumn.setCellValueFactory(
                cellData -> cellData.getValue().ADRESSEProperty());
    }

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;

    }

    @FXML
    public void BoutonSupprimer(ActionEvent actionEvent) {
        int selectedIndex = VisiteurTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression");
            alert.setContentText("Voulez-vous supprimer ce visiteur?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                String[] tabIndexV = listevisiteur.get(selectedIndex).getNUMVISITEUR().split("-");
                int index = Integer.valueOf(tabIndexV[1]).intValue();;
                Connection connex = connect.getConnection();
                String sql = "DELETE from visiteur where NUMVISITEUR=?";
                PreparedStatement state = null;

                try {
                    state = connex.prepareStatement(sql);
                    state.setInt(1, index);
                    state.execute();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Affichage();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun visiteur selectionné");
            alert.setContentText("Veuillez sélectionner un visiteur");

            alert.showAndWait();
        }
    }

    public void BoutonModifier(ActionEvent actionEvent) {

        int selectedIndex = VisiteurTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Visiteur tempVisiteur = VisiteurTable.getSelectionModel().getSelectedItem();
            boolean okClicked = mainApp.Modification(tempVisiteur);
            if (okClicked) {
               Affichage();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun visiteur selectionné");
            alert.setContentText("Veuillez sélectionner un visiteur");

            alert.showAndWait();
        }
    }






}
