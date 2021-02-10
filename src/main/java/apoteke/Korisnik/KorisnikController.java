package apoteke.Korisnik;


import apoteke.Korisnik.registration.EmailService;
import apoteke.Lek.Lek;
import apoteke.Lek.LekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RestController
@CrossOrigin("*")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;


    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;


/*    @Autowired
    private LekService lekService;*/


    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/korisnici",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Korisnik>> getAll() {
        List<Korisnik> korisnici = korisnikService.findAll();
        return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);
    }

    //korisnik po id-u
    @RequestMapping(
            value="/korisnik/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Optional<Korisnik>> getKorisnikById(@PathVariable ("id") Integer id) {

        Optional<Korisnik> k = this.korisnikService.findOne(id);
        if(k == null){
            return new ResponseEntity<Optional<Korisnik>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Korisnik>>(k, HttpStatus.OK);

    }

    //user po mailu
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/{email}/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> getKorisnik(@PathVariable("email") String email) {
        Korisnik k = korisnikService.findByEmail(email);
        System.out.println(k.getEmail());
        return new ResponseEntity<Korisnik>(k,HttpStatus.OK);
    }

    //ulogovan korisnik
    @RequestMapping(
            value="/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> getLoggedInKorisnik(Korisnik korisnik) {

        Korisnik user = (Korisnik) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getIme();
        korisnik.setIme(name);
        return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);

    }




    //registracija
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> insertKorisnik(@RequestBody Korisnik korisnik){


        Korisnik k = korisnikService.findByEmail(korisnik.getEmail());

        if(k != null) {

            System.out.println("Vec postiji korisnik sa zadatim emailom");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        List<String> roles = new ArrayList<String>();
        roles.add("PACIJENT");
        korisnik.setRoles(roles);
        korisnik.setEnabled(false);
        korisnik.setPasswordChanged(false);

        korisnik.setConfirmationToken(UUID.randomUUID().toString());


        String appUrl = "http://localhost:8080";

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(korisnik.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/confirm?token=" + korisnik.getConfirmationToken());
        registrationEmail.setFrom("poseti.me.isa@gmail.com");
        emailService.sendEmail(registrationEmail);


        Korisnik insertedKorisnik = korisnikService.insert(korisnik);
        return new ResponseEntity<Korisnik>(insertedKorisnik, HttpStatus.OK);
    }


    //confirmation token
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/confirm"
    )
    public RedirectView confirm(@RequestParam("token") String confirmationToken){
        Korisnik k = korisnikService.findByConfirmationToken(confirmationToken);
        k.setEnabled(true);
        korisnikService.save(k);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:4200/login");
        return redirectView;
    }

    //izmena licnih podataka
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/user/{email}/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> updateKorisnik(@PathVariable("email") String email, @RequestBody Korisnik korisnik) throws Exception{
        Korisnik user = this.korisnikService.findByEmail(email);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setIme(korisnik.getIme());
        user.setPrezime(korisnik.getPrezime());
        user.setRezervisani_lekovi(korisnik.getRezervisani_lekovi());

        Korisnik updatedUser = this.korisnikService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //promena sifre
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/passwordChange/{email}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Korisnik> updatePassword(@PathVariable("email") String email, @RequestBody Korisnik korisnik) throws Exception{

        System.out.println("Update password prosledjen mail: " + email);

        System.out.println("prosledjena sifra: " + korisnik.getPassword());

        String mail = email.concat(".com");

        Korisnik user = this.korisnikService.findByEmail(mail);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        user.setPassword(bcryptEncoder.encode(korisnik.getPassword()));
        user.setPasswordChanged(true);

        Korisnik updatedUser = this.korisnikService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/korisnik/{id}"
    )
    public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable("id") Integer id){
        this.korisnikService.delete(id);
        return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
    }


    //pretraga po imenu i prezimenu
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/parameters",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Korisnik>> getUserByName(@RequestParam Map<String,String> requestParams)  {
        String ime = requestParams.get("ime");
        String prezime = requestParams.get("prezime");


        if(ime.equals("") && !prezime.equals("")) {
            return new ResponseEntity<List<Korisnik>>(korisnikService.findBySurname(prezime),HttpStatus.OK);
        }

        if(prezime.equals("") && !ime.equals("")) {
            return new ResponseEntity<List<Korisnik>>(korisnikService.findByName(ime), HttpStatus.OK);
        }

        if(!ime.equals("") && !prezime.equals("")) {
            return new ResponseEntity<List<Korisnik>>(korisnikService.findByInfo(ime,prezime), HttpStatus.OK);
        }


        return new ResponseEntity<List<Korisnik>>(HttpStatus.NOT_FOUND);

    }


}
