/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.auth;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@DisplayName(
    "[Authorization] Diese Testklasse prüft die Entgegennahme von REST-Anfragen mit Autorisierung")
class DmsAuthorizationProcessorTest {

  private MockPasswordEncoder encoder;
  private DmsAuthorizationProcessor processor;
  private MockUserDetailsService userDetailsService;
  private UserService userService;

  private static final String NUTZER = "hans.wurst";
  private static final String ANWENDUNG = "Demoanwendung";
  private static final String ENCODED_PASSWORD = "drowssap";

  private static final String BASE64_AUTH = "Basic SGFuczpwYXNzd29yZA=="; // Hans:password

  private static final String DMS_USER = "DMS_USER";
  private static final String DMS_PW = "DMS_USER_PW";

  @BeforeEach
  void setUp() {

    userDetailsService =
        new MockUserDetailsService(
            true, ENCODED_PASSWORD, "Etwas ist falsch gelaufen. Nutzer nicht gefunden.");
    encoder = new MockPasswordEncoder(ENCODED_PASSWORD);
    final DmsAuthorizationProcessorConfig dmsAuthorizationProcessorConfig =
        new DmsAuthorizationProcessorConfig();
    dmsAuthorizationProcessorConfig.setEaiServiceUser(DMS_USER); // NOSONAR
    dmsAuthorizationProcessorConfig.setEaiServicePassword(DMS_PW); // NOSONAR
    userService = Mockito.mock(UserService.class);
    processor =
        new DmsAuthorizationProcessor(
            userDetailsService, encoder, dmsAuthorizationProcessorConfig, userService);
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob ein gültige Anfrage mit richtigen Authentifizierungsinformationen richtig weitergegeben wird
            und die Properties richtig weitergeleitet werden
            """)
  void testeErfolgreicheAuthentifizierung() throws Exception {
    userDetailsService.setSuccessful(true);
    Mockito.when(userService.getCustomServiceUrlByUsernameOrNull(DMS_USER)).thenReturn(null);
    final Exchange result = prozessorAusfuehren();

    Assertions.assertEquals(
        NUTZER,
        result.getProperty(QueryProperties.PROPERTY_NUTZER),
        "Die Nutzer Property ist nicht richtig gesetzt.");
    Assertions.assertEquals(
        ANWENDUNG,
        result.getProperty(QueryProperties.PROPERTY_ANWENDUNG),
        "Die Anwendung Property ist nicht richtig gesetzt.");
    Assertions.assertEquals(
        DMS_USER,
        result.getProperty(QueryProperties.PROPERTY_AUTH_USER),
        "Die Auth_User Property ist nicht gesetzt.");
    Assertions.assertEquals(
        DMS_PW,
        result.getProperty(QueryProperties.PROPERTY_AUTH_PASS),
        "Die Auth_Pass Property ist nicht gesetzt.");
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob eine Exception geworfen wird, falls keine Authentifizierungsinformationen übergeben werden
            """)
  void testeAuthentifizierungOhneHeader() {
    userDetailsService.setSuccessful(true);
    assertThrows(
        BadCredentialsException.class,
        () -> {
          prozessorAusfuehren(null);
        },
        "Es wird keine BadCredentialsException geworfen, obwohl der Authorization Header fehlt.");
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob eine Exception geworfen wird, falls der Nutzername nicht gefunden wird.
            """)
  void testeAuthentifizierungOhneNutzer() {
    userDetailsService.setSuccessful(false);
    assertThrows(
        BadCredentialsException.class,
        () -> {
          prozessorAusfuehren();
        },
        "Es wird keine BadCredentialsException geworfen, obwohl der Nutzername nicht im UserDetailsService vorhanden ist.");
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob eine Exception geworfen wird, falls das Passwort nicht stimmt.
            """)
  void testeAuthentifizierungMitFalschenPasswort() {
    userDetailsService.setSuccessful(true);
    encoder.setEncoding("falsches Passwort"); // gib falsches Passwort zurück
    assertThrows(
        BadCredentialsException.class,
        () -> {
          prozessorAusfuehren();
        },
        "Es wird keine BadCredentialsException geworfen, obwohl das Password falsch ist (Encoder) liefert falsches Passwort zurück.");
  }

  private Exchange prozessorAusfuehren() throws Exception {
    return prozessorAusfuehren(BASE64_AUTH);
  }

  private Exchange prozessorAusfuehren(final String authorization) throws Exception {
    final CamelContext ctx = new DefaultCamelContext();
    final Exchange exchange = new DefaultExchange(ctx);
    final Message nachricht = exchange.getIn();

    nachricht.removeHeaders("*");
    nachricht.setHeader(RouteConstants.HEADER_PARAM_USERLOGIN, NUTZER);
    nachricht.setHeader(RouteConstants.HEADER_PARAM_BUSINESSAPP, ANWENDUNG);
    if (authorization != null && !authorization.isEmpty())
      nachricht.setHeader(AUTHORIZATION, authorization);
    processor.process(exchange);
    return exchange;
  }

  /**
   * Mock Klasse für einen UserDetailsService Gibt entweder einen festen Nutzer zurück oder wirft
   * eine Exception
   */
  private static class MockUserDetailsService implements UserDetailsService {
    private boolean successful;
    private final String password;

    private final String exceptionMessage;

    private MockUserDetailsService(boolean successful, String password, String exceptionMessage) {
      this.successful = successful;
      this.password = password;
      this.exceptionMessage = exceptionMessage;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      if (successful) {
        final List<SimpleGrantedAuthority> authorities =
            Stream.of("cxfPost").map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
            username, password, authorities);
      }
      throw new UsernameNotFoundException(exceptionMessage);
    }

    public void setSuccessful(boolean successful) {
      this.successful = successful;
    }
  }

  /**
   * Mock Implementierung eines PasswordEncoders
   *
   * <p>Encoded in einen fixen testString
   */
  private static class MockPasswordEncoder implements PasswordEncoder {
    private String encoding;

    private MockPasswordEncoder(String encoding) {
      this.encoding = encoding;
    }

    @Override
    public String encode(CharSequence rawPassword) {
      return encoding;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
      return encoding.equals(encodedPassword);
    }

    public String getEncoding() {
      return encoding;
    }

    public void setEncoding(String encoding) {
      this.encoding = encoding;
    }
  }
}
