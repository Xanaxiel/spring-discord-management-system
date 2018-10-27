package pl.bmstefanski.discordms.web.form;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ProfileDetailsForm implements Serializable {

  private String firstName;
  private String secondName;
  private String description;
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate birthday;

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

}
