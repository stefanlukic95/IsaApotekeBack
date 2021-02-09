package apoteke.LekRezervacija;

import apoteke.Lek.LekService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LekRezervacijaServiceImp implements LekRezervacijaService {

    @Autowired
    private LekRezervacijaRepository lekRezervacijaRepository;


    @Override
    public List<LekRezervacija> findAll(){
        List<LekRezervacija> rez = this.lekRezervacijaRepository.findAll();
        return rez;
    }

    @Override
    public Optional<LekRezervacija> findOne(Integer id) {
        return lekRezervacijaRepository.findById(id);
    }


    @Override
    public LekRezervacija create(LekRezervacija rezervacija) throws Exception {
        LekRezervacija  savedRezervacija = this.lekRezervacijaRepository.save(rezervacija);
        return savedRezervacija;
    }

    @Override
    public LekRezervacija update(LekRezervacija r) {

        return lekRezervacijaRepository.save(r);
    }

    @Override
    public LekRezervacija insert(LekRezervacija r) {
        return lekRezervacijaRepository.save(r);
    }


    @Override
    public void delete(Integer r) {
        lekRezervacijaRepository.deleteById(r);
    }
}
