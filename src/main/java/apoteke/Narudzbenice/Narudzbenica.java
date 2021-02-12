package apoteke.Narudzbenice;

import apoteke.Lek.Lek;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Narudzbenica")
public class Narudzbenica {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @OneToOne
    private Lek lek;
    private int kolicina;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime datum_vreme;
    private boolean prihvacena;
    private int ukupna_cena;


    public Narudzbenica(){


    }

    public Narudzbenica(Integer id,Lek lek, int kolicina, LocalDateTime datum_vreme, boolean prihvacena,int ukupna_cena) {
        this.id = id;
        this.lek = lek;
        this.kolicina = kolicina;
        this.datum_vreme = datum_vreme;
        this.prihvacena = false;
        this.ukupna_cena = ukupna_cena;
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

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public LocalDateTime getDatum_vreme() {
        return datum_vreme;
    }

    public void setDatum_vreme(LocalDateTime datum_vreme) {
        this.datum_vreme = datum_vreme;
    }

    public boolean isPrihvacena() {
        return prihvacena;
    }

    public void setPrihvacena(boolean prihvacena) {
        this.prihvacena = prihvacena;
    }

    public int getUkupna_cena() {
        return ukupna_cena;
    }

    public void setUkupna_cena(int ukupna_cena) {
        this.ukupna_cena = ukupna_cena;
    }
}
