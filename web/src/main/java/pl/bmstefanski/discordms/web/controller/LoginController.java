package pl.bmstefanski.discordms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login() {
    return "redirect:/oauth2/authorization/discord";
  }

}
