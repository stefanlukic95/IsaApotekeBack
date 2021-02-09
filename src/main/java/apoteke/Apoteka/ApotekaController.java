package apoteke.Apoteka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ApotekaController {

    @Autowired
    private ApotekaService apotekaService;

    @Autowired
    private ApotekaRepository apotekaRepository;

    public ApotekaController(ApotekaService apotekaService){this.apotekaService=apotekaService;}

    //sve apoteke
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/apoteke",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Apoteka>> getAll() {
        List<Apoteka> apoteke = apotekaService.findAll();
        return new ResponseEntity<List<Apoteka>>(apoteke, HttpStatus.OK);
    }


    // po id-u
    @RequestMapping(
            value="/apoteka/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Apoteka> getApotekaById(@PathVariable("id") Integer id) {

        Apoteka apoteka = this.apotekaService.findOne(id);
        if(apoteka == null){
            return new ResponseEntity<Apoteka>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Apoteka>(apoteka, HttpStatus.OK);

    }

    //post za novu apoteku
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/apoteke",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Apoteka> insertApoteka(@RequestBody Apoteka apoteka) throws Exception{

        Apoteka createdApoteka  = this.apotekaService.create(apoteka);


        return new ResponseEntity<Apoteka>(createdApoteka, HttpStatus.CREATED);
    }


    //brisanje apoteke po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/apoteka/{id}"
    )
    public ResponseEntity<Apoteka> deleteApoteka(@PathVariable("id") Integer id){

        this.apotekaService.delete(id);
        return new ResponseEntity<Apoteka>(HttpStatus.NO_CONTENT);
    }


    //pretraga apoteka po nazivu
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/apoteke/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Apoteka> searchByNazivApoteke(@RequestParam(value = "naziv", required = false) String naziv) throws Exception {


        Apoteka apoteka = this.apotekaService.searchByApotekaName(naziv);

        return new ResponseEntity<Apoteka>(apoteka, HttpStatus.OK);
    }

    //pretraga apoteka po adresi
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/apoteke/searchAdresa",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Apoteka> searchByAdresaApoteke(@RequestParam(value = "adresa", required = false) String adresa) throws Exception {


        Apoteka apoteka = this.apotekaService.searchByApotekaAdresa(adresa);

        return new ResponseEntity<Apoteka>(apoteka, HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/apoteka/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Apoteka> updateApoteka(@PathVariable("id") Integer id, @RequestBody Apoteka apoteka) throws Exception {
        Apoteka apotekaa = this.apotekaRepository.findById(id).orElse(null);

        if (apotekaa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        apotekaa.setNaziv(apoteka.getNaziv());
        apotekaa.setOpis(apoteka.getOpis());
        apotekaa.setAkcije(apoteka.getAkcije());
        apotekaa.setAdresa(apoteka.getAdresa());
        apoteka.setDermatolozi(apoteka.getDermatolozi());
        apoteka.setFarmaceuti(apoteka.getFarmaceuti());
        apoteka.setLekovi(apoteka.getLekovi());

        Apoteka updateApoteka = this.apotekaService.update(apotekaa);


        if (updateApoteka == null) {
            return new ResponseEntity<Apoteka>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateApoteka,HttpStatus.OK);
    }
}
