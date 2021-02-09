package apoteke.Pregled;


import apoteke.Korisnik.Korisnik;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="Pregled")
public class Pregled {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    @OneToOne
    private Korisnik dermatolog;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime datum_vreme;
    private int cena;
    private boolean rezervisan;

    public Pregled(){

    }

    public Pregled(Integer id, Korisnik dermatolog, LocalDateTime datum_vreme, int cena,boolean rezervisan) {
        this.id = id;
        this.dermatolog = dermatolog;
        this.datum_vreme = datum_vreme;
        this.cena = cena;
        this.rezervisan = rezervisan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Korisnik getDermatolog() {
        return dermatolog;
    }

    public void setDermatolog(Korisnik dermatolog) {
        this.dermatolog = dermatolog;
    }

    public LocalDateTime getDatum_vreme() {
        return datum_vreme;
    }

    public void setDatum_vreme(LocalDateTime datum_vreme) {
        this.datum_vreme = datum_vreme;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public boolean isRezervisan() {
        return rezervisan;
    }

    public void setRezervisan(boolean rezervisan) {
        this.rezervisan = rezervisan;
    }
}
