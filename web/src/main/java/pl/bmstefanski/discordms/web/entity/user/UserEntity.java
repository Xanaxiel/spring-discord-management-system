package pl.bmstefanski.discordms.web.entity.user;

import java.time.LocalDateTime;
import pl.bmstefanski.discordms.web.entity.IdentifiableEntity;

public interface UserEntity extends IdentifiableEntity<Long> {

  String getUsername();

  void setUsername(String username);

  int getDiscriminator();

  void setDiscriminator(int discriminator);

  String getAvatarHash();

  void setAvatarHash(String avatarHash);

  String getAvatarUrl();

  void setAvatarUrl(String avatarUrl);

  String getLocale();

  void setLocale(String locale);

  LocalDateTime getCreated();

  void setCreated(LocalDateTime created);

  LocalDateTime getLastLogin();

  void setLastLogin(LocalDateTime lastLogin);

}
