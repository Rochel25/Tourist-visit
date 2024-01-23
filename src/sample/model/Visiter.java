package sample.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Visiter {
    private StringProperty NUMVISITEUR;
    private StringProperty NOM;
    private StringProperty NOMSITE;
    private IntegerProperty NUMSITE;
    private IntegerProperty NBJOURS;
    private ObjectProperty<LocalDate> DATEVISITE;





public Visiter(){
    this(null,null,0,null,0,null);
}

public Visiter(String num, String nom, int numsite,String nomsite,  int nbjours, Object datevi) {
        this.NUMVISITEUR= new SimpleStringProperty(num);
        this.NOM=new  SimpleStringProperty(nom);
        this.NUMSITE=new SimpleIntegerProperty(numsite);
        this.NOMSITE=new  SimpleStringProperty(nomsite);
        this.NBJOURS=new SimpleIntegerProperty(nbjours);
        this.DATEVISITE=new SimpleObjectProperty(datevi);

    }


    public String getNUMVISITEUR() {
        return NUMVISITEUR.get();
    }

    public Integer getNUMSITE () {
        return NUMSITE.get();
    }

    public String getNOMSITE () {
        return NOMSITE.get();
    }

    public String getNOM () {
        return NOM.get();
    }

    public Integer getNBJOURS () {
        return NBJOURS.get();
    }

    public LocalDate getDATEVISITE () {
        return DATEVISITE.get();
    }

    public StringProperty NUMVISITEURProperty() {
        return NUMVISITEUR;
    }

    public StringProperty NOMProperty() {
        return NOM;
    }

    public StringProperty NOMSITEProprety() {
        return NOMSITE;
    }

    public IntegerProperty NBJOURSProperty() { return NBJOURS; }

    public ObjectProperty DATEVISITEProperty() { return DATEVISITE; }





}
