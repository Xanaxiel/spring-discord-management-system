package pl.bmstefanski.discordms.web.entity.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import pl.bmstefanski.discordms.web.entity.guild.GuildEntityImpl;
import pl.bmstefanski.discordms.web.util.Buildable;

public class UserBuilder implements Buildable<UserEntityImpl> {

  private long identifier;
  private String username;
  private int discriminator;
  private String avatarHash;
  private String avatarUrl;
  private String locale;
  private String email;
  private LocalDateTime created;
  private LocalDateTime lastLogin;
  private Set<GrantedAuthority> authorities;
  private Map<String, Object> attributes;
  private List<GuildEntityImpl> guildEntities;

  public UserBuilder withIdentifier(long identifier) {
    this.identifier = identifier;
    return this;
  }

  public UserBuilder withUsername(String username) {
    this.username = username;
    return this;
  }

  public UserBuilder withDiscriminator(int discriminator) {
    this.discriminator = discriminator;
    return this;
  }

  public UserBuilder withAvatarHash(String avatarHash) {
    this.avatarHash = avatarHash;
    return this;
  }

  public UserBuilder withAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
    return this;
  }

  public UserBuilder withLocale(String locale) {
    this.locale = locale;
    return this;
  }

  public UserBuilder withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserBuilder withCreated(LocalDateTime created) {
    this.created = created;
    return this;
  }

  public UserBuilder withLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
    return this;
  }

  public UserBuilder withAuthorities(Set<GrantedAuthority> authorities) {
    this.authorities = authorities;
    return this;
  }

  public UserBuilder withAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
    return this;
  }

  public UserBuilder withGuildEntities(List<GuildEntityImpl> guildEntities) {
    this.guildEntities = guildEntities;
    return this;
  }

  @Override
  public UserEntityImpl build() {
    return new UserEntityImpl(this.identifier, this.username, this.discriminator, this.avatarHash,
        this.avatarUrl, this.locale, this.email, this.created, this.lastLogin, this.authorities,
        this.attributes, this.guildEntities);
  }

}
