package pl.bmstefanski.discordms.web.entity.user;

import java.time.LocalDateTime;
import pl.bmstefanski.discordms.web.util.Buildable;

public class UserBuilder implements Buildable<UserEntityImpl> {

  private long identifier;
  private String username;
  private int discriminator;
  private String avatarHash;
  private String avatarUrl;
  private String locale;
  private LocalDateTime created;
  private LocalDateTime lastLogin;

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

  public UserBuilder withCreated(LocalDateTime created) {
    this.created = created;
    return this;
  }

  public UserBuilder withLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
    return this;
  }

  @Override
  public UserEntityImpl build() {
    return new UserEntityImpl(this.identifier, this.username, this.discriminator, this.avatarHash,
        this.avatarUrl, this.locale, this.created, this.lastLogin);
  }

}
