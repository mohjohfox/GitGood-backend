package dhbw.karlsruhe.gitgood.security;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

  /*
   * Definiert, welche URL Pfade gesichert werden sollen und welche nicht
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/test", "/", "/game/start", "/allGamemodes", "/gamemode/*", "/allGames")
        .permitAll().and()
        .csrf()
        .ignoringAntMatchers("/login", "/game/start", "/allGamemodes", "/gamemode/*", "/allGames");
    http.exceptionHandling().accessDeniedPage("/access-denied");
    http.cors();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.addAllowedMethod(HttpMethod.GET);
    configuration.addAllowedMethod(HttpMethod.POST);
    configuration.addAllowedMethod(HttpMethod.PUT);
    configuration.addAllowedMethod(HttpMethod.DELETE);
    configuration.setAllowCredentials(true);
    configuration.addAllowedHeader("Authorization");
    configuration.addAllowedHeader("Cache-Control");
    configuration.addAllowedHeader("Content-Type");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }


}
