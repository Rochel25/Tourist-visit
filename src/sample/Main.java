package sample;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.model.Visiter;
import sample.model.Visiteur;
import sample.model.Site;
import sample.view.*;


public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de visite");

        initRootLayout();
        affichevisiteur();

    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            //controller visiteur
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            //controller site
            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setMain(this);

            //controller visite
            RootLayoutController rootLayoutControlle = loader.getController();
            rootLayoutControlle.setMain(this);

            //controller listev
            RootLayoutController rootLayoutControl = loader.getController();
            rootLayoutControl.setMain(this);

            //controller effectif
            RootLayoutController rootLayoutControle = loader.getController();
            rootLayoutControle.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void affichevisiteur() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VisiteurLayout.fxml"));
             AnchorPane VisiteurLayout = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(VisiteurLayout);

            // Give the controller access to the main app.
            VisiteurController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean AjoutVisiteur(Visiteur visiteur) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AjoutVisiteur.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouveau visiteur");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AjoutVisiteurController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVisiteur(visiteur);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Modification(Visiteur visiteur) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ModifierVisiteur.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modification de visiteur");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ModifierVisiteur controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVisiteur(visiteur);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void site() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/SiteLayout.fxml"));
            AnchorPane SiteLayout = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(SiteLayout);
            // Give the controller access to the main app

           SiteController controller = loader.getController();
           controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean AjoutSite(Site site) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AjoutSite.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouveau site");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AjoutSiteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSite(site);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ModifierSite(Site site) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ModifierSite.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modification de site");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ModifierSite controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSite(site);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void visite() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VisiterLayout.fxml"));
            AnchorPane VisiteLayout = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(VisiteLayout);
            // Give the controller access to the main app

            VisiterController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean AjoutVisiter(Visiter visiter) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/AjoutVisiter.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle visite");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AjoutVisiterController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVisiter(visiter);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ModifierVisiter(Visiter visiter) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ModifierVisiter.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modification de visite");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ModifierVisiter controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVisiter(visiter);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void ListeV() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ListeVisiteur.fxml"));
            AnchorPane ListeVLayout = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(ListeVLayout);
            // Give the controller access to the main app

            ListeVisiteur controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Eff() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Effectif.fxml"));
            AnchorPane EffLayout = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(EffLayout);
            // Give the controller access to the main app

            Effectif controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    public Window getPrimaryStage() {
        return primaryStage;
    }
}