package pl.bmstefanski.discordms.web.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAttributeControllerAdvice {

  @ModelAttribute("user")
  public User userAttribute(@AuthenticationPrincipal User user) {
    return user;
  }

}
