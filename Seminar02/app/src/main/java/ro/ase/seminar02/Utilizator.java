package ro.ase.seminar02;

public class Utilizator {
    private String username;
    private String parola;
    private Gen gen;
    private boolean esteAngajat;

    public Utilizator(String username, String parola, Gen gen, boolean esteAngajat) {
        this.username = username;
        this.parola = parola;
        this.gen = gen;
        this.esteAngajat = esteAngajat;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", gen=" + gen +
                ", esteAngajat=" + esteAngajat +
                '}';
    }
}
