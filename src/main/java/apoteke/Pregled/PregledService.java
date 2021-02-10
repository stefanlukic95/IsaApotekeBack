package apoteke.Pregled;


import java.util.List;
import java.util.Optional;

public interface PregledService {
    List<Pregled> findAll();
    Pregled findOne(Integer id);
    Pregled create(Pregled lek) throws Exception;
    void delete(Integer id);

}
