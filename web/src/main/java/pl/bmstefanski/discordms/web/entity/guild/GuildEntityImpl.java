package pl.bmstefanski.discordms.web.entity.guild;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuildEntityImpl implements GuildEntity {

  private boolean owner;
  private long permissions;
  private String icon;
  @JsonProperty("id")
  private long identifier;
  private String name;

  GuildEntityImpl() {
  }

  GuildEntityImpl(boolean owner, long permissions, String icon, long identifier, String name) {
    this.owner = owner;
    this.permissions = permissions;
    this.icon = icon;
    this.identifier = identifier;
    this.name = name;
  }

  @Override
  public boolean isOwner() {
    return owner;
  }

  @Override
  public void setOwner(boolean owner) {
    this.owner = owner;
  }

  @Override
  public long getPermissions() {
    return permissions;
  }

  @Override
  public void setPermissions(long permissions) {
    this.permissions = permissions;
  }

  @Override
  public String getIcon() {
    return icon;
  }

  @Override
  public void setIcon(String icon) {
    this.icon = icon;
  }

  @Override
  public Long getIdentifier() {
    return identifier;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "GuildEntityImpl{" +
        "owner=" + owner +
        ", permissions=" + permissions +
        ", icon='" + icon + '\'' +
        ", identifier=" + identifier +
        ", name='" + name + '\'' +
        '}';
  }

}
