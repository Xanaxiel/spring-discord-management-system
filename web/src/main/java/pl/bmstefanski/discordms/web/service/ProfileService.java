package pl.bmstefanski.discordms.web.service;

import org.springframework.ui.Model;

public interface ProfileService {

  String findProfilePageByUserId(long id, Model model, String failureUrl);

}
