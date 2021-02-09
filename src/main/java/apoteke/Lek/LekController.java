package apoteke.Lek;

import apoteke.Apoteka.Apoteka;
import apoteke.Apoteka.ApotekaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LekController {

    @Autowired
    private LekService lekService;

    @Autowired
    private ApotekaService apotekaService;

    public LekController(LekService lekService){ this.lekService = lekService;}

    //svi lekovi
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/lekovi",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Lek>> getAll() {
        List<Lek> lekovi = lekService.findAll();
        return new ResponseEntity<List<Lek>>(lekovi, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            value="/lek/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lek> getLekById(@PathVariable("id") Integer id) {

        Lek lek = this.lekService.findOne(id);
        if(lek == null){
            return new ResponseEntity<Lek>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Lek>(lek, HttpStatus.OK);

    }

    //post za novi lek
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/lekovi/apoteke/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lek> insertLek(@RequestBody Lek lek,@PathVariable ("id") Integer id) throws Exception{

        Lek createdLek  = this.lekService.create(lek);

       /* ArrayList<Lek> lekovi = new ArrayList<>();
        lekovi.add(createdLek);*/


        Apoteka apotekaa = this.apotekaService.findOne(id);
        apotekaa.getLekovi().add(createdLek);
        this.apotekaService.update(apotekaa);

        return new ResponseEntity<Lek>(createdLek, HttpStatus.CREATED);
    }


    //brisanje leka po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/lek/{id}"
    )
    public ResponseEntity<Lek> deleteLek(@PathVariable("id") Integer id){

        this.lekService.delete(id);
        return new ResponseEntity<Lek>(HttpStatus.NO_CONTENT);
    }


    //pretraga lekova
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/lekovi/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lek> searchByNazivLeka(@RequestParam(value = "naziv", required = false) String naziv) throws Exception {


        Lek lek = this.lekService.searchLekByName(naziv);

        return new ResponseEntity<Lek>(lek, HttpStatus.OK);
    }

    //promena cene leka
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/lek/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lek> updateLek(@PathVariable("id") Integer id, @RequestBody Lek lek) throws Exception {
       Lek lekk = this.lekService.findOne(id);

        if (lekk == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lekk.setCena(lek.getCena());


        Lek updateLek = this.lekService.update(lek);
        if (updateLek == null) {
            return new ResponseEntity<Lek>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateLek, HttpStatus.OK);
    }
}
