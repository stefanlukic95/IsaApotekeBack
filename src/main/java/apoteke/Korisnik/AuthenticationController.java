package apoteke.Korisnik;

import apoteke.Korisnik.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private KorisnikService korisnikService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Korisnik user = korisnikService.findByEmail(loginUser.getUsername());
        if(user.isEnabled()) {
            final String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new AuthToken(token));
        } else {
            final String token = jwtTokenUtil.generateToken(null);
            return ResponseEntity.ok(new AuthToken(token));
        }
    }
}
