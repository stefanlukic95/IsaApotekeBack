package apoteke.Rezervacija;

import apoteke.Korisnik.Korisnik;
import apoteke.Pregled.Pregled;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "Rezervacija")
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    @OneToOne
    private Pregled pregled;

    private String pacijent;

    public Rezervacija(){

    }

    public Rezervacija(Integer id, Pregled pregled, String pacijent) {
        this.id = id;
        this.pregled = pregled;
        this.pacijent = pacijent;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Pregled getPregled() {
        return pregled;
    }

    public void setPregled(Pregled pregled) {
        this.pregled = pregled;
    }

    public String getPacijent() {
        return pacijent;
    }

    public void setPacijent(String pacijent) {
        this.pacijent = pacijent;
    }
}
