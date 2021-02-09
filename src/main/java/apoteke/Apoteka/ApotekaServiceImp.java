package apoteke.Apoteka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApotekaServiceImp implements ApotekaService{

    @Autowired
    private ApotekaRepository apotekaRepository;
    //svi
    @Override
    public List<Apoteka> findAll() {
        return apotekaRepository.findAll();
    }

    //po id
    @Override
    public Apoteka findOne(Integer id) {
        return apotekaRepository.findById(id).orElse(null);
    }

    @Override
    public Apoteka create(Apoteka apoteka) throws Exception {
        Apoteka  savedApoteka = this.apotekaRepository.save(apoteka);
        return savedApoteka;
    }

    @Override
    public void delete(Integer id) {
        this.apotekaRepository.deleteById(id);}

    @Override
    public Apoteka searchByApotekaName(String naziv) throws Exception {
        List<Apoteka> apoteke = this.apotekaRepository.findAll();

        for(Apoteka apoteka: apoteke){
            if(apoteka.getNaziv().equals(naziv)){
                return apoteka;
            }else{
                if(apoteka == null){
                    throw new Exception("Nije pronadjena apoteka.");
                }
            }
        }
        return null;
    }

    @Override
    public Apoteka searchByApotekaAdresa(String adresa) throws Exception {
        List<Apoteka> apoteke = this.apotekaRepository.findAll();

        for(Apoteka apoteka: apoteke){
            if(apoteka.getAdresa().equals(adresa)){
                return apoteka;
            }else{
                if(apoteka == null){
                    throw new Exception("Nije pronadjena apoteka.");
                }
            }
        }
        return null;
    }


    @Override
    public Apoteka update(Apoteka a)throws Exception{

        return apotekaRepository.save(a);
    }
}
