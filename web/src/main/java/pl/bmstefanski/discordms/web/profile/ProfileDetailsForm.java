package pl.bmstefanski.discordms.web.profile;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

public class ProfileDetailsForm implements Serializable {

  private String firstName;
  private String secondName;
  private String description;
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate birthday;
  private MultipartFile banner;

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return this.secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public MultipartFile getBanner() {
    return this.banner;
  }

  public void setBanner(MultipartFile banner) {
    this.banner = banner;
  }

}
