package pl.bmstefanski.discordms.web.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import pl.bmstefanski.discordms.web.user.CustomOAuth2UserServiceImpl;
import pl.bmstefanski.discordms.web.user.UserEntityImpl;
import pl.bmstefanski.discordms.web.user.UserRepository;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserRepository userRepository;

  @Autowired
  public SpringSecurityConfiguration(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2Login()
        .loginPage("/login")
        .tokenEndpoint()
        .accessTokenResponseClient(new RestOAuth2AccessTokenResponseClient(this.restOperations()))
        .and()
        .userInfoEndpoint()
        .customUserType(UserEntityImpl.class, "discord")
        .userService(new CustomOAuth2UserServiceImpl(this.restOperations(), this.userRepository));
  }

  @Bean
  public RestOperations restOperations() {
    return new RestTemplate();
  }

}
