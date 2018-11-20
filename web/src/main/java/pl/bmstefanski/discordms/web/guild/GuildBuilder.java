package pl.bmstefanski.discordms.web.guild;

import pl.bmstefanski.discordms.web.util.Buildable;

public class GuildBuilder implements Buildable<Guild> {

  private long identifier;
  private long permissions;
  private boolean owner;
  private String name;
  private String icon;

  public GuildBuilder withOwner(boolean owner) {
    this.owner = owner;
    return this;
  }

  public GuildBuilder withPermissions(long permissions) {
    this.permissions = permissions;
    return this;
  }

  public GuildBuilder withIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public GuildBuilder withIdentifier(long identifier) {
    this.identifier = identifier;
    return this;
  }

  public GuildBuilder withName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public Guild build() {
    return new Guild(this.owner, this.permissions, this.icon, this.identifier, this.name);
  }

}
