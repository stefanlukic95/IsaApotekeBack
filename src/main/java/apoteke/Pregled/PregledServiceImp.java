package apoteke.Pregled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PregledServiceImp implements PregledService {

    @Autowired
    private PregledRepository pregledRepository;
    //svi
    @Override
    public List<Pregled> findAll() {
        return pregledRepository.findAll();
    }

    //po id
    @Override
    public Optional<Pregled> findOne(Integer id) {
        return pregledRepository.findById(id);
    }

    @Override
    public Pregled create(Pregled pregled) throws Exception {
        Pregled  savedPregled = this.pregledRepository.save(pregled);
        savedPregled.setRezervisan(false);
        return savedPregled;
    }

    @Override
    public void delete(Integer id) {
        this.pregledRepository.deleteById(id);}

}
