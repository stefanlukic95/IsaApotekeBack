package apoteke.akcije;


import apoteke.Apoteka.Apoteka;
import apoteke.Apoteka.ApotekaService;
import apoteke.Korisnik.Korisnik;
import apoteke.Korisnik.KorisnikService;
import apoteke.Korisnik.registration.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AkcijaController {

    @Autowired
    private AkcijaService akcijaService;

    @Autowired
    private ApotekaService apotekaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private KorisnikService korisnikService;

    public AkcijaController(AkcijaService akcijaService){this.akcijaService=akcijaService;}

    //sve akcije
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/akcije",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Akcija>> getAll() {
        List<Akcija> akcije = akcijaService.findAll();
        return new ResponseEntity<List<Akcija>>(akcije, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            value="/akcija/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Akcija> getAkcijeById(@PathVariable("id") Integer id) {

        Akcija apoteka = this.akcijaService.findOne(id);
        if(apoteka == null){
            return new ResponseEntity<Akcija>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Akcija>(apoteka, HttpStatus.OK);

    }

    //post za novu akciju
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/akcije/apoteke/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Akcija> insertAkcija(@RequestBody Akcija akcija, @PathVariable ("id") Integer id) throws Exception{



        String appUrl = "http://localhost:8080";




        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo("stefan.lukic@yahoo.com","stefan.lukic777@gmail.com");
        registrationEmail.setSubject("Аkcije!");
        registrationEmail.setText("Nove akcije su u toku");
        registrationEmail.setFrom("poseti.me.isa@gmail.com");
        emailService.sendEmail(registrationEmail);


        Akcija createdAkcija  = this.akcijaService.create(akcija);

        ArrayList<Akcija> akcije = new ArrayList<>();
        akcije.add(createdAkcija);

        Apoteka apotekaa = this.apotekaService.findOne(id);


            apotekaa.getAkcije().add(createdAkcija);
            this.apotekaService.update(apotekaa);

        return new ResponseEntity<Akcija>(createdAkcija, HttpStatus.CREATED);
    }


    //brisanje akcije po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/akcija/{id}"
    )
    public ResponseEntity<Akcija> deleteAkcija(@PathVariable("id") Integer id){

        this.akcijaService.delete(id);
        return new ResponseEntity<Akcija>(HttpStatus.NO_CONTENT);
    }


    //promena akcije
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/akcija/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Akcija> updateAkcija(@PathVariable("id") Integer id, @RequestBody Akcija akcija) throws Exception {
        Akcija akcijaa = this.akcijaService.findOne(id);

        if (akcijaa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        akcijaa.setNaziv(akcija.getNaziv());
        akcijaa.setPeriod_vazenja(akcija.getPeriod_vazenja());
        akcijaa.setPretplatnici(akcija.getPretplatnici());

        Akcija updateAkcija = this.akcijaService.update(akcija);
        if (updateAkcija == null) {
            return new ResponseEntity<Akcija>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateAkcija, HttpStatus.OK);
    }

}
