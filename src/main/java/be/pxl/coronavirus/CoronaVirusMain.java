package be.pxl.coronavirus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableAspectJAutoProxy
public class CoronaVirusMain extends WebSecurityConfigurerAdapter {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(CoronaVirusMain.class, args);
    }

    @Override
    @Secured("ROLE_DOCTOR")
    protected void configure(HttpSecurity http) throws Exception {

        //disable form login
        //iedereen van buitenaf kan je api raadplegen als je de csrf weglaat niet secured dan kan je gewoon put requests

        //designpattern hier is builder
        http
                .cors()
                .and()
                .csrf().disable()   //-> use @CrossOrigin on endpoint
                .formLogin().disable()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("doctor").password(passwordEncoder().encode("password")).roles("DOCTOR");
        auth.inMemoryAuthentication().withUser("patient").password(passwordEncoder().encode("password")).roles("PATIENT");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
