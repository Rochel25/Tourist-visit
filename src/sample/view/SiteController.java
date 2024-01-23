package sample.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.connexion.Connect;
import sample.model.Site;



import java.sql.*;
import java.util.Locale;
import java.util.Optional;




public class SiteController {
    private Main mainApp;
    private ObservableList<Site> listesite;
    private Connect connect= new Connect();
    private Site liste;
    private ObservableList<Site>listerech;
    private Site listeS;

    @FXML
    private TableView<Site> SiteTable;
    @FXML
    private TableColumn<Site, Integer> NumColumn;
    @FXML
    private TableColumn<Site, String> NomColumn;
    @FXML
    private TableColumn<Site, String> LieuColumn;
    @FXML
    private TableColumn<Site, String> TarifColumn;
    @FXML
    private TextField search;

    @FXML
    public void initialize(){
        Affichage();
        recherche();
    }

    @FXML
    private void BoutonAjouter() {
        Site tempSite = new Site();
        boolean okClicked = mainApp.AjoutSite(tempSite);
        if (okClicked) {
           Affichage();
        }
    }

    public void list_rech(String numS,String nomS) {
        listerech = FXCollections.observableArrayList();

        try {
            Connection connex = connect.getConnection();
            String sql = new String("Select NUMSITE, NOMSITE, LIEU,TARIF from site where NUMSITE regexp '"+(numS)+"' or NOMSITE regexp '"+(nomS)+"'");
            Statement state = connex.createStatement();
            ResultSet result = state.executeQuery(sql);
            while (result.next()) {
                listeS=new Site(result.getInt("NUMSITE"),result.getString("NOMSITE"),result.getString("LIEU"),result.getString("TARIF" ));
                listerech.add(listeS);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        SiteTable.setItems(listerech);
        NumColumn.setCellValueFactory(
                cellData -> cellData.getValue().NUMSITEProprety().asObject());
        NomColumn.setCellValueFactory(
                cellData -> cellData.getValue().NOMSITEProprety());
        LieuColumn.setCellValueFactory(
                cellData -> cellData.getValue().LIEUProprety());
        TarifColumn.setCellValueFactory(
                cellData -> cellData.getValue().TARIFProprety());

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
        listesite = FXCollections.observableArrayList();
        try {
            Connection connex= connect.getConnection();
            String sql= "Select NUMSITE, NOMSITE, LIEU,TARIF from site";
            Statement state= connex.createStatement();
            ResultSet result=state.executeQuery(sql);
            while (result.next()){
                liste=new Site ( result.getInt("NUMSITE"),result.getString("NOMSITE"),result.getString("LIEU"),result.getString("TARIF" ));
                listesite.add(liste);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        SiteTable.setItems(listesite);
        NumColumn.setCellValueFactory(
                cellData -> cellData.getValue().NUMSITEProprety().asObject());
        NomColumn.setCellValueFactory(
                cellData -> cellData.getValue().NOMSITEProprety());
        LieuColumn.setCellValueFactory(
                cellData -> cellData.getValue().LIEUProprety());
        TarifColumn.setCellValueFactory(
                cellData -> cellData.getValue().TARIFProprety());
    }


    public void setMain(Main mainApp) {
        this.mainApp = mainApp;

    }

    public void btnModifierSite(ActionEvent event) {
        int selectedIndex = SiteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Site tempSite = SiteTable.getSelectionModel().getSelectedItem();
            boolean okClicked = mainApp.ModifierSite(tempSite);
            if (okClicked) {
                Affichage();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun site selectionné");
            alert.setContentText("Veuillez sélectionner un site");

            alert.showAndWait();
        }

    }

    public void btnSuppr(ActionEvent event) {
        int selectedIndex = SiteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression");
            alert.setContentText("Voulez-vous supprimer ce site?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                Site site = SiteTable.getSelectionModel().getSelectedItem();
                String index = site.getNUMSITE();
                Connection connex = connect.getConnection();
                String sql = "DELETE from site where NUMSITE=?";
                PreparedStatement state = null;

                try {
                    state = connex.prepareStatement(sql);
                    state.setString(1, index);
                    state.execute();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Affichage();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun site selectionné");
            alert.setContentText("Veuillez sélectionner un site");

            alert.showAndWait();
        }
    }
}
