package pl.bmstefanski.discordms.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import pl.bmstefanski.discordms.web.file.FileStorageService;

@EntityScan(basePackageClasses = {Jsr310JpaConverters.class, WebApplicationBootstrap.class})
@SpringBootApplication
public class WebApplicationBootstrap {

  public static void main(String[] args) {
    SpringApplication.run(WebApplicationBootstrap.class, args);
  }

  @Bean
  CommandLineRunner initialize(FileStorageService fileStorageService) {
    return args -> {
      fileStorageService.initialize();
    };
  }

}
