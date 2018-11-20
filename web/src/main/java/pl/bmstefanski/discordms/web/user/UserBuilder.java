package pl.bmstefanski.discordms.web.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import pl.bmstefanski.discordms.web.guild.Guild;
import pl.bmstefanski.discordms.web.util.Buildable;

public class UserBuilder implements Buildable<User> {

  private long identifier;
  private int discriminator;
  private String username;
  private String avatarHash;
  private String locale;
  private String email;
  private String firstName;
  private String secondName;
  private String description;
  private String bannerUrl;
  private LocalDate birthday;
  private LocalDateTime created;
  private LocalDateTime lastLogin;
  private Set<GrantedAuthority> authorities;
  private Map<String, Object> attributes;
  private List<Guild> guildEntities;

  public UserBuilder() {
    this.firstName = "N/A";
    this.secondName = "N/A";
    this.description = "N/A";
    this.birthday = LocalDate.now();
  }

  public UserBuilder withBannerUrl(String bannerUrl) {
    this.bannerUrl = bannerUrl;
    return this;
  }

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

  public UserBuilder withGuildEntities(List<Guild> guildEntities) {
    this.guildEntities = guildEntities;
    return this;
  }

  public UserBuilder withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public UserBuilder withSecondName(String secondName) {
    this.secondName = secondName;
    return this;
  }

  public UserBuilder withDescription(String description) {
    this.description = description;
    return this;
  }

  public UserBuilder withBirthday(LocalDate birthDay) {
    this.birthday = birthDay;
    return this;
  }

  @Override
  public User build() {
    return new User(this.identifier, this.discriminator, this.username, this.avatarHash,
        this.locale, this.email, this.firstName, this.secondName, this.description, this.bannerUrl,
        this.birthday, this.created, this.lastLogin, this.authorities, this.attributes,
        this.guildEntities);
  }

}
