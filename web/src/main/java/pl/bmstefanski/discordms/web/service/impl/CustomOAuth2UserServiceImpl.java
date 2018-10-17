package pl.bmstefanski.discordms.web.service.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.web.client.RestOperations;
import pl.bmstefanski.discordms.web.service.CustomOAuth2UserService;

public class CustomOAuth2UserServiceImpl implements CustomOAuth2UserService {

  private final RestOperations restOperations;

  public CustomOAuth2UserServiceImpl(RestOperations restOperations) {
    this.restOperations = restOperations;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest)
      throws OAuth2AuthenticationException {
    ProviderDetails providerDetails = userRequest.getClientRegistration().getProviderDetails();
    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.set(HttpHeaders.AUTHORIZATION,
        String.format("Bearer %s", userRequest.getAccessToken().getTokenValue()));
    httpHeaders.set(HttpHeaders.USER_AGENT, "DiscordMS");

    ParameterizedTypeReference<Map<String, Object>> parameterizedTypeReference =
        new ParameterizedTypeReference<Map<String, Object>>() {};

    ResponseEntity<Map<String, Object>> responseEntity = this.restOperations.exchange(
        providerDetails.getUserInfoEndpoint().getUri(),
        HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        parameterizedTypeReference
    );

    Map<String, Object> userAttributes = responseEntity.getBody();
    Set<GrantedAuthority> authorities = Collections
        .singleton(new OAuth2UserAuthority(userAttributes));

    return new DefaultOAuth2User(authorities, userAttributes, providerDetails.getUserInfoEndpoint()
        .getUserNameAttributeName());
  }

}