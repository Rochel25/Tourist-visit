package sample.model;

import javafx.beans.property.*;


public class Visiteur {

    private StringProperty NUMVISITEUR;
    private StringProperty NOM;
    private StringProperty ADRESSE;

    public Visiteur(){
        this(null,null,null);
    }

    public Visiteur(String NUM, String NOM, String ADRESSE){
        this.NUMVISITEUR = new SimpleStringProperty(NUM);
        this.NOM = new SimpleStringProperty(NOM);
        this.ADRESSE = new SimpleStringProperty(ADRESSE);

    }

    public String getNUMVISITEUR () {
        return NUMVISITEUR.get();
    }

    public void setNUMVISITEUR(String NUMVISITEUR){
        this.NUMVISITEUR.set(NUMVISITEUR);
    }

    public StringProperty NUMVISITEURProperty() {
        return NUMVISITEUR;
    }

    public String getNOM() {
        return NOM.get();
    }


    public void setNOM(String NOM){
        this.NOM.set(NOM);
    }

    public StringProperty NOMProperty() {
        return NOM;
    }

    public String getADRESSE() {
        return ADRESSE.get();
    }


    public void setADRESSE(String ADRESSE){
        this.ADRESSE.set(ADRESSE);
    }

    public StringProperty ADRESSEProperty() {
        return ADRESSE;
    }
}
