package apoteke.Lek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LekServiceImp implements LekService {


    @Autowired
    private LekRepository lekRepository;
    //svi
    @Override
    public List<Lek> findAll() {
        return lekRepository.findAll();
    }

    //po id
    @Override
    public Lek findOne(Integer id) {
        return lekRepository.findById(id).orElse(null);
    }

    @Override
    public Lek create(Lek lek) throws Exception {
        Lek  savedLek = this.lekRepository.save(lek);
        return savedLek;
    }

    @Override
    public void delete(Integer id) {
        this.lekRepository.deleteById(id);}

    @Override
    public Lek searchLekByName(String naziv) throws Exception {
        List<Lek> lekovi = this.lekRepository.findAll();

        for(Lek lek: lekovi){
            if(lek.getNaziv().equals(naziv)){
                return lek;
            }else{
                if(lek == null){
                    throw new Exception("Nije pronadjen lek.");
                }
            }
        }
        return null;
    }


    @Override
    public Lek update(Lek k) {

        return lekRepository.save(k);
    }

}
