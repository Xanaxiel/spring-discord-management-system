package pl.bmstefanski.discordms.web.entity.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.bmstefanski.discordms.web.entity.IdentifiableEntity;
import pl.bmstefanski.discordms.web.entity.guild.GuildEntityImpl;
import pl.bmstefanski.discordms.web.form.ProfileDetailsForm;

public interface UserEntity extends IdentifiableEntity<Long>, OAuth2User, Serializable {

  String getUsername();

  void setUsername(String username);

  int getDiscriminator();

  void setDiscriminator(int discriminator);

  String getAvatarHash();

  void setAvatarHash(String avatarHash);

  String getAvatarUrl();

  String getLocale();

  void setLocale(String locale);

  String getEmail();

  void setEmail(String email);

  LocalDateTime getCreated();

  void setCreated(LocalDateTime created);

  LocalDateTime getLastLogin();

  void setLastLogin(LocalDateTime lastLogin);

  void setAuthorities(Set<GrantedAuthority> authorities);

  void setAttributes(Map<String, Object> attributes);

  List<GuildEntityImpl> getGuildEntities();

  void setGuildEntities(List<GuildEntityImpl> guildEntities);

  ProfileDetailsForm getProfileDetailsForm();

  void setProfileDetailsForm(ProfileDetailsForm profileDetailsForm);

}
