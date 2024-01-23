package sample.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ListeV {
    private IntegerProperty MONTANT;
    private StringProperty NOM;
    private StringProperty NOMSITE;
    private IntegerProperty NBJOURS;
    private StringProperty TARIF;
    private ObjectProperty<LocalDate> DATEVISITE;







    public ListeV( String nom, Object datevi, String tarif, int nbjours,int montant) {

        this.NOM=new  SimpleStringProperty(nom);
       // this.NOMSITE=new  SimpleStringProperty(nomsite);
        this.TARIF=new SimpleStringProperty(tarif);
        this.MONTANT=new SimpleIntegerProperty(montant);
        this.NBJOURS=new SimpleIntegerProperty(nbjours);
        this.DATEVISITE=new SimpleObjectProperty(datevi);

    }




    public Integer getMONTANT () {
        return MONTANT.get();
    }


    public IntegerProperty MONTANTProprety () {
        return MONTANT;
    }

    public StringProperty TARIFProprety () {
        return TARIF;
    }


    public StringProperty NOMProperty() {
        return NOM;
    }



    public IntegerProperty NBJOURSProperty() {
       return NBJOURS; }

    public ObjectProperty DATEVISITEProperty() { return DATEVISITE; }



}
