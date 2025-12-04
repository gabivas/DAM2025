package ro.ase.seminar09;

@Dao
public interface BibliotecaDAO {

    @Insert
    void insertBiblioteca(Biblioteca biblioteca);

    @Query("SELECT * FROM biblioteci")
    List<Biblioteca> getBiblioteci();
}
