package apoteke.Narudzbenice;

import java.util.List;

public interface NarudzbenicaService {
    List<Narudzbenica> findAll();
    Narudzbenica findOne(Integer id);
    Narudzbenica create(Narudzbenica narudzbenica) throws Exception;
    void delete(Integer id);
    Narudzbenica update(Narudzbenica narudzbenica) throws Exception;
}
