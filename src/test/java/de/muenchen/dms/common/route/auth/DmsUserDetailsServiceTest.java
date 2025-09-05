package de.muenchen.dms.common.route.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class DmsUserDetailsServiceTest {

  private DmsUserDetailsService service;
  private ConfiguredWebSecProperties props;

  private static final User EXISTING_USER = new User("Hans", "1234", List.of("SUPERUSER"), null);

  private static final Map<String, Set<String>> roles =
      Map.of(
          "SUPERUSER", Set.of("WRITE", "READ"),
          "MEGAADMIN", Set.of("WRITE", "READ", "DELETE"));

  @BeforeEach
  void setUp() {
    props = new ConfiguredWebSecProperties();
    service = new DmsUserDetailsService(props);
  }

  @Test
  void loadUserByUsernameWithEmptyUsers() {
    props.getUsers().clear();
    final String username = EXISTING_USER.getUsername();
    assertThrows(
        UsernameNotFoundException.class,
        () -> {
          service.loadUserByUsername(username);
        },
        "Es wird keine UsernameNotFoundException geworfen, obwohl keine Nutzer vorhanden sind");
  }

  @Test
  void loadUserByUsernameWithEmptyRoles() {
    props.getUsers().add(EXISTING_USER);
    props.setRoles(null);
    final String username = EXISTING_USER.getUsername();

    assertThrows(
        UsernameNotFoundException.class,
        () -> {
          service.loadUserByUsername(username);
        },
        "Es wird keine UsernameNotFoundException geworfen, obwohl keine Rollen vorhanden sind");
  }

  @Test
  void loadUserByUsernanmeNotExisting() {
    props.getUsers().add(EXISTING_USER);
    props.getRoles().putAll(roles);

    assertThrows(
        UsernameNotFoundException.class,
        () -> {
          service.loadUserByUsername("Hans Nichtexistend");
        },
        "Es wird keine UsernameNotFoundException geworfen, obwohl der Nutzername nicht vorhanden ist");
  }

  @Test
  void loadUserByUsernameWithoutPassword() {
    EXISTING_USER.setPassword(null);
    props.getUsers().add(EXISTING_USER);
    props.getRoles().putAll(roles);
    final String username = EXISTING_USER.getUsername();

    assertThrows(
        UsernameNotFoundException.class,
        () -> {
          service.loadUserByUsername(username);
        },
        "Es wird keine UsernameNotFoundException geworfen, obwohl der Nutzer kein Passwort gesetzt hat");
  }

  @Test
  void loadUserByUsernanmeWithoutRoles() {
    EXISTING_USER.setRoles(List.of());
    props.getUsers().add(EXISTING_USER);
    props.getRoles().putAll(roles);
    final String username = EXISTING_USER.getUsername();

    assertThrows(
        UsernameNotFoundException.class,
        () -> {
          service.loadUserByUsername(username);
        },
        "Es wird keine UsernameNotFoundException geworfen, obwohl der Nutzer kein Passwort gesetzt hat");
  }

  @Test
  void loadUserByUsernanme() {
    props.getUsers().add(EXISTING_USER);
    props.getRoles().putAll(roles);

    final UserDetails userDetails = service.loadUserByUsername(EXISTING_USER.getUsername());
    assertEquals(userDetails.getPassword(), EXISTING_USER.getPassword());
    assertEquals(userDetails.getUsername(), EXISTING_USER.getUsername());
    assertEquals(2, userDetails.getAuthorities().size());
    assertEquals(
        Boolean.TRUE,
        userDetails.getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("WRITE")));
    assertEquals(
        Boolean.TRUE,
        userDetails.getAuthorities().stream()
            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("READ")));
  }
}
