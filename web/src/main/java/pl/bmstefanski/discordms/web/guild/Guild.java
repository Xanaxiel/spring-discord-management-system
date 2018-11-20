package pl.bmstefanski.discordms.web.guild;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import pl.bmstefanski.discordms.web.util.IdentifiableEntity;

public class Guild implements Serializable, IdentifiableEntity<Long> {

  @JsonProperty("id")
  private long identifier;
  private long permissions;
  private boolean owner;
  private String name;
  private String icon;

  Guild(boolean owner, long permissions, String icon, long identifier, String name) {
    this.owner = owner;
    this.permissions = permissions;
    this.icon = icon;
    this.identifier = identifier;
    this.name = name;
  }

  public boolean isOwner() {
    return owner;
  }

  public void setOwner(boolean owner) {
    this.owner = owner;
  }

  public long getPermissions() {
    return permissions;
  }

  public void setPermissions(long permissions) {
    this.permissions = permissions;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  @Override
  public Long getIdentifier() {
    return identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Guild{" +
        "owner=" + owner +
        ", permissions=" + permissions +
        ", icon='" + icon + '\'' +
        ", identifier=" + identifier +
        ", name='" + name + '\'' +
        '}';
  }

}
