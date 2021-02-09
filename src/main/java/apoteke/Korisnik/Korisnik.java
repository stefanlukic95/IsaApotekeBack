package apoteke.Korisnik;
import apoteke.Lek.Lek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Korisnik")
public class Korisnik {


    public enum RoleEnum  {PACIJENT,FARMACEUT,DERMATOLOG,ADMINAPOTEKE,ADMINISTRATOR,DOVABLJAC}
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "email")
    private String email;
    @Column(name ="password")
    private String password;
    @Column(name = "roleEnum")
    private RoleEnum roleEnum;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "roles")
    private List<String> roles;
    @JsonIgnore
    @Column(name = "enabled")
    private boolean enabled;
    @JsonIgnore
    @Column(name = "confirmationToken")
    private String confirmationToken;
    @Column(name = "passwordChanged")
    private boolean passwordChanged;
    @ElementCollection
    @OneToMany
    private List<Lek> lek_alergija;
    @OneToMany
    private List<Lek> rezervisani_lekovi;


    public Korisnik(){

    }

    public Korisnik(Integer id, String ime, String prezime, String email, String password, RoleEnum roleEnum, List<String> roles, boolean enabled, String confirmationToken, boolean passwordChanged, List<Lek> lek_alergija, List<Lek> rezervisani_lekovi) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.roleEnum =  roleEnum;
        this.roles = roles;
        this.enabled = enabled;
        this.lek_alergija = lek_alergija;
        this.rezervisani_lekovi = rezervisani_lekovi;
        this.confirmationToken ="";
        this.passwordChanged = passwordChanged;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public List<Lek> getLek_alergija() {
        return lek_alergija;
    }

    public void setLek_alergija(List<Lek> lek_alergija) {
        this.lek_alergija = lek_alergija;
    }

    public List<Lek> getRezervisani_lekovi() {
        return rezervisani_lekovi;
    }

    public void setRezervisani_lekovi(List<Lek> rezervisani_lekovi) {
        this.rezervisani_lekovi = rezervisani_lekovi;
    }
}
