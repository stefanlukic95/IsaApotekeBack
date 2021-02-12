package apoteke.GodisnjiOdmor;

import apoteke.Korisnik.Korisnik;
import apoteke.Korisnik.KorisnikService;
import apoteke.Korisnik.registration.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GodisnjiOdmorController {

    @Autowired
    private GodisnjiOdmorService godisnjiOdmorService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private EmailService emailService;

    public GodisnjiOdmorController(GodisnjiOdmorService godisnjiOdmorService){ this.godisnjiOdmorService = godisnjiOdmorService;}

    //svi godisnji odmori
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/godisnjiOdmori",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GodisnjiOdmor>> getAll() {
        List<GodisnjiOdmor> godisnjiOdmori = godisnjiOdmorService.findAll();
        return new ResponseEntity<List<GodisnjiOdmor>>(godisnjiOdmori, HttpStatus.OK);
    }

    // po id-u
    @RequestMapping(
            value="/godisnjiOdmor/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GodisnjiOdmor> getGodisnjiOdmorById(@PathVariable("id") Integer id) {

        GodisnjiOdmor godisnjiOdmor = this.godisnjiOdmorService.findOne(id);

        if(godisnjiOdmor == null){
            return new ResponseEntity<GodisnjiOdmor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GodisnjiOdmor>(godisnjiOdmor, HttpStatus.OK);

    }

    //post za novi godisnji odmor
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/godisnjiOdmori",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GodisnjiOdmor> insertGodisnjiOdmor(@RequestBody GodisnjiOdmor godisnjiOdmor) throws Exception{

        GodisnjiOdmor createdGodisnjiOdmor  = this.godisnjiOdmorService.create(godisnjiOdmor);


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Korisnik k = this.korisnikService.findByEmail(name);

        createdGodisnjiOdmor.setKorisnik(name);
        godisnjiOdmorService.update(createdGodisnjiOdmor);
        return new ResponseEntity<GodisnjiOdmor>(createdGodisnjiOdmor, HttpStatus.CREATED);
    }


    //brisanje odmora po id-u
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/godisnjiOdmor/{id}"
    )
    public ResponseEntity<GodisnjiOdmor> deleteGodisnjiOdmor(@PathVariable("id") Integer id){

        this.godisnjiOdmorService.delete(id);
        return new ResponseEntity<GodisnjiOdmor>(HttpStatus.NO_CONTENT);
    }

    //odobravanje godisnjeg odmora
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/godisnjiOdmor/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GodisnjiOdmor> updateGodisnjiOdmor(@PathVariable("id") Integer id, @RequestBody GodisnjiOdmor godisnjiOdmor) throws Exception {
        GodisnjiOdmor godisnjiOdmor1 = this.godisnjiOdmorService.findOne(id);

        if (godisnjiOdmor1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        GodisnjiOdmor updateGodisnjiOdmor = this.godisnjiOdmorService.update(godisnjiOdmor);
        if (updateGodisnjiOdmor == null) {
            return new ResponseEntity<GodisnjiOdmor>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String k =  godisnjiOdmor1.getKorisnik();
        godisnjiOdmor1.setOdobren(godisnjiOdmor.isOdobren());
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        if(godisnjiOdmor1.isOdobren() == true) {
            registrationEmail.setTo(k);
            registrationEmail.setSubject("Godisnji Odmor");
            registrationEmail.setText("Vas Godisnji odmor je odobren.");
            registrationEmail.setFrom("poseti.me.isa@gmail.com");
            emailService.sendEmail(registrationEmail);
        }



        if(godisnjiOdmor1.isOdobren() != true){
            SimpleMailMessage registrationEmail1 = new SimpleMailMessage();
            registrationEmail.setTo(k);
            registrationEmail.setSubject("Godisnji Odmor");
            registrationEmail.setText("Vas Godisnji odmor je odbijen.Izaberite drugi termin");
            registrationEmail.setFrom("poseti.me.isa@gmail.com");
            emailService.sendEmail(registrationEmail);
        }
        return new ResponseEntity<>(updateGodisnjiOdmor, HttpStatus.OK);
    }

}
