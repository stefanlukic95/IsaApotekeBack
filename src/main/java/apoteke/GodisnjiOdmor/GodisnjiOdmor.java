package apoteke.GodisnjiOdmor;


import apoteke.Korisnik.Korisnik;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "GodisnjiOdmor")
public class GodisnjiOdmor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String korisnik;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime termin_od;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime termin_do;
    private boolean odobren;


    public GodisnjiOdmor(){

    }


    public GodisnjiOdmor(Integer id, String korisnik, LocalDateTime termin_od, LocalDateTime termin_do,boolean odobren) {
        this.id = id;
        this.korisnik = korisnik;
        this.termin_od = termin_od;
        this.termin_do = termin_do;
        this.odobren = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public LocalDateTime getTermin_od() {
        return termin_od;
    }

    public void setTermin_od(LocalDateTime termin_od) {
        this.termin_od = termin_od;
    }

    public LocalDateTime getTermin_do() {
        return termin_do;
    }

    public void setTermin_do(LocalDateTime termin_do) {
        this.termin_do = termin_do;
    }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }
}
