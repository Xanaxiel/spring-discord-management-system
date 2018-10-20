package pl.bmstefanski.discordms.web.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
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
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.web.client.RestOperations;
import pl.bmstefanski.discordms.web.entity.user.UserBuilder;
import pl.bmstefanski.discordms.web.entity.user.UserEntityImpl;
import pl.bmstefanski.discordms.web.exception.InvalidUserAttributes;
import pl.bmstefanski.discordms.web.repository.UserRepository;
import pl.bmstefanski.discordms.web.service.CustomOAuth2UserService;

public class CustomOAuth2UserServiceImpl implements CustomOAuth2UserService {

  private final RestOperations restOperations;
  private final UserRepository userRepository;

  public CustomOAuth2UserServiceImpl(RestOperations restOperations, UserRepository userRepository) {
    this.restOperations = restOperations;
    this.userRepository = userRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    ProviderDetails providerDetails = userRequest.getClientRegistration().getProviderDetails();
    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.set(HttpHeaders.AUTHORIZATION,
        String.format("Bearer %s", userRequest.getAccessToken().getTokenValue()));
    httpHeaders.set(HttpHeaders.USER_AGENT, "DiscordMS");

    ParameterizedTypeReference<Map<String, Object>> parameterizedTypeReference =
        new ParameterizedTypeReference<Map<String, Object>>() {
        };
    ResponseEntity<Map<String, Object>> responseEntity = this.restOperations.exchange(
        providerDetails.getUserInfoEndpoint().getUri(),
        HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        parameterizedTypeReference
    );

    Optional<Map<String, Object>> optionalUserAttributes = Optional.ofNullable(responseEntity.getBody());
    Map<String, Object> userAttributes = optionalUserAttributes.orElseThrow(InvalidUserAttributes::new);
    Set<GrantedAuthority> authorities = Collections.singleton(new OAuth2UserAuthority(userAttributes));

    long idValue = Long.parseLong(userAttributes.get("id").toString());
    String avatarHash = userAttributes.get("avatar").toString();
    Optional<UserEntityImpl> userEntity = this.userRepository.findById(idValue);

    if (!userEntity.isPresent()) {
      userEntity = Optional.of(new UserBuilder()
          .withIdentifier(idValue)
          .withUsername(userAttributes.get("username").toString())
          .withDiscriminator(Integer.parseInt(userAttributes.get("discriminator").toString()))
          .withAvatarHash(avatarHash)
          .withLocale(userAttributes.get("locale").toString())
          .withAvatarUrl("https://cdn.discordapp.com/avatars/" + idValue + "/" + avatarHash)
          .withCreated(LocalDateTime.now())
          .withLastLogin(LocalDateTime.now())
          .withAuthorities(authorities)
          .withAttributes(userAttributes)
          .build());
    } else {
      userEntity.get().setLastLogin(LocalDateTime.now());
    }

    return this.userRepository.save(userEntity.get());
  }

}
