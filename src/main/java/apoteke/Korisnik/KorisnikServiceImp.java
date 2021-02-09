package apoteke.Korisnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "korisnikService")
public class KorisnikServiceImp implements UserDetailsService,KorisnikService {


    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;



    //load
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Korisnik korisnik = findByEmail(s);
        if(korisnik == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(String r: korisnik.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(korisnik.getEmail(), korisnik.getPassword(), authorities);

        return userDetails;
    }


    //svi
    @Override
    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    //po id
    @Override
    public Optional<Korisnik> findOne(Integer id) {
        return korisnikRepository.findById(id);
    }


    @Override
    public Korisnik insert(Korisnik k) {
        k.setPassword(bcryptEncoder.encode(k.getPassword()));
        return korisnikRepository.save(k);
    }


    //po mailu
    @Override
    public Korisnik findByEmail(String email) {
        List<Korisnik> korisnici = korisnikRepository.findAll();

        for(Korisnik k : korisnici) {
            if(k.getEmail().equals(email)) {
                return k;
            }
        }
        return null;
    }
    @Override
    public void delete(Integer id) {
        this.korisnikRepository.deleteById(id);
    }
    @Override
    public Korisnik findByConfirmationToken(String token) {
        List<Korisnik> korisnici = korisnikRepository.findAll();

        for(Korisnik k : korisnici) {
            if (!k.isEnabled()) {
                if (k.getConfirmationToken().equals(token)) {
                    return k;
                }
            }
        }
        return null;

    }


    @Override
    public Korisnik save(Korisnik k) {
        return korisnikRepository.save(k);
    }


    @Override
    public List<Korisnik> findByName(String name) {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<Korisnik> pronadjeni = new ArrayList<Korisnik>();
        for(Korisnik k : korisnici) {
            if(k.getIme().toLowerCase().contains(name.toLowerCase())) {
                pronadjeni.add(k);
            }
        }
        return pronadjeni;
    }

    @Override
    public List<Korisnik> findBySurname(String surname) {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<Korisnik> pronadjeni = new ArrayList<Korisnik>();
        for(Korisnik k : korisnici) {
            if(k.getPrezime().toLowerCase().contains(surname.toLowerCase())) {
                pronadjeni.add(k);
            }
        }
        return pronadjeni;
    }

    @Override
    public List<Korisnik> findByInfo(String name, String surname) {
        List<Korisnik> korisnici = korisnikRepository.findAll();
        List<Korisnik> pronadjeni = new ArrayList<Korisnik>();
        for(Korisnik k : korisnici) {
            if(k.getIme().toLowerCase().contains(name.toLowerCase()) && k.getPrezime().toLowerCase().contains(surname.toLowerCase())) {
                pronadjeni.add(k);
            }
        }
        return pronadjeni;
    }


}
