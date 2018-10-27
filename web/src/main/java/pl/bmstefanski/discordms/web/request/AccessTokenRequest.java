package pl.bmstefanski.discordms.web.request;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import pl.bmstefanski.discordms.web.configuration.security.AccessTokenResponse;

public class AccessTokenRequest implements Requestable<AccessTokenResponse> {

  private final RestOperations restOperations;
  private final MultiValueMap multiValueMap;

  public AccessTokenRequest(RestOperations restOperations, MultiValueMap multiValueMap) {
    this.restOperations = restOperations;
    this.multiValueMap = multiValueMap;
  }

  @Override
  public AccessTokenResponse submitRequest() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    httpHeaders.add(HttpHeaders.USER_AGENT, "DiscordMS");

    return restOperations.exchange("https://discordapp.com/api/oauth2/token",
        HttpMethod.POST,
        new HttpEntity<>(this.multiValueMap, httpHeaders),
        AccessTokenResponse.class
    ).getBody();
  }

}
