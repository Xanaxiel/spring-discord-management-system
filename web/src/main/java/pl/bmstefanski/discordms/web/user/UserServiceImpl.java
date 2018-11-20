package pl.bmstefanski.discordms.web.user;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bmstefanski.discordms.web.file.FileStorageService;
import pl.bmstefanski.discordms.web.profile.ProfileDetailsForm;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final FileStorageService fileStorageService;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, FileStorageService fileStorageService) {
    this.userRepository = userRepository;
    this.fileStorageService = fileStorageService;
  }

  @Override
  public void saveUser(User user) {
    this.userRepository.save(user);
  }

  @Override
  public void convertProfileDetailsToUserEntity(User user, ProfileDetailsForm form) {
    user.setFirstName(this.validateFormField(user.getFirstName()));
    user.setSecondName(this.validateFormField(user.getSecondName()));
    user.setDescription(this.validateFormField(user.getDescription()));
    user.setBirthday(form.getBirthday() == null ? LocalDate.now() : form.getBirthday());
    user.setBannerUrl(this.fileStorageService.getFileUrl(form.getBanner()));

    this.saveUser(user);
  }

  private String validateFormField(String value) {
    return value.isEmpty() ? "N/A" : value;
  }

}
