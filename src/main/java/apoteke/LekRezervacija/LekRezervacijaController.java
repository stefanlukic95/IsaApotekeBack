package apoteke.LekRezervacija;


import apoteke.Korisnik.Korisnik;
import apoteke.Korisnik.KorisnikService;
import apoteke.Korisnik.registration.EmailService;
import apoteke.Lek.Lek;
import apoteke.Lek.LekService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LekRezervacijaController {

    @Autowired
    private LekRezervacijaService lekRezervacijaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private LekService lekService;


    public LekRezervacijaController(LekRezervacijaService lekRezervacijaService){this.lekRezervacijaService= lekRezervacijaService;}


    //kreiranje predefinisane rezervacije
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/rezervacijaLek/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LekRezervacija> insertLekRezervacija(@PathVariable("id") Integer lekId, @RequestBody LekRezervacija rezervacija) throws Exception{
        LekRezervacija createdRezervacija  = this.lekRezervacijaService.create(rezervacija);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Korisnik k = this.korisnikService.findByEmail(name);
        Lek lek = this.lekService.findOne(lekId);


        createdRezervacija.setPacijent(name);
        createdRezervacija.setLek(lek);
        createdRezervacija.setDatum_vreme(createdRezervacija.getDatum_vreme());


        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(k.getEmail());
        registrationEmail.setSubject("Drug reservation");
        registrationEmail.setText("Your one click reservation has been made!");
        registrationEmail.setFrom("poseti.me.isa@gmail.com");
        emailService.sendEmail(registrationEmail);

        createdRezervacija.getLek().setRezervisan(true);
        lekRezervacijaService.insert(createdRezervacija);
        k.getRezervisani_lekovi().add(lek);

        return new ResponseEntity<LekRezervacija>(createdRezervacija, HttpStatus.CREATED);
    }

    //sve rezervacije lekova
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/rezervacijeLek",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LekRezervacija>> getAll() {
        List<LekRezervacija> rezervacije = lekRezervacijaService.findAll();
        return new ResponseEntity<List<LekRezervacija>>(rezervacije, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            method = RequestMethod.GET,
            value="/rezervacijaLek/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<LekRezervacija>> getRezervacijaLekById(@PathVariable("id") Integer id) {

        Optional<LekRezervacija> rezervacija = this.lekRezervacijaService.findOne(id);
        if(rezervacija == null){
            return new ResponseEntity<Optional<LekRezervacija>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<LekRezervacija>>(rezervacija, HttpStatus.OK);

    }


    //brisanje rezervacije po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/rezervacijaLek/{id}"
    )
    public ResponseEntity<LekRezervacija> deleteRezervacijaLek(@PathVariable("id") Integer id){

        this.lekRezervacijaService.delete(id);
        return new ResponseEntity<LekRezervacija>(HttpStatus.NO_CONTENT);
    }
}
