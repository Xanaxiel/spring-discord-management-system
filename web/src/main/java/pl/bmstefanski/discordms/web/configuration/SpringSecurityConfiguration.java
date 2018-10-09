package pl.bmstefanski.discordms.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/console/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and().formLogin()
        .and().csrf().ignoringAntMatchers("/console/**")
        .and().headers().frameOptions().sameOrigin();
  }

}
