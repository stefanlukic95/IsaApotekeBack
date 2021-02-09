package apoteke.Lek;

import java.util.List;
import java.util.Optional;

public interface LekService {

    List<Lek> findAll();

    Lek findOne(Integer id);

    Lek create(Lek lek) throws Exception;

    void delete(Integer id);

    Lek searchLekByName(String naziv) throws Exception;

    Lek update(Lek lek) throws Exception;


}
