package apoteke.akcije;

import apoteke.Korisnik.Korisnik;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Akcija")
public class Akcija {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String naziv;
    private String period_vazenja;
    private String pretplatnici;

    public Akcija(){

    }
    public Akcija(Integer id, String naziv,String period_vazenja, String pretplatnici) {
        this.id = id;
        this.naziv = naziv;
        this.period_vazenja = period_vazenja;
        this.pretplatnici = pretplatnici;
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

    public String getPeriod_vazenja() {
        return period_vazenja;
    }

    public void setPeriod_vazenja(String period_vazenja) {
        this.period_vazenja = period_vazenja;
    }

    public String getPretplatnici() {
        return pretplatnici;
    }

    public void setPretplatnici(String pretplatnici) {
        this.pretplatnici = pretplatnici;
    }
}
