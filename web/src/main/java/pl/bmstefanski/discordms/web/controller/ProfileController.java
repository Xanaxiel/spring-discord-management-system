package pl.bmstefanski.discordms.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.bmstefanski.discordms.web.service.ProfileService;

@Controller
public class ProfileController {

  private final ProfileService profileService;

  @Autowired
  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @GetMapping("/profile")
  public String profile() {
    return "my-profile";
  }

  @GetMapping("/profile/{id}")
  public String profile(@PathVariable long id, Model model, HttpServletRequest request) {
    return this.profileService.findProfilePageByUserId(id, model, request.getHeader("Referer"));
  }

}
