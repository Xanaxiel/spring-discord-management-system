package pl.bmstefanski.discordms.web.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import pl.bmstefanski.discordms.web.entity.guild.GuildEntityImpl;

@JsonIgnoreProperties({"name", "attributes", "authorities"})
@Entity
@Table(name = "users")
public class UserEntityImpl implements UserEntity {

  @Id
  private long identifier;
  private String username;
  private int discriminator;
  private String avatarHash;
  private String avatarUrl;
  private String locale;
  private String email;
  private LocalDateTime created;
  private LocalDateTime lastLogin;
  @Transient
  private Set<GrantedAuthority> authorities;
  @Transient
  private Map<String, Object> attributes;
  @ElementCollection
  private List<GuildEntityImpl> guildEntities;

  UserEntityImpl() {
  }

  public UserEntityImpl(long identifier, String username, int discriminator, String avatarHash,
      String avatarUrl, String locale, String email, LocalDateTime created, LocalDateTime lastLogin,
      Set<GrantedAuthority> authorities, Map<String, Object> attributes,
      List<GuildEntityImpl> guildEntities) {
    this.identifier = identifier;
    this.username = username;
    this.discriminator = discriminator;
    this.avatarHash = avatarHash;
    this.avatarUrl = avatarUrl;
    this.locale = locale;
    this.email = email;
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

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public int getDiscriminator() {
    return this.discriminator;
  }

  @Override
  public void setDiscriminator(int discriminator) {
    this.discriminator = discriminator;
  }

  @Override
  public String getAvatarHash() {
    return this.avatarHash;
  }

  @Override
  public void setAvatarHash(String avatarHash) {
    this.avatarHash = avatarHash;
  }

  @Override
  public String getAvatarUrl() {
    return this.avatarUrl;
  }

  @Override
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  @Override
  public String getLocale() {
    return this.locale;
  }

  @Override
  public void setLocale(String locale) {
    this.locale = locale;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public LocalDateTime getCreated() {
    return this.created;
  }

  @Override
  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @Override
  public LocalDateTime getLastLogin() {
    return this.lastLogin;
  }

  @Override
  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
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
    }

    return this.attributes;
  }

  @Override
  public String getName() {
    return this.username;
  }

  @Override
  public List<GuildEntityImpl> getGuildEntities() {
    return guildEntities;
  }

  @Override
  public void setGuildEntities(List<GuildEntityImpl> guildEntities) {
    this.guildEntities = guildEntities;
  }

  @Override
  public String toString() {
    return "UserEntityImpl{" +
        "identifier=" + identifier +
        ", username='" + username + '\'' +
        ", discriminator=" + discriminator +
        ", avatarHash='" + avatarHash + '\'' +
        ", avatarUrl='" + avatarUrl + '\'' +
        ", locale='" + locale + '\'' +
        ", created=" + created +
        ", lastLogin=" + lastLogin +
        ", authorities=" + authorities +
        ", attributes=" + attributes +
        '}';
  }

}
