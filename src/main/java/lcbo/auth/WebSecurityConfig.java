package lcbo.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Customization of {@link WebSecurityConfigurerAdapter} for authenticating users with MongoDB.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private UserRepository userRepository;

    private UserDetailsManager userDetailsManager;
    private UserDetailsManagerConfigurer userDetailsManagerConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/signup").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        userDetailsManagerConfigurer = new MongoDBUserDetailsManagerConfigurer<>(userDetailsManager);
        auth.userDetailsService(userDetailsManager);
        auth.apply(userDetailsManagerConfigurer);
    }

    @Bean(name = "userDetailsManager")
    public UserDetailsManager userDetailsManager() throws Exception {
        userDetailsManager = new MongoDBUserDetailsManager(userRepository);
        return userDetailsManager;
    }

}