package pl.bmstefanski.discordms.web.guild;

import java.io.Serializable;
import pl.bmstefanski.discordms.web.util.IdentifiableEntity;

public interface GuildEntity extends IdentifiableEntity<Long>, Serializable {

  boolean isOwner();

  void setOwner(boolean owner);

  long getPermissions();

  void setPermissions(long permissions);

  String getIcon();

  void setIcon(String icon);

  String getName();

  void setName(String name);
}
