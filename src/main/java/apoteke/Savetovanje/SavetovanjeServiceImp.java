package apoteke.Savetovanje;

import apoteke.Apoteka.Apoteka;
import apoteke.Apoteka.ApotekaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavetovanjeServiceImp implements SavetovanjeService {

    @Autowired
    private SavetovanjeRepository savetovanjeRepository;

    @Autowired
    private ApotekaRepository apotekaRepository;


    //svi
    @Override
    public List<Savetovanje> findAll() {
        return savetovanjeRepository.findAll();
    }

    //po id
    @Override
    public Savetovanje findOne(Integer id) {
        return savetovanjeRepository.findById(id).orElse(null);
    }

    @Override
    public Savetovanje create(Savetovanje savetovanje) throws Exception {
        List<Savetovanje> savetovanja = this.savetovanjeRepository.findAll();

        for (Savetovanje savetovanje1 : savetovanja) {
            if (savetovanje1.getDatum_vreme().toString().equals(savetovanje.getDatum_vreme().toString()) && savetovanje1.getFarmaceut().getIme().equals(savetovanje.getFarmaceut().getIme())) {
                return null;
            }
        }

        Savetovanje  savedSavetovanje = this.savetovanjeRepository.save(savetovanje);
        return savedSavetovanje;
    }

    @Override
    public void delete(Integer id) {
        this.savetovanjeRepository.deleteById(id);}

    @Override
    public Savetovanje searchSavetovanjeByDateName(String datum_vreme,String farmaceut) throws Exception {
        List<Savetovanje> savetovanja = this.savetovanjeRepository.findAll();


        for(Savetovanje savetovanje: savetovanja){
            if(!savetovanje.getDatum_vreme().toString().equals(datum_vreme) && savetovanje.getFarmaceut().getIme().toString().equals(farmaceut)){
                return savetovanje;
            }else{
                if(savetovanje == null){
                    throw new Exception("Nije pronadjeno savetovanje.");
                }
            }
        }
        return null;
    }


    @Override
    public Savetovanje update(Savetovanje s) {

        return savetovanjeRepository.save(s);
    }

}
