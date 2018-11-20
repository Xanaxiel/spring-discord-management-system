package pl.bmstefanski.discordms.web.user;

import pl.bmstefanski.discordms.web.profile.ProfileDetailsForm;

public interface UserService {

  void saveUser(User user);

  void convertProfileDetailsToUserEntity(User user, ProfileDetailsForm form);

}
