package apoteke.Apoteka;

import java.util.List;
import java.util.Optional;

public interface ApotekaService {


    List<Apoteka> findAll();
    Apoteka findOne(Integer id);
    Apoteka create(Apoteka apoteka) throws Exception;
    void delete(Integer id);
    Apoteka searchByApotekaName(String naziv) throws Exception;
    Apoteka searchByApotekaAdresa(String adresa) throws Exception;
    Apoteka update(Apoteka apoteka) throws Exception;
}
