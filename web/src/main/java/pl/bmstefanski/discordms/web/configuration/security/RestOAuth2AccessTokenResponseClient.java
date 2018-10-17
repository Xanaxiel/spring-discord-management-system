package pl.bmstefanski.discordms.web.configuration.security;

import java.util.Objects;
import java.util.Set;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

public class RestOAuth2AccessTokenResponseClient implements
    OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

  private final RestOperations restOperations;

  RestOAuth2AccessTokenResponseClient(RestOperations restOperations) {
    this.restOperations = restOperations;
  }

  @Override
  public OAuth2AccessTokenResponse getTokenResponse(
      OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest)
      throws OAuth2AuthenticationException {
    ClientRegistration clientRegistration = authorizationCodeGrantRequest.getClientRegistration();

    MultiValueMap<String, String> tokenRequest = new LinkedMultiValueMap<>();
    tokenRequest.add("client_id", clientRegistration.getClientId());
    tokenRequest.add("client_secret", clientRegistration.getClientSecret());
    tokenRequest.add("grant_type", clientRegistration.getAuthorizationGrantType().getValue());
    tokenRequest.add("code", authorizationCodeGrantRequest.getAuthorizationExchange()
        .getAuthorizationResponse().getCode());
    tokenRequest.add("redirect_uri", authorizationCodeGrantRequest.getAuthorizationExchange()
        .getAuthorizationRequest().getRedirectUri());
    tokenRequest.add("scope",
        String.join(" ", authorizationCodeGrantRequest.getClientRegistration().getScopes()));

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.add(HttpHeaders.USER_AGENT, "DiscordMS");

    ResponseEntity<AccessTokenResponse> responseEntity = restOperations
        .exchange(clientRegistration.getProviderDetails().getTokenUri(), HttpMethod.POST,
            new HttpEntity<>(tokenRequest, httpHeaders), AccessTokenResponse.class);

    AccessTokenResponse accessTokenResponse = responseEntity.getBody();
    Set<String> scopes = Objects.requireNonNull(accessTokenResponse).getScopes().isEmpty()
        ? authorizationCodeGrantRequest.getAuthorizationExchange().getAuthorizationRequest()
        .getScopes() : accessTokenResponse.getScopes();

    return OAuth2AccessTokenResponse.withToken(accessTokenResponse.getAccessToken())
        .tokenType(accessTokenResponse.getTokenType())
        .expiresIn(accessTokenResponse.getExpiresIn())
        .scopes(scopes)
        .build();
  }

}
