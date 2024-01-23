package sample.model;

import javafx.beans.property.*;


public class Effect {
    private SimpleStringProperty EFFECTIF;
    private StringProperty NOMSITE;
    private StringProperty TOTAL;








    public Effect(String nom, String eff, String total) {
        this.NOMSITE=new  SimpleStringProperty(nom);
        this.EFFECTIF= new SimpleStringProperty(eff);
        this.TOTAL=new SimpleStringProperty(total);
    }


    public StringProperty NOMSITEProperty() {
        return NOMSITE;
    }

    public StringProperty EFFECTIFProperty() { return EFFECTIF; }

    public StringProperty TOTALProperty() {
        return TOTAL;
    }






}
