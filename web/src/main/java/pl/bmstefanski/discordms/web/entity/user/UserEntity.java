package pl.bmstefanski.discordms.web.entity.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.oauth2.core.user.OAuth2User;
import pl.bmstefanski.discordms.web.entity.IdentifiableEntity;
import pl.bmstefanski.discordms.web.entity.guild.GuildEntityImpl;

public interface UserEntity extends IdentifiableEntity<Long>, OAuth2User, Serializable {

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

  List<GuildEntityImpl> getGuildEntities();

  void setGuildEntities(List<GuildEntityImpl> guildEntities);

}
