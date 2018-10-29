package pl.bmstefanski.discordms.web.service;

import pl.bmstefanski.discordms.web.entity.user.UserEntity;
import pl.bmstefanski.discordms.web.form.ProfileDetailsForm;

public interface UserService {

  void saveUser(UserEntity userEntity);

  void convertProfileDetailsToUserEntity(UserEntity userEntity, ProfileDetailsForm form);

}
