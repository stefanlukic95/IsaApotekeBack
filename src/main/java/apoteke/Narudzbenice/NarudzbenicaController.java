package apoteke.Narudzbenice;

import apoteke.Apoteka.Apoteka;
import apoteke.Apoteka.ApotekaService;
import apoteke.Lek.Lek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class NarudzbenicaController {

    @Autowired
    private NarudzbenicaService narudzbenicaService;

    @Autowired
    private ApotekaService apotekaService;


    public NarudzbenicaController(NarudzbenicaService narudzbenicaService){ this.narudzbenicaService = narudzbenicaService;}

    //sve narudzbenice
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/narudzbenice",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Narudzbenica>> getAll() {
        List<Narudzbenica> narudzbenice = narudzbenicaService.findAll();
        return new ResponseEntity<List<Narudzbenica>>(narudzbenice, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            value="/narudzbenica/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Narudzbenica> getNarudzbenicaById(@PathVariable("id") Integer id) {

        Narudzbenica narudzbenica = this.narudzbenicaService.findOne(id);
        if(narudzbenica == null){
            return new ResponseEntity<Narudzbenica>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Narudzbenica>(narudzbenica, HttpStatus.OK);

    }

    //post za novi narudzbenicu
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/narudzbenice/apoteke/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Narudzbenica> insertNarudzbenica(@RequestBody Narudzbenica narudzbenica,@PathVariable ("id") Integer id) throws Exception{

        Narudzbenica createdNarudzbenica  = this.narudzbenicaService.create(narudzbenica);
        ArrayList<Narudzbenica> narudzbenice = new ArrayList<>();
        narudzbenice.add(createdNarudzbenica);

        Apoteka apotekaa = this.apotekaService.findOne(id);
        apotekaa.setNarudzbenice(narudzbenice);
        this.apotekaService.update(apotekaa);

        return new ResponseEntity<Narudzbenica>(createdNarudzbenica, HttpStatus.CREATED);
    }


    //brisanje pregleda po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/narudzbenica/{id}"
    )
    public ResponseEntity<Narudzbenica> deleteNarudzbenica(@PathVariable("id") Integer id){

        this.narudzbenicaService.delete(id);
        return new ResponseEntity<Narudzbenica>(HttpStatus.NO_CONTENT);
    }

    //prihvatanje narudzbenice
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/narudzbenica/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Narudzbenica> updateNarudzbenica(@PathVariable("id") Integer id, @RequestBody Narudzbenica narudzbenica) throws Exception {
        Narudzbenica narudzbenica1 = this.narudzbenicaService.findOne(id);

        if (narudzbenica1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        narudzbenica1.setPrihvacena(true);

        Narudzbenica updateNarudzbenica = this.narudzbenicaService.update(narudzbenica);
        if (updateNarudzbenica == null) {
            return new ResponseEntity<Narudzbenica>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateNarudzbenica, HttpStatus.OK);
    }

}
