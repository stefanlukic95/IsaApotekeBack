package apoteke.Pregled;

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
public class PregledController {

    @Autowired
    private PregledService pregledService;

    @Autowired
    private ApotekaService apotekaService;


    public PregledController(PregledService pregledService){ this.pregledService = pregledService;}

    //svi pregledi
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/pregledi",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Pregled>> getAll() {
        List<Pregled> pregledi = pregledService.findAll();
        return new ResponseEntity<List<Pregled>>(pregledi, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            value="/pregled/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pregled> getPregledById(@PathVariable("id") Integer id) {

        Pregled pregled = this.pregledService.findOne(id);
        if(pregled == null){
            return new ResponseEntity<Pregled>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pregled>(pregled, HttpStatus.OK);

    }

    //post za novi pregled
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/pregledi/apoteke/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pregled> insertPregled(@RequestBody Pregled pregled,@PathVariable ("id") Integer id) throws Exception{

        Pregled createdPregled  = this.pregledService.create(pregled);
        /*ArrayList<Pregled> pregledi = new ArrayList<>();
        pregledi.add(createdPregled);*/

        Apoteka apotekaa = this.apotekaService.findOne(id);
        apotekaa.getPregledi().add(createdPregled);
        this.apotekaService.update(apotekaa);

        return new ResponseEntity<Pregled>(createdPregled, HttpStatus.CREATED);
    }


    //brisanje pregleda po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/pregledi/{id}"
    )
    public ResponseEntity<Pregled> deletePregled(@PathVariable("id") Integer id){

        this.pregledService.delete(id);
        return new ResponseEntity<Pregled>(HttpStatus.NO_CONTENT);
    }


}
