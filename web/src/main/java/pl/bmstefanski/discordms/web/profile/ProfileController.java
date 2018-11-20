package pl.bmstefanski.discordms.web.profile;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bmstefanski.discordms.web.user.UserEntityImpl;
import pl.bmstefanski.discordms.web.user.UserService;

@RequestMapping("/profile")
@Controller
public class ProfileController {

  private final ProfileService profileService;
  private final UserService userService;

  @Autowired
  public ProfileController(ProfileService profileService, UserService userService) {
    this.profileService = profileService;
    this.userService = userService;
  }

  @GetMapping("/")
  public String profile(Model model) {
    model.addAttribute("profileDetails", new ProfileDetailsForm());
    return "my-profile";
  }

  @PostMapping("/")
  public String profile(@ModelAttribute ProfileDetailsForm profileDetails,
      @AuthenticationPrincipal UserEntityImpl userEntity, Model model) {
    this.userService.convertProfileDetailsToUserEntity(userEntity, profileDetails);
    return this.profileService.findProfilePageByUserId(userEntity.getIdentifier(), model, "#");
  }

  @GetMapping("/{id}")
  public String profile(@PathVariable long id, Model model, HttpServletRequest request) {
    return this.profileService.findProfilePageByUserId(id, model, request.getHeader("Referer"));
  }

}
