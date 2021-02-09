package apoteke.Lek;

import apoteke.Apoteka.Apoteka;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lek")
public class Lek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String naziv;
    private int cena;
    private boolean rezervisan;
    @ElementCollection
    @ManyToOne
    private Apoteka apoteka;





    public Lek(){

    }

    public Lek(Integer id, String naziv,Apoteka apoteka, int cena, boolean rezervisan) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.apoteka = apoteka;
        this.rezervisan= rezervisan;
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

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public Apoteka getApoteka() {
        return apoteka;
    }

    public void setApoteka(Apoteka apoteka) {
        this.apoteka = apoteka;
    }

    public boolean isRezervisan() {
        return rezervisan;
    }

    public void setRezervisan(boolean rezervisan) {
        this.rezervisan = rezervisan;
    }
}
