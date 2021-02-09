package apoteke.Korisnik;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {

    List<Korisnik> findAll();
    Optional<Korisnik> findOne(Integer id);
    Korisnik insert(Korisnik k);
    Korisnik save(Korisnik k);
    Korisnik findByEmail(String email);
    void delete(Integer id);
    Korisnik findByConfirmationToken(String token);
    List<Korisnik> findByName(String name);
    List<Korisnik> findBySurname(String surname);
    List<Korisnik> findByInfo(String name, String surname);

}
