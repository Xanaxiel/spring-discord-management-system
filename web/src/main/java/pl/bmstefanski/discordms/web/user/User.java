package pl.bmstefanski.discordms.web.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.bmstefanski.discordms.web.guild.Guild;
import pl.bmstefanski.discordms.web.util.IdentifiableEntity;

@JsonIgnoreProperties({"name", "attributes", "authorities"})
@Entity
@Table(name = "users")
public class User implements OAuth2User, Serializable, IdentifiableEntity<Long> {

  @Id
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
  @Transient
  private Set<GrantedAuthority> authorities;
  @Transient
  private Map<String, Object> attributes;
  @ElementCollection
  private List<Guild> guildEntities;

  User(long identifier, int discriminator, String username,
      String avatarHash, String locale, String email, String firstName, String secondName,
      String description, String bannerUrl, LocalDate birthday, LocalDateTime created,
      LocalDateTime lastLogin,
      Set<GrantedAuthority> authorities, Map<String, Object> attributes,
      List<Guild> guildEntities) {
    this.identifier = identifier;
    this.discriminator = discriminator;
    this.username = username;
    this.avatarHash = avatarHash;
    this.locale = locale;
    this.email = email;
    this.firstName = firstName;
    this.secondName = secondName;
    this.description = description;
    this.bannerUrl = bannerUrl;
    this.birthday = birthday;
    this.created = created;
    this.lastLogin = lastLogin;
    this.authorities = authorities;
    this.attributes = attributes;
    this.guildEntities = guildEntities;
  }

  @Override
  public Long getIdentifier() {
    return this.identifier;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getDiscriminator() {
    return this.discriminator;
  }

  public void setDiscriminator(int discriminator) {
    this.discriminator = discriminator;
  }

  public String getAvatarHash() {
    return this.avatarHash;
  }

  public void setAvatarHash(String avatarHash) {
    this.avatarHash = avatarHash;
  }

  public String getAvatarUrl() {
    return "https://cdn.discordapp.com/avatars/" + this.identifier + "/" + this.avatarHash;
  }

  public String getLocale() {
    return this.locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDateTime getCreated() {
    return this.created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getLastLogin() {
    return this.lastLogin;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  public void setAuthorities(Set<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  public String getName() {
    return this.username;
  }

  public List<Guild> getGuildEntities() {
    return this.guildEntities;
  }

  public void setGuildEntities(List<Guild> guildEntities) {
    this.guildEntities = guildEntities;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return this.secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBannerUrl() {
    return this.bannerUrl;
  }

  public void setBannerUrl(String bannerUrl) {
    this.bannerUrl = bannerUrl;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  @Override
  public Map<String, Object> getAttributes() {
    if (this.attributes == null) {
      this.attributes = new HashMap<>();
      this.attributes.put("id", this.getIdentifier());
      this.attributes.put("username", this.getUsername());
      this.attributes.put("discriminator", this.getDiscriminator());
      this.attributes.put("avatar", this.avatarHash);
      this.attributes.put("locale", this.locale);
      this.attributes.put("email", this.email);
    }

    return this.attributes;
  }

}
