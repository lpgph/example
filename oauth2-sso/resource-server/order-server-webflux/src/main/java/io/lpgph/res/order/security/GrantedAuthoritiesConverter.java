package io.lpgph.res.order.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @see
 *     org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
 */
public class GrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  public static final Map<String, String> WELL_KNOWN_AUTHORITIES_CLAIM_NAMES =
      Map.of("scope", "SCOPE_", "scp", "SCOPE_");

  // key is ClaimName , value is prefix,not prefix set ""
  private final Map<String, String> authoritiesClaimName;

  public GrantedAuthoritiesConverter(Map<String, String> authoritiesClaimName) {
    this.authoritiesClaimName = authoritiesClaimName;
  }

  public GrantedAuthoritiesConverter() {
    this.authoritiesClaimName = WELL_KNOWN_AUTHORITIES_CLAIM_NAMES;
  }

  @Override
  public Collection<GrantedAuthority> convert(Jwt jwt) {
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    for (Map.Entry<String, String> claimNameEntry : authoritiesClaimName.entrySet()) {
      for (String authority : getAuthorities(claimNameEntry.getKey(), jwt)) {
        grantedAuthorities.add(
            new SimpleGrantedAuthority(
                claimNameEntry.getValue() == null ? "" : claimNameEntry.getValue() + authority));
      }
    }
    return grantedAuthorities;
  }

  @SuppressWarnings("unchecked")
  private Collection<String> getAuthorities(String claimName, Jwt jwt) {
    if (claimName == null) {
      return Collections.emptyList();
    }

    Object authorities = jwt.getClaim(claimName);
    if (authorities instanceof String) {
      if (StringUtils.hasText((String) authorities)) {
        return Arrays.asList(((String) authorities).split(" "));
      } else {
        return Collections.emptyList();
      }
    } else if (authorities instanceof Collection) {
      return (Collection<String>) authorities;
    }
    return Collections.emptyList();
  }
}
