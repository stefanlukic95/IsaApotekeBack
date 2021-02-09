package apoteke.Rezervacija;

import java.util.List;
import java.util.Optional;

public interface RezervacijaService {


    Optional<Rezervacija> findOne(Integer id);
    List<Rezervacija> findAll();
    Rezervacija create(Rezervacija rezervacija) throws Exception;
    Rezervacija update(Rezervacija r);
    Rezervacija insert(Rezervacija r);
    void delete(Integer id);
}
