package apoteke.Korisnik.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "korisnikService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()

                .antMatchers("/token/*", "/").permitAll()
                .antMatchers(HttpMethod.POST,"/apoteke","/apoteka/*").hasAuthority("ADMINISRATOR")
                .antMatchers(HttpMethod.POST,"/godisnjiOdmori").hasAuthority("DERMATOLOG")
                .antMatchers(HttpMethod.POST,"/godisnjiOdmori").hasAuthority("FARMACEUT")
                .antMatchers(HttpMethod.POST, "/rezervacijaLek/*","/rezervacijaPredef/*", "/rezervacijaPredefUpdt/*","/rezervacije","/rezervacije/*","/savetovanja/apoteke/*").hasAuthority("PACIJENT")
                .antMatchers(HttpMethod.GET,"/rezervacijeLek","/rezervacijaLek/*","/godisnjiOdmori","/narudzbenice","/narudzbenica/*","/savetovanja","/savetovanje/*").hasAuthority("ADMINAPOTEKE")
                .antMatchers(HttpMethod.POST,"/apoteke","/apoteka/*","/lekovi/apoteke/*","/pregledi/apoteke/*","/akcije/apoteke/*","/narudzbenice/apoteke/*").hasAuthority("ADMINAPOTEKE")
                .antMatchers(HttpMethod.PUT,"/apoteka/*").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT,"/lek/*","/akcija/*","/godisnjiOdmor/*","/narudzbenica/*","/savetovanje/*").hasAuthority("ADMINAPOTEKE")
                .antMatchers(HttpMethod.GET,"/akcije","/akcija/*","/apoteke","/lekovi","/lek/*","/lekovi/search","/farmaceuti","/dermatolozi","/akcije","/korisnici","/apoteke/search","/apoteke/searchAdresa","/pregledi","/pregled/*").permitAll()
                .antMatchers(HttpMethod.DELETE,"/apoteka/*").hasAuthority("ADMINISRATOR")
                .antMatchers(HttpMethod.DELETE,"/lek/*","/rezervacijaLek/*","/rezervacije/*","/pregledi/*","/akcija/*","/godisnjiOdmor/*","/narudzbenica/*","/savetovanje/*").hasAuthority("ADMINAPOTEKE")
                .antMatchers(HttpMethod.DELETE,"/rezervacije/*").hasAuthority("PACIJENT")

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/*/*","/user/*/","/generate-token","/login","/user/*","/user","/register","/confirm","/korisnik/*");

    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
