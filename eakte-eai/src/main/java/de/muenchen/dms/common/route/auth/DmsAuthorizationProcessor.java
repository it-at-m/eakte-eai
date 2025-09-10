/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.common.route.auth;

import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * DmsAuthorizationProcessor. Überprüft ob übergebener Benutzer aus dem Authorization Header im
 * UserDetailsService gespeichert ist und ob dessen Passwort übereinstimmt --------------- Input:
 * --------------- Exchange mit Headern: -HEADER_PARAM_NUTZER -HEADER_PARAM_ANWENDUNG -AUTHORIZATION
 * ------------------ Output: ----------------- Im Erfolgsfall Exchange mit Properties: -
 * PROPERTY_NUTZER aus dem Header, wird genutzt als Propertie in den SOAP aufrufen -
 * PROPERTY_ANWENDUNG aus dem Header, wird genutzt als Propertie in den SOAP aufrufen -
 * PROPERTY_AUTH_USER Nutzername für den Nutzer auf DMS Seite - PROPERTY_AUTH_PASS Passwort für das
 * Passwort des Nutzers auf DMS Seite oder BadCredentialsException falls keine AUTHORIZATION oder
 * Nutzername oder Passwort falsch sind
 */
@Slf4j
public class DmsAuthorizationProcessor implements Processor {

  @Value("${dms.eai.service.url}")
  private String eaiServiceUrl;

  private static final String BASIC = "Basic";
  public static final String INVALID_USERNAME_OR_PASSWORD = "Invalid username or password";

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final DmsAuthorizationProcessorConfig config;
  private final UserService userService;

  public DmsAuthorizationProcessor(
      UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder,
      DmsAuthorizationProcessorConfig info,
      UserService userService) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
    this.config = info;
    this.userService = userService;
  }

  @Override
  public void process(final Exchange exchange) throws Exception {
    setAuthorization(exchange);
    final String nutzer =
        exchange.getMessage().getHeader(RouteConstants.HEADER_PARAM_USERLOGIN, String.class);
    final String anwendung =
        exchange.getMessage().getHeader(RouteConstants.HEADER_PARAM_BUSINESSAPP, String.class);
    final String organisationseinheit =
        exchange.getMessage().getHeader(RouteConstants.HEADER_PARAM_JOBOE, String.class);
    final String rolle =
        exchange.getMessage().getHeader(RouteConstants.HEADER_PARAM_JOBPOSITION, String.class);

    final String authUserName = getAuthUsernameFromExchangeOrElseNull(exchange);
    String serviceUrl = userService.getCustomServiceUrlByUsernameOrNull(authUserName);
    if (serviceUrl == null) {
      serviceUrl = eaiServiceUrl;
    }

    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, nutzer);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, anwendung);
    exchange.setProperty(QueryProperties.PROPERTY_AUTH_USER, this.config.getEaiServiceUser());
    exchange.setProperty(QueryProperties.PROPERTY_AUTH_PASS, this.config.getEaiServicePassword());
    exchange.setProperty(QueryProperties.PROPERTY_ORGANISATIONSEINHEIT, organisationseinheit);
    exchange.setProperty(QueryProperties.PROPERTY_ROLLE, rolle);
    exchange.setProperty(QueryProperties.PROPERTY_ADDRESS, serviceUrl);
  }

  private void setAuthorization(final Exchange exchange) {
    final String authorization =
        exchange.getIn().getHeader(HttpHeaders.AUTHORIZATION, String.class);
    if (authorization != null && !authorization.isEmpty() && authorization.startsWith(BASIC)) {
      final String base64Credentials = authorization.substring(BASIC.length()).trim();
      final byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
      final String credentials = new String(credDecoded, StandardCharsets.UTF_8);
      // credentials = username:password
      final String[] values = credentials.split(":", 2);
      final String username = values[0];
      final String password = values[1];

      final UserDetails userDetails;
      try {
        userDetails = userDetailsService.loadUserByUsername(username);
      } catch (UsernameNotFoundException e) {
        throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD, e);
      }

      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD);
      }

      log.debug("Authentification via basic auth");
      exchange.setProperty("AuthenticatedUser", username);
    } else {
      throw new BadCredentialsException(INVALID_USERNAME_OR_PASSWORD);
    }
  }

  private String getAuthUsernameFromExchangeOrElseNull(final Exchange exchange) {
    final String authorization =
        exchange.getIn().getHeader(HttpHeaders.AUTHORIZATION, String.class);
    if (authorization != null && !authorization.isEmpty() && authorization.startsWith(BASIC)) {
      final String base64Credentials = authorization.substring(BASIC.length()).trim();
      final byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
      final String credentials = new String(credDecoded, StandardCharsets.UTF_8);
      // credentials = username:password
      final String[] values = credentials.split(":", 2);
      return values[0];
    }
    return null;
  }
}
