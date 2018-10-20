package pl.bmstefanski.discordms.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.bmstefanski.discordms.web.entity.user.UserEntityImpl;

@ControllerAdvice
public class GlobalAttributeControllerAdvice {

  @ModelAttribute("user")
  public UserEntityImpl userAttribute(@AuthenticationPrincipal UserEntityImpl user) {
    return user;
  }

}
