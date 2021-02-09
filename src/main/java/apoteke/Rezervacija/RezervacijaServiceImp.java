package apoteke.Rezervacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaServiceImp implements RezervacijaService{


    @Autowired
    private RezervacijaRepository rezervacijaRepository;

    @Override
    public List<Rezervacija> findAll(){
        List<Rezervacija> rez = this.rezervacijaRepository.findAll();
        return rez;
    }

    @Override
    public Optional<Rezervacija> findOne(Integer id) {
        return rezervacijaRepository.findById(id);
    }


    @Override
    public Rezervacija create(Rezervacija rezervacija) throws Exception {
        Rezervacija  savedRezervacija = this.rezervacijaRepository.save(rezervacija);
        return savedRezervacija;
    }

    @Override
    public Rezervacija update(Rezervacija r) {

        return rezervacijaRepository.save(r);
    }

    @Override
    public Rezervacija insert(Rezervacija r) {
        return rezervacijaRepository.save(r);
    }


    @Override
    public void delete(Integer r) {
        rezervacijaRepository.deleteById(r);
    }
}
