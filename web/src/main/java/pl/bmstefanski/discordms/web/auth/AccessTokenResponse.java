package pl.bmstefanski.discordms.web.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.thymeleaf.util.StringUtils;

public class AccessTokenResponse implements Serializable {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("token_type")
  private String tokenType;
  @JsonProperty("expires_in")
  private int expiresIn;
  @JsonProperty("refresh_token")
  private String refreshToken;
  private String scope;

  AccessTokenResponse() {}

  AccessTokenResponse(String accessToken, String tokenType, int expiresIn,
      String refreshToken, String scope) {
    this.accessToken = accessToken;
    this.tokenType = tokenType;
    this.expiresIn = expiresIn;
    this.refreshToken = refreshToken;
    this.scope = scope;
  }

  String getAccessToken() {
    return this.accessToken;
  }

  String getRefreshToken() {
    return this.refreshToken;
  }

  OAuth2AccessToken.TokenType getTokenType() {
    return OAuth2AccessToken.TokenType.BEARER.getValue().equalsIgnoreCase(this.tokenType)
        ? OAuth2AccessToken.TokenType.BEARER : null;
  }

  int getExpiresIn() {
    return this.expiresIn;
  }

  Set<String> getScopes() {
    return StringUtils.isEmpty(this.scope) ? Collections.emptySet()
        : Stream.of(this.scope.split("\\s+")).collect(Collectors.toSet());
  }

}
