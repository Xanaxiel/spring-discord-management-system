package pl.bmstefanski.discordms.web.request;

import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

public class DiscordUserRequest implements Requestable<Map<String, Object>> {

  private final RestOperations restOperations;
  private final HttpHeaders httpHeaders;

  public DiscordUserRequest(RestOperations restOperations, HttpHeaders httpHeaders) {
    this.restOperations = restOperations;
    this.httpHeaders = httpHeaders;
  }

  @Override
  public Map<String, Object> submitRequest() {
    return this.restOperations.exchange(
        "https://discordapp.com/api/users/@me",
        HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        new ParameterizedTypeReference<Map<String, Object>>() {}
    ).getBody();
  }

}
