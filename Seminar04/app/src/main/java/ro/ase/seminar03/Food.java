package ro.ase.seminar03;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

enum Produse {APA, CARTOFI, SUC}

enum Valuta {RON, EUR}

public class Food implements Serializable {
    private String nume;
    private float pret;
    private int cantitate;
    private String adresa;
    private Date dataLivrare;
    private Produse produse;
    private Valuta valuta;

    public Food(String nume, float pret, int cantitate, String adresa, Date dataLivrare, Produse produse, Valuta valuta) {
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
        this.adresa = adresa;
        this.dataLivrare = dataLivrare;
        this.produse = produse;
        this.valuta = valuta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDataLivrare() {
        return dataLivrare;
    }

    public void setDataLivrare(Date dataLivrare) {
        this.dataLivrare = dataLivrare;
    }

    public Produse getProduse() {
        return produse;
    }

    public void setProduse(Produse produse) {
        this.produse = produse;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Food{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", adresa='" + adresa + '\'' +
                ", dataLivrare=" + sdf.format(dataLivrare) +
                ", produse=" + produse +
                ", valuta=" + valuta +
                '}';
    }
}