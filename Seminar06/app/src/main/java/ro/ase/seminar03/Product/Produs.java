package ro.ase.seminar03.Product;

public class Produs {
    private String marca;
    private float pret;

    public Produs(String marca, float pret) {
        this.marca = marca;
        this.pret = pret;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
}
