package apoteke.akcije;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AkcijaServiceImpl implements AkcijaService{

    @Autowired
    private AkcijaRepository akcijaRepository;
    //svi
    @Override
    public List<Akcija> findAll() {
        return akcijaRepository.findAll();
    }

    //po id
    @Override
    public Akcija findOne(Integer id) {
        return akcijaRepository.findById(id).orElse(null);
    }

    @Override
    public Akcija create(Akcija akcija) throws Exception {
        Akcija  savedApoteka = this.akcijaRepository.save(akcija);
        return savedApoteka;
    }

    @Override
    public void delete(Integer id) {
        this.akcijaRepository.deleteById(id);}

    @Override
    public Akcija update(Akcija a) {

        return akcijaRepository.save(a);
    }


}
