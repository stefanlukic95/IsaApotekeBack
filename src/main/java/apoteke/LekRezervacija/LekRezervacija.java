package apoteke.LekRezervacija;


import apoteke.Korisnik.Korisnik;
import apoteke.Lek.Lek;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LekRezervacija")
public class LekRezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Lek lek;
    private String pacijent;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime datum_vreme;


    public LekRezervacija(){

    }

    public LekRezervacija(Integer id, Lek lek, String pacijent, LocalDateTime datum_vreme) {
        this.id = id;
        this.lek = lek;
        this.pacijent = pacijent;
        this.datum_vreme = datum_vreme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }

    public LocalDateTime getDatum_vreme() {
        return datum_vreme;
    }

    public void setDatum_vreme(LocalDateTime datum_vreme) {
        this.datum_vreme = datum_vreme;
    }

    public String getPacijent() {
        return pacijent;
    }

    public void setPacijent(String pacijent) {
        this.pacijent = pacijent;
    }
}
