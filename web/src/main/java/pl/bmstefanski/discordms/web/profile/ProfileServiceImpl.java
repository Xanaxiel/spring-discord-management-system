package pl.bmstefanski.discordms.web.profile;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.bmstefanski.discordms.web.user.UserEntityImpl;
import pl.bmstefanski.discordms.web.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

  private final UserRepository userRepository;

  @Autowired
  public ProfileServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;

  }

  @Override
  public String findProfilePageByUserId(long id, Model model, String failureUrl) {
    Optional<UserEntityImpl> user = this.userRepository.findById(id);

    if (user.isPresent()) {
      model.addAttribute("targetUser", user.get());
      return "profile";
    }

    if (failureUrl == null) {
      return "redirect:" + "/home";
    }

    return "redirect:" + failureUrl;
  }

}
