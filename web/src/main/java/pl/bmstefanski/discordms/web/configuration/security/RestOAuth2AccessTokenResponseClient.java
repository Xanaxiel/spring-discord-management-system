package pl.bmstefanski.discordms.web.configuration.security;

import java.util.Objects;
import java.util.Set;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import pl.bmstefanski.discordms.web.request.AccessTokenRequest;

public class RestOAuth2AccessTokenResponseClient implements
    OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

  private final RestOperations restOperations;

  RestOAuth2AccessTokenResponseClient(RestOperations restOperations) {
    this.restOperations = restOperations;
  }

  @Override
  public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest codeGrantRequest)
      throws OAuth2AuthenticationException {

    ClientRegistration clientRegistration = codeGrantRequest.getClientRegistration();
    OAuth2AuthorizationExchange authorizationExchange = codeGrantRequest.getAuthorizationExchange();

    MultiValueMap<String, String> tokenRequest = new LinkedMultiValueMap<>();
    tokenRequest.add("client_id", clientRegistration.getClientId());
    tokenRequest.add("client_secret", clientRegistration.getClientSecret());
    tokenRequest.add("grant_type", clientRegistration.getAuthorizationGrantType().getValue());
    tokenRequest.add("code", authorizationExchange.getAuthorizationResponse().getCode());
    tokenRequest.add("redirect_uri", authorizationExchange.getAuthorizationRequest().getRedirectUri());
    tokenRequest.add("scope", String.join(" ", codeGrantRequest.getClientRegistration().getScopes()));

    AccessTokenResponse accessTokenResponse = new AccessTokenRequest(this.restOperations, tokenRequest).submitRequest();
    Set<String> scopes = Objects.requireNonNull(accessTokenResponse).getScopes().isEmpty()
        ? codeGrantRequest.getAuthorizationExchange().getAuthorizationRequest()
        .getScopes() : accessTokenResponse.getScopes();

    return OAuth2AccessTokenResponse.withToken(accessTokenResponse.getAccessToken())
        .tokenType(accessTokenResponse.getTokenType())
        .expiresIn(accessTokenResponse.getExpiresIn())
        .scopes(scopes)
        .build();
  }

}
