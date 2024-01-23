package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Main;
import sample.connexion.Connect;
import sample.model.ListeV;
import sample.util.DateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class ListeVisiteur {


    private Main mainApp;
    private ObservableList<ListeV> listevisiteur;
    private Connect connect = new Connect();
    private ListeV liste;
    private ObservableList<String> listeS = FXCollections.observableArrayList();
    private ObservableList<String> listeM = FXCollections.observableArrayList();
    private ObservableList<String> listeA = FXCollections.observableArrayList();

    @FXML
    private TableView<ListeV> TableListeV;
    @FXML
    private TableColumn<ListeV, String> Visiteur1;
    @FXML
    private TableColumn<ListeV, String> DateTab;
    @FXML
    private TableColumn<ListeV, String> Tarif;
    @FXML
    private TableColumn<ListeV, String> Nbjours;
    @FXML
    private TableColumn<ListeV, String> Montant;
    @FXML
    private ComboBox SiteIndex;
    @FXML
    private DatePicker Date1;
    @FXML
    private Label MontantTotal;
    @FXML
    private ComboBox mois;
    @FXML
    private ComboBox an;
    @FXML
    private DatePicker Date2;


    @FXML
    public void initialize() {

        Liste_Site();
        Liste_Mois();
        Liste_Année();

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

        SiteIndex.setValue(listeS.get(0));
        SiteIndex.setItems(listeS);
    }

    private void Liste_Mois() {
        Connection connex = connect.getConnection();
        String sql = new String("select distinct month(DATEVISITE) as mois from visiter");
        Statement statement = null;
        ResultSet resultat = null;

        try {
            statement = connex.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                listeM.add(String.valueOf(resultat.getObject("mois")));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

       // mois.setValue(listeM.get(0));
        mois.setItems(listeM);
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

       // an.setValue(listeA.get(0));
        an.setItems(listeA);
    }


    public void setMain(Main mainApp) {
        this.mainApp = mainApp;

    }

    public void Liste2dates(int NumSite,Object DateP,Object DateS) {
        listevisiteur = FXCollections.observableArrayList();
        int i = 0;
        int montantTotal = 0;

        try {
            Connection connex = connect.getConnection();
            String query = "SELECT visiteur.NOM as nom, site.NUMSITE as numsite,site.NOMSITE as nomsite, visiter.DATEVISITE as datev, site.TARIF as tarif, visiter.NBJOURS as nbj, (site.TARIF*visiter.NBJOURS) as montant from visiter inner join visiteur on visiter.NUMVISITEUR=visiteur.NUMVISITEUR inner join site on site.NUMSITE=visiter.NUMSITE  where site.NUMSITE='"+NumSite+"' and (visiter.DATEVISITE BETWEEN '"+DateP+"' AND '"+DateS+"') GROUP BY visiteur.NUMVISITEUR ";
            Statement state = connex.createStatement();
            ResultSet result = state.executeQuery(query);

            while (result.next()) {
                liste=new ListeV(result.getString("nom"),result.getObject("datev"),result.getString("tarif"), result.getInt("nbj"),result.getInt("montant")) ;
                listevisiteur.add(liste);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        while (i < listevisiteur.size()) {
            montantTotal += listevisiteur.get(i).getMONTANT();
            i++;
        }
        TableListeV.setItems(listevisiteur);
        Visiteur1.setCellValueFactory(cellData -> cellData.getValue().NOMProperty());
        DateTab.setCellValueFactory(cellData -> cellData.getValue().DATEVISITEProperty());
        Tarif.setCellValueFactory(cellData -> cellData.getValue().TARIFProprety());
        Nbjours.setCellValueFactory(cellData -> cellData.getValue().NBJOURSProperty().asString());
        Montant.setCellValueFactory(cellData ->cellData.getValue().MONTANTProprety().asString());
        SiteIndex.setItems(listeS);

        mois.setValue("");
        an.setValue("");
       MontantTotal.setText(montantTotal+ "Ar");
    }

    public void Listemoisannee(int NumSite,Object mois,Object an) {
        listevisiteur = FXCollections.observableArrayList();
        int i = 0;
        int montantTotal = 0;

        try {
            Connection connex = connect.getConnection();
            String query = "SELECT visiteur.NOM as nom, site.NUMSITE as numsite,site.NOMSITE as nomsite, visiter.DATEVISITE as datev, site.TARIF as tarif, visiter.NBJOURS as nbj, (site.TARIF*visiter.NBJOURS) as montant from visiter inner join visiteur on visiter.NUMVISITEUR=visiteur.NUMVISITEUR inner join site on site.NUMSITE=visiter.NUMSITE  where site.NUMSITE='"+NumSite+"' and month(visiter.DATEVISITE)='"+mois+"' and year(visiter.DATEVISITE)='"+an+"' GROUP BY visiteur.NOM ";
            Statement state = connex.createStatement();
            ResultSet result = state.executeQuery(query);

            while (result.next()) {
                liste=new ListeV(result.getString("nom"),result.getObject("datev"),result.getString("tarif"), result.getInt("nbj"),result.getInt("montant")) ;
                listevisiteur.add(liste);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        while (i < listevisiteur.size()) {
            montantTotal += listevisiteur.get(i).getMONTANT();
            i++;
        }
        TableListeV.setItems(listevisiteur);
        Visiteur1.setCellValueFactory(cellData -> cellData.getValue().NOMProperty());
        DateTab.setCellValueFactory(cellData -> cellData.getValue().DATEVISITEProperty());
        Tarif.setCellValueFactory(cellData -> cellData.getValue().TARIFProprety());
        Nbjours.setCellValueFactory(cellData -> cellData.getValue().NBJOURSProperty().asString());
        Montant.setCellValueFactory(cellData ->cellData.getValue().MONTANTProprety().asString());
        SiteIndex.setItems(listeS);

        Date1.setValue(null);
        Date2.setValue(null);
        MontantTotal.setText(montantTotal+ "Ar");
    }



    public void BtnSearch2(ActionEvent actionEvent) {
        String valeur_site = (String) SiteIndex.getValue();
        String[] tabValeurS = valeur_site.split("-");
        int NUMSI = Integer.valueOf(tabValeurS[0]).intValue();
        Object premierDate = Date1.getValue();
        Object secondDate = Date2.getValue();
        if(premierDate == null || secondDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Date non valider");
            alert.setContentText("Rectifier la date que vous avez entrer");
            alert.showAndWait();
        }
        else {
            Liste2dates(NUMSI,premierDate,secondDate);
        }
    }



    public void BtnSearch1(ActionEvent actionEvent) {
        String valeur_site = (String) SiteIndex.getValue();
        String[] tabValeurS = valeur_site.split("-");
        int NUMSI = Integer.valueOf(tabValeurS[0]).intValue();
        Object moi = mois.getValue();
        Object ann = an.getValue();
        if(moi == null || ann == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("mois et année invalides");
            alert.setContentText("Rectifier le mois et l'année que vous avez entrés");
            alert.showAndWait();
        }
        else {
            Listemoisannee(NUMSI,moi,ann);
        }
    }
}
