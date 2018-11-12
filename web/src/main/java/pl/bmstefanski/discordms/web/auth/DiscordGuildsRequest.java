package pl.bmstefanski.discordms.web.auth;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import pl.bmstefanski.discordms.web.guild.GuildEntityImpl;
import pl.bmstefanski.discordms.web.util.Requestable;

public class DiscordGuildsRequest implements Requestable<List<GuildEntityImpl>> {

  private final RestOperations restOperations;
  private final HttpHeaders httpHeaders;

  public DiscordGuildsRequest(RestOperations restOperations, HttpHeaders httpHeaders) {
    this.restOperations = restOperations;
    this.httpHeaders = httpHeaders;
  }

  @Override
  public List<GuildEntityImpl> submitRequest() {
    return this.restOperations.exchange(
        "https://discordapp.com/api/users/@me/guilds",
        HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        new ParameterizedTypeReference<List<GuildEntityImpl>>() {}
    ).getBody();
  }

}
