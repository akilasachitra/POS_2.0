package testAPI.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import testAPI.api.user.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@ComponentScan(basePackages = "testAPI.api")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/admin")
//                .hasRole("ADMIN").antMatchers("/user").hasRole("DOCTOR")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin();
//    }

 @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
//                .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//                .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//                .antMatchers("/delete/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403");
//    }




//    //Enable jdbc authentication
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService)
//                .passwordEncoder(passwordEncoder())// ????????????? role
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .jdbcAuthentication()
//                .dataSource(dataSource);
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from users where username=?")
//                .authoritiesByUsernameQuery("select username, role from users where username=?");
//    }
//
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("1987"))
                .roles("USER").and().withUser("admin").password(passwordEncoder().encode("1987")).roles("ADMIN", "USER");
    }


//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user1").password(passwordEncoder().encode("1987")).roles("USER").build();
//        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("1987")).roles("ADMIN", "USER").build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
