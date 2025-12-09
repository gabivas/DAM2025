package ro.ase.seminar08;

public class CursValutar {
    private String dataCurs;
    private String eur;
    private String usd;

    public CursValutar(String dataCurs, String eur, String usd) {
        this.dataCurs = dataCurs;
        this.eur = eur;
        this.usd = usd;
    }

    public CursValutar() {
    }

    @Override
    public String toString() {
        return "CursValutar{" +
                "dataCurs= " + dataCurs +
                ", eur= " + eur +
                ", usd= " + usd +
                '}';
    }
}
