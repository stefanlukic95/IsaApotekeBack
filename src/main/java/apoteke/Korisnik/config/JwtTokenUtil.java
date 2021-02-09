package apoteke.Korisnik.config;

import apoteke.Korisnik.Korisnik;
import apoteke.Korisnik.KorisnikService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import static apoteke.Korisnik.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static apoteke.Korisnik.Constants.SIGNING_KEY;


@Component
public class JwtTokenUtil {
    @Autowired
    private KorisnikService korisnikService;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Korisnik user) {
        return doGenerateToken(user.getEmail());
    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Korisnik k = korisnikService.findByEmail(subject);
        for(String r: k.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r));
        }
        claims.put("scopes", authorities);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("ISA2021")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token) && userDetails.isEnabled());
    }
}