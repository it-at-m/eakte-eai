/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.auth;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DmsAuthConfiguration {
  private static final String ACTUATOR_HEALTH_PATH = "/actuator/health/**";

  @Bean
  public DmsAuthenticationRouteBuilder authenticationRouteBuilder() {
    return new DmsAuthenticationRouteBuilder();
  }

  /**
   * Erstellen des Passwortencoder. Der Passwortencoder dient dazu, dass in der EAI keine Passwörter
   * von Benutzern im Klartext liegen müssen. Die Passwörter der Anfrage werden mit dem lokal
   * gespeicherten Hash verglichen.
   *
   * @param strength Stärke des Hashings des {@link BCryptPasswordEncoder}
   * @return Ersteller Passwortencoder
   */
  @Bean
  public PasswordEncoder passwordEncoder(
      final @Value("${dms.auth.bcrypt.strength:}") Integer strength) {
    if (strength == null) {
      return new BCryptPasswordEncoder();
    } else {
      return new BCryptPasswordEncoder(strength);
    }
  }

  @Bean
  public DaoAuthenticationProvider authProvider(
      final UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);

    return provider;
  }

  @Bean()
  public SecurityFilterChain filterChain(
      HttpSecurity http,
      final @Value("${application.security.disabled:false}") boolean securityDisabled,
      final @Value("${de.muenchen.rest.api.path}") String apiPath)
      throws Exception {
    if (securityDisabled) {
      http.httpBasic(AbstractHttpConfigurer::disable)
          .formLogin(AbstractHttpConfigurer::disable)
          .csrf(AbstractHttpConfigurer::disable)
          // Permit all requests without authentication
          .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
    } else {
      http.securityMatcher("/swagger-ui**")
          .authorizeHttpRequests(
              rmr -> rmr.requestMatchers(antMatcher("/swagger-ui**")).permitAll())
          .securityMatcher("/actuator/info/**")
          .authorizeHttpRequests(
              rmr ->
                  rmr.requestMatchers(antMatcher(HttpMethod.GET, ACTUATOR_HEALTH_PATH)).permitAll())
          .securityMatcher(ACTUATOR_HEALTH_PATH)
          .authorizeHttpRequests(
              rmr ->
                  rmr.requestMatchers(antMatcher(HttpMethod.GET, ACTUATOR_HEALTH_PATH)).permitAll())
          .securityMatcher(apiPath + "/**")
          .authorizeHttpRequests(
              rmr ->
                  rmr.requestMatchers(antMatcher(HttpMethod.GET, apiPath + "/**"))
                      .hasAuthority("READ")
                      .requestMatchers(antMatcher(HttpMethod.PUT, apiPath + "/**"))
                      .hasAuthority("WRITE")
                      .requestMatchers(antMatcher(HttpMethod.POST, apiPath + "/**"))
                      .hasAuthority("WRITE"))
          .securityMatcher("/error**")
          .authorizeHttpRequests(
              rmr -> rmr.requestMatchers(antMatcher(HttpMethod.GET, "/error**")).permitAll())
          .httpBasic(Customizer.withDefaults());
    }
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(ConfiguredWebSecProperties properties) {
    return new DmsUserDetailsService(properties);
  }

  @Bean
  public DmsAuthorizationProcessorConfig dmsAuthInformation(
      final @Value("${dms.eai.service.username}") String eaiServiceUser,
      final @Value("${dms.eai.service.password}") String eaiServicePassword) {
    final DmsAuthorizationProcessorConfig config = new DmsAuthorizationProcessorConfig();
    config.setEaiServiceUser(eaiServiceUser);
    config.setEaiServicePassword(eaiServicePassword);
    return config;
  }

  @Bean
  public DmsAuthorizationProcessor authorizationProcessor(
      UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder,
      DmsAuthorizationProcessorConfig info,
      UserService userService) {
    return new DmsAuthorizationProcessor(userDetailsService, passwordEncoder, info, userService);
  }
}
