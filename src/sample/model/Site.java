package sample.model;

import javafx.beans.property.*;


public class Site {
    private IntegerProperty NUMSITE;
    private StringProperty NOMSITE;
    private StringProperty LIEU;
    private SimpleStringProperty TARIF;

    public Site() {
this(0,null,null,null);
    }


    public Site(Integer num, String nom, String lieu, String tarif ){
        this.NUMSITE= new SimpleIntegerProperty(num);
        this.NOMSITE= new SimpleStringProperty(nom);
        this.LIEU= new SimpleStringProperty(lieu);
        this.TARIF=new SimpleStringProperty(tarif);
    }




    public String getNUMSITE(){ return String.valueOf(NUMSITE.get()); }
    public void setNUMSITE(Integer NUMSITE){ this.NUMSITE.set(NUMSITE); }
    public IntegerProperty NUMSITEProprety(){return NUMSITE;}

    public String getNOMSITE(){ return NOMSITE.get();}
    public void setNOMSITE(String NOMSITE){this.NOMSITE.set(NOMSITE);}
    public StringProperty NOMSITEProprety(){return NOMSITE;}

    public String getLIEU(){ return LIEU.get();}
    public void setLIEU(String LIEU){this.LIEU.set(LIEU);}
    public StringProperty LIEUProprety(){return LIEU;}

    public String getTARIF(){ return (TARIF.get()); }
    public void setTARIF(Integer TARIF){ this.TARIF.set(String.valueOf(TARIF)); }
    public StringProperty TARIFProprety(){return TARIF;}


}
