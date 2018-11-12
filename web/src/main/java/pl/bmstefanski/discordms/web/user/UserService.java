package pl.bmstefanski.discordms.web.user;

import pl.bmstefanski.discordms.web.profile.ProfileDetailsForm;

public interface UserService {

  void saveUser(UserEntity userEntity);

  void convertProfileDetailsToUserEntity(UserEntity userEntity, ProfileDetailsForm form);

}
