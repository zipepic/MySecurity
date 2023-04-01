package zipepic.project.MySecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import zipepic.project.MySecurity.repositories.PeopleRepository;
import zipepic.project.MySecurity.services.PersonDetailsService;
;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PeopleRepository peopleRepository;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/products/all").hasRole("ADMIN")
                .requestMatchers("/auth/**","/error","/products/welcome").permitAll()
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/products/welcome",true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login").and();
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/products/welcome").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/products/**").authenticated()
//                .and().formLogin().loginPage("/auth/login").permitAll()
//                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/products/welcome",true)
//                .failureUrl("/auth/login?error")
//                .and()
//                .logout().permitAll()
//                .and();
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        UserDetails admin = User.withUsername("zip")
//                .password("123")
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("zip2")
//                .password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new PersonDetailsService(peopleRepository);
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}
