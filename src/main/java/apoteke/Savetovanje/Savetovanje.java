package apoteke.Savetovanje;


import apoteke.Korisnik.Korisnik;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="Savetovanje")
public class Savetovanje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Korisnik farmaceut;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime datum_vreme;
    private boolean zakazano;
    private String pacijent;



    public Savetovanje(){

    }

    public Savetovanje(Integer id, Korisnik farmaceut, LocalDateTime datum_vreme, boolean zakazano, String pacijent) {
        this.id = id;
        this.farmaceut = farmaceut;
        this.datum_vreme = datum_vreme;
        this.zakazano = true;
        this.pacijent = pacijent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Korisnik getFarmaceut() {
        return farmaceut;
    }

    public void setFarmaceut(Korisnik farmaceut) {
        this.farmaceut = farmaceut;
    }

    public LocalDateTime getDatum_vreme() {
        return datum_vreme;
    }

    public void setDatum_vreme(LocalDateTime datum_vreme) {
        this.datum_vreme = datum_vreme;
    }

    public boolean isZakazano() {
        return zakazano;
    }

    public void setZakazano(boolean zakazano) {
        this.zakazano = zakazano;
    }

    public String getPacijent() {
        return pacijent;
    }

    public void setPacijent(String pacijent) {
        this.pacijent = pacijent;
    }
}
