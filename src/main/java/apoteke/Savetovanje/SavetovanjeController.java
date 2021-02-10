package apoteke.Savetovanje;

import apoteke.Apoteka.Apoteka;
import apoteke.Apoteka.ApotekaService;
import apoteke.Korisnik.Korisnik;
import apoteke.Korisnik.KorisnikService;
import apoteke.Korisnik.registration.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class SavetovanjeController {

    @Autowired
    private SavetovanjeService savetovanjeService;

    @Autowired
    private ApotekaService apotekaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private KorisnikService korisnikService;

    public SavetovanjeController(SavetovanjeService savetovanjeService) {
        this.savetovanjeService = savetovanjeService;
    }

    //sva savetovanja
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/savetovanja",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Savetovanje>> getAll() {
        List<Savetovanje> savetovanja = savetovanjeService.findAll();
        return new ResponseEntity<List<Savetovanje>>(savetovanja, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            value = "/savetovanje/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Savetovanje> getSavetovanjeById(@PathVariable("id") Integer id) {

        Savetovanje savetovanje = this.savetovanjeService.findOne(id);
        if (savetovanje == null) {
            return new ResponseEntity<Savetovanje>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Savetovanje>(savetovanje, HttpStatus.OK);

    }

    //post za novo savetovanje
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/savetovanja/apoteke/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Savetovanje> insertSavetovanje(@RequestBody Savetovanje savetovanje, @PathVariable("id") Integer id) throws Exception {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Korisnik k = this.korisnikService.findByEmail(name);
        Savetovanje createdSavetovanje = this.savetovanjeService.create(savetovanje);
        createdSavetovanje.setPacijent(name);
        ArrayList<Savetovanje> savetovanja = new ArrayList<>();
        savetovanja.add(createdSavetovanje);

        createdSavetovanje.setPacijent(k.getIme());
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(k.getEmail());
        registrationEmail.setSubject("Savetovanje");
        registrationEmail.setText("Savetovanje je kreirano");
        registrationEmail.setFrom("poseti.me.isa@gmail.com");
        emailService.sendEmail(registrationEmail);


        Apoteka apotekaa = this.apotekaService.findOne(id);
        apotekaa.setSavetovanja(savetovanja);
        this.apotekaService.update(apotekaa);

        return new ResponseEntity<Savetovanje>(createdSavetovanje, HttpStatus.CREATED);
    }


    //brisanje leka po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/savetovanje/{id}"
    )
    public ResponseEntity<Savetovanje> deleteSavetovanje(@PathVariable("id") Integer id) {

        this.savetovanjeService.delete(id);
        return new ResponseEntity<Savetovanje>(HttpStatus.NO_CONTENT);
    }


    //pretraga lekova
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/savetovanja/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Savetovanje> searchSavetovanjeByDateName(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "datum_vreme", required = false) String datum_vreme,
                                                               @RequestParam(value = "farmaceut" , required = false) String farmaceut) throws Exception {


        Savetovanje savetovanje = this.savetovanjeService.searchSavetovanjeByDateName(datum_vreme,farmaceut);

        return new ResponseEntity<Savetovanje>(savetovanje, HttpStatus.OK);
    }

    //otkazivanje savetovanja
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/savetovanje/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Savetovanje> updateSavetovanje(@PathVariable("id") Integer id, @RequestBody Savetovanje savetovanje) throws Exception {
        Savetovanje savetovanjee = this.savetovanjeService.findOne(id);

        if (savetovanjee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        savetovanjee.setZakazano(false);


        Savetovanje updateSavetovanje = this.savetovanjeService.update(savetovanje);
        if (updateSavetovanje == null) {
            return new ResponseEntity<Savetovanje>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateSavetovanje, HttpStatus.OK);
    }
}