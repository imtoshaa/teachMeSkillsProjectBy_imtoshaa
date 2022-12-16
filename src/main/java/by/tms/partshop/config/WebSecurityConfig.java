package by.tms.partshop.config;

import static by.tms.partshop.util.constants.RolesConstants.ROLE_ADMIN;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = "by")
@Configuration
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeRequests((auth) -> {
              try {
                auth
                    .antMatchers("/registration").not().fullyAuthenticated()
                    .antMatchers("/resources/**", "/").permitAll()
                    .antMatchers("/cart/confirmOrder", "/mypage").authenticated()
                    .antMatchers("/admins/**").hasAuthority(ROLE_ADMIN)
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .permitAll()
                    .defaultSuccessUrl("/home")
                    .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .permitAll();
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            }
        );
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }
}
