package com.proyectofinal.Proyectofinal.security;

import com.proyectofinal.Proyectofinal.repository.UserRepository;
import com.proyectofinal.Proyectofinal.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/templates/**","/static/**","/assets/**", "/css/**", "/jpeg/**", "/png/**", "/mp4/**"};

    @Autowired
    private ClienteServicio clienteServicio;


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(clienteServicio);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());

        auth.inMemoryAuthentication()
                .withUser("techoro1230@gmail.com")
                .password(passwordEncoder().encode("1230"))
                .roles("admin");

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers(
                      "/registrate**",
                                  "/static/*+",
                                    "/css/**",
                                   "/img/**",
                        "/jpg/**",
                        "/png/**",
                        "/svg/**",
                        "/jpeg/**",
                                    "/mp4/**",
                                    "/index/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/farmadoc")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}
