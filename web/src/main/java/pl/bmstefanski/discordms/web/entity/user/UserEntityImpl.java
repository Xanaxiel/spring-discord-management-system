package pl.bmstefanski.discordms.web.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import pl.bmstefanski.discordms.web.entity.guild.GuildEntityImpl;

@JsonIgnoreProperties({"name", "attributes", "authorities"})
@Entity
@Table(name = "users")
public class UserEntityImpl implements UserEntity {

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
  private LocalDate birthday;
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

  UserEntityImpl(long identifier, int discriminator, String username,
      String avatarHash, String locale, String email, String firstName, String secondName,
      String description, LocalDate birthday, LocalDateTime created,
      LocalDateTime lastLogin,
      Set<GrantedAuthority> authorities, Map<String, Object> attributes,
      List<GuildEntityImpl> guildEntities) {
    this.identifier = identifier;
    this.discriminator = discriminator;
    this.username = username;
    this.avatarHash = avatarHash;
    this.locale = locale;
    this.email = email;
    this.firstName = firstName;
    this.secondName = secondName;
    this.description = description;
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
    return "https://cdn.discordapp.com/avatars/" + this.identifier + "/" + this.avatarHash;
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
  public void setAuthorities(Set<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getName() {
    return this.username;
  }

  @Override
  public List<GuildEntityImpl> getGuildEntities() {
    return this.guildEntities;
  }

  @Override
  public void setGuildEntities(List<GuildEntityImpl> guildEntities) {
    this.guildEntities = guildEntities;
  }

  @Override
  public String getFirstName() {
    return this.firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getSecondName() {
    return this.secondName;
  }

  @Override
  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public LocalDate getBirthday() {
    return this.birthday;
  }

  @Override
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
