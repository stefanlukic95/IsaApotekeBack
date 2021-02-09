package apoteke.Rezervacija;

import apoteke.Korisnik.Korisnik;
import apoteke.Korisnik.KorisnikService;
import apoteke.Korisnik.registration.EmailService;
import apoteke.Pregled.Pregled;
import apoteke.Pregled.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class RezervacijaController {

    @Autowired
    private RezervacijaService rezervacijaService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PregledService pregledService;

    @Autowired
    private EmailService emailService;


    public RezervacijaController(RezervacijaService rezervacijaService){this.rezervacijaService = rezervacijaService;}


    //kreiranje rezervacije predefinisanog pregleda
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/rezervacijaPredef/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Rezervacija> insertRezervacija(@PathVariable("id") Integer pregledId, @RequestBody Rezervacija rezervacija) throws Exception{
        Rezervacija createdRezervacija  = this.rezervacijaService.create(rezervacija);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Korisnik k = this.korisnikService.findByEmail(name);
        Optional<Pregled> pregled = this.pregledService.findOne(pregledId);


        createdRezervacija.setPacijent(name);
        createdRezervacija.setPregled(pregled.get());



        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(k.getEmail());
        registrationEmail.setSubject("One click reservation");
        registrationEmail.setText("Your one click reservation has been made!");
        registrationEmail.setFrom("poseti.me.isa@gmail.com");
        emailService.sendEmail(registrationEmail);

        createdRezervacija.getPregled().setRezervisan(true);
        rezervacijaService.insert(createdRezervacija);

        return new ResponseEntity<Rezervacija>(createdRezervacija, HttpStatus.CREATED);
    }

    //otkazivanje rezervacije
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/rezervacijaPredefUpdt/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Rezervacija> cancelRezervacija(@PathVariable("id") Integer id, @RequestBody Rezervacija rezervacija) throws Exception{
       Optional<Rezervacija> rez = this.rezervacijaService.findOne(id);

        if(rez == null){
            return new ResponseEntity<Rezervacija>(HttpStatus.NOT_FOUND);
        }


        rez.get().getPregled().setRezervisan(false);
        Rezervacija updateRezervacija = this.rezervacijaService.update(rez.get());
        if (updateRezervacija == null) {
            return new ResponseEntity<Rezervacija>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Rezervacija>(updateRezervacija, HttpStatus.OK);
    }



    //sve rezervacije
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/rezervacije",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Rezervacija>> getAll() {
        List<Rezervacija> rezervacije = rezervacijaService.findAll();
        return new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            value="/rezervacije/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<Rezervacija>> getRezervacijaById(@PathVariable("id") Integer id) {

        Optional<Rezervacija> rezervacija = this.rezervacijaService.findOne(id);
        if(rezervacija == null){
            return new ResponseEntity<Optional<Rezervacija>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Rezervacija>>(rezervacija, HttpStatus.OK);

    }


    //brisanje rezervacije po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/rezervacije/{id}"
    )
    public ResponseEntity<Rezervacija> deleteRezervacija(@PathVariable("id") Integer id){

        this.rezervacijaService.delete(id);
        return new ResponseEntity<Rezervacija>(HttpStatus.NO_CONTENT);
    }

}
