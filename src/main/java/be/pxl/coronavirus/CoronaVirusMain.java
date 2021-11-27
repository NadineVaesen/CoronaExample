package be.pxl.coronavirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableAspectJAutoProxy
public class CoronaVirusMain extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(CoronaVirusMain.class, args);
    }

    @Override
    @Secured({"ROLE_DOCTOR","ROLE_PATIENT"})
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .cors()
                .and()
                //.csrf().disable()   //-> use @CrossOrigin on endpoint
                .formLogin().disable()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("doctor").password("{noop}password").roles("DOCTOR");
        auth.inMemoryAuthentication().withUser("patient").password("{noop}password").roles("PATIENT");
    }

}
