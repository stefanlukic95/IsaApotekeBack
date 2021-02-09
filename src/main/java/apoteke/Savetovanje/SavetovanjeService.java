package apoteke.Savetovanje;

import apoteke.Korisnik.Korisnik;

import java.util.List;

public interface SavetovanjeService {

    List<Savetovanje> findAll();

    Savetovanje findOne(Integer id);

    Savetovanje create(Savetovanje savetovanje) throws Exception;

    void delete(Integer id);

    Savetovanje searchSavetovanjeByDateName(String datum_vreme, String farmaceut) throws Exception;

    Savetovanje update(Savetovanje savetovanje) throws Exception;
}
