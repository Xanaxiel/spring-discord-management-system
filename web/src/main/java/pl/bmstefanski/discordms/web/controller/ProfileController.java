package pl.bmstefanski.discordms.web.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.bmstefanski.discordms.web.entity.user.UserEntityImpl;
import pl.bmstefanski.discordms.web.repository.UserRepository;

@Controller
public class ProfileController {

  private final UserRepository userRepository;

  @Autowired
  public ProfileController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/profile")
  public String profile() {
    return "my-profile";
  }

  @GetMapping("/profile/{id}")
  public String profile(@PathVariable long id, Model model, HttpServletRequest request) {
    Optional<UserEntityImpl> user = userRepository.findById(id);

    if (user.isPresent()) {
      model.addAttribute("targetUser", user.get());
      return "profile";
    }

    return "redirect:" + request.getHeader("Referer");
  }

}
