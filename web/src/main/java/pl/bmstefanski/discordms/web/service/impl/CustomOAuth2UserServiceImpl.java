package pl.bmstefanski.discordms.web.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.web.client.RestOperations;
import pl.bmstefanski.discordms.web.entity.guild.GuildEntityImpl;
import pl.bmstefanski.discordms.web.entity.user.UserBuilder;
import pl.bmstefanski.discordms.web.entity.user.UserEntityImpl;
import pl.bmstefanski.discordms.web.repository.UserRepository;
import pl.bmstefanski.discordms.web.request.DiscordGuildsRequest;
import pl.bmstefanski.discordms.web.request.DiscordUserRequest;
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
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + userRequest.getAccessToken().getTokenValue());
    httpHeaders.set(HttpHeaders.USER_AGENT, "DiscordMS");

    Map<String, Object> userAttributes = new DiscordUserRequest(this.restOperations, httpHeaders).submitRequest();
    List<GuildEntityImpl> guildEntities = new DiscordGuildsRequest(this.restOperations, httpHeaders).submitRequest();
    Set<GrantedAuthority> authorities = Collections.singleton(new OAuth2UserAuthority(userAttributes));

    long userIdentifier = Long.parseLong(userAttributes.get("id").toString());
    int discriminator = Integer.parseInt(userAttributes.get("discriminator").toString());
    String username = userAttributes.get("username").toString();
    String locale = userAttributes.get("locale").toString();
    String avatarHash = userAttributes.get("avatar").toString();
    String email = userAttributes.get("email").toString();

    Optional<UserEntityImpl> userEntity = this.userRepository.findById(userIdentifier);

    if (!userEntity.isPresent()) {

      userEntity = Optional.of(new UserBuilder()
          .withIdentifier(userIdentifier)
          .withUsername(userAttributes.get("username").toString())
          .withDiscriminator(Integer.parseInt(userAttributes.get("discriminator").toString()))
          .withAvatarHash(avatarHash)
          .withLocale(userAttributes.get("locale").toString())
          .withEmail(userAttributes.get("email").toString())
          .withCreated(LocalDateTime.now())
          .withLastLogin(LocalDateTime.now())
          .withAuthorities(authorities)
          .withAttributes(userAttributes)
          .withGuildEntities(guildEntities)
          .build());
    } else {
      userEntity.get().setLastLogin(LocalDateTime.now());
      userEntity.get().setUsername(username);
      userEntity.get().setDiscriminator(discriminator);
      userEntity.get().setLocale(locale);
      userEntity.get().setAvatarHash(avatarHash);
      userEntity.get().setEmail(email);
      userEntity.get().setGuildEntities(guildEntities);
      userEntity.get().setAuthorities(authorities);
      userEntity.get().setAttributes(userAttributes);
    }

    return this.userRepository.save(userEntity.get());
  }

}
