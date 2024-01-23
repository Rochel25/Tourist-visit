package sample.view;

import javafx.fxml.FXML;
import sample.Main;


public class RootLayoutController {
    private Main main_1;

    //Setters
    public void setMain(Main main_2){
        this.main_1 = main_2;
    }

    @FXML
    private void btnSite(){ main_1.site(); }

    @FXML
    private void btnVisiteur(){
        main_1.affichevisiteur();
    }

    @FXML
    private void btnVisite(){
        main_1.visite();
    }

    @FXML
    private void btnListe(){
        main_1.ListeV();
    }

    @FXML
    private void btnEffectif(){
        main_1.Eff();
    }
}
