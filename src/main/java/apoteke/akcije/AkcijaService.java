package apoteke.akcije;


import java.util.List;
import java.util.Optional;

public interface AkcijaService {
    List<Akcija> findAll();
    Akcija findOne(Integer id);
    Akcija create(Akcija akcija) throws Exception;
    void delete(Integer id);
    Akcija update(Akcija akcija) throws Exception;


}
