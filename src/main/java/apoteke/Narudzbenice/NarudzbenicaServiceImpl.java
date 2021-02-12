package apoteke.Narudzbenice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NarudzbenicaServiceImpl implements NarudzbenicaService {

    @Autowired
    private NarudzbenicaRepository narudzbenicaRepository;
    //svi
    @Override
    public List<Narudzbenica> findAll() {
        return narudzbenicaRepository.findAll();
    }

    //po id
    @Override
    public Narudzbenica findOne(Integer id) {
        return narudzbenicaRepository.findById(id).orElse(null);
    }

    @Override
    public Narudzbenica create(Narudzbenica narudzbenica) throws Exception {
        Narudzbenica  savedNarudzbenica = this.narudzbenicaRepository.save(narudzbenica);

        return savedNarudzbenica;
    }

    @Override
    public void delete(Integer id) {
        this.narudzbenicaRepository.deleteById(id);}

    @Override
    public Narudzbenica update(Narudzbenica n) {

        return narudzbenicaRepository.save(n);
    }

}


