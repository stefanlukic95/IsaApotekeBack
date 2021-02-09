package apoteke.LekRezervacija;


import java.util.List;
import java.util.Optional;

public interface LekRezervacijaService {

    Optional<LekRezervacija> findOne(Integer id);
    List<LekRezervacija> findAll();
    LekRezervacija create(LekRezervacija rezervacija) throws Exception;
    LekRezervacija update(LekRezervacija r);
    LekRezervacija insert(LekRezervacija r);
    void delete(Integer id);
}
