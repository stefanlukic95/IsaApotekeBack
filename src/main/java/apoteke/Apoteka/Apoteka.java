package apoteke.Apoteka;


import apoteke.Korisnik.Korisnik;
import apoteke.Lek.Lek;
import apoteke.Narudzbenice.Narudzbenica;
import apoteke.Pregled.Pregled;
import apoteke.Savetovanje.Savetovanje;
import apoteke.akcije.Akcija;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Apoteka")
public class Apoteka {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "naziv")
    private String naziv;
    private String adresa;
    private String opis;
    @ElementCollection
    @OneToMany
    private List<Korisnik> dermatolozi;
    @ElementCollection
    @OneToMany
    private List<Korisnik> farmaceuti;
    @ElementCollection
    @OneToMany
    private List<Lek> lekovi;
    @ElementCollection
    @OneToMany
    private List<Akcija> akcije;
    @ElementCollection
    @OneToMany
    private List<Pregled> pregledi;
    @ElementCollection
    @OneToOne
    private Korisnik administratorApoteke;
    @ElementCollection
    @OneToMany
    private List<Savetovanje> savetovanja;
    @ElementCollection
    @OneToMany
    private List<Narudzbenica> narudzbenice;

     
    public Apoteka(){

    }
    public Apoteka(Integer id, String naziv, String adresa, String opis, List<Korisnik> dermatolozi, List<Korisnik> farmaceuti,List<Lek> lekovi,List<Akcija> akcije,List<Pregled> pregledi,Korisnik administratorApoteke,List<Savetovanje> savetovanja,List<Narudzbenica> narudzbenice) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.opis = opis;
        this.dermatolozi = dermatolozi;
        this.farmaceuti = farmaceuti;
        this.lekovi = lekovi;
        this.akcije = akcije;
        this.pregledi = pregledi;
        this.administratorApoteke = administratorApoteke;
        this.savetovanja = savetovanja;
        this.narudzbenice = narudzbenice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Korisnik> getDermatolozi() {
        return dermatolozi;
    }

    public void setDermatolozi(List<Korisnik> dermatolozi) {
        this.dermatolozi = dermatolozi;
    }

    public List<Korisnik> getFarmaceuti() {
        return farmaceuti;
    }

    public void setFarmaceuti(List<Korisnik> farmaceuti) {
        this.farmaceuti = farmaceuti;
    }

    public List<Lek> getLekovi() {
        return lekovi;
    }

    public void setLekovi(List<Lek> lekovi) {
        this.lekovi = lekovi;
    }

    public List<Akcija> getAkcije() {
        return akcije;
    }

    public void setAkcije(List<Akcija> akcije) {
        this.akcije = akcije;
    }

    public List<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(List<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    public Korisnik getAdministratorApoteke() {
        return administratorApoteke;
    }

    public void setAdministratorApoteke(Korisnik administratorApoteke) {
        this.administratorApoteke = administratorApoteke;
    }

    public List<Savetovanje> getSavetovanja() {
        return savetovanja;
    }

    public void setSavetovanja(List<Savetovanje> savetovanja) {
        this.savetovanja = savetovanja;
    }

    public List<Narudzbenica> getNarudzbenice() {
        return narudzbenice;
    }

    public void setNarudzbenice(List<Narudzbenica> narudzbenice) {
        this.narudzbenice = narudzbenice;
    }
}
