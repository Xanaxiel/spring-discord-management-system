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
  public void saveUser(UserEntity userEntity) {
    this.userRepository.save((UserEntityImpl) userEntity);
  }

  @Override
  public void convertProfileDetailsToUserEntity(UserEntity userEntity, ProfileDetailsForm form) {
    userEntity.setFirstName(this.validateFormField(userEntity.getFirstName()));
    userEntity.setSecondName(this.validateFormField(userEntity.getSecondName()));
    userEntity.setDescription(this.validateFormField(userEntity.getDescription()));
    userEntity.setBirthday(form.getBirthday() == null ? LocalDate.now() : form.getBirthday());
    userEntity.setBannerUrl(this.fileStorageService.getFileUrl(form.getBanner()));

    this.saveUser(userEntity);
  }

  private String validateFormField(String value) {
    return value.isEmpty() ? "N/A" : value;
  }

}
