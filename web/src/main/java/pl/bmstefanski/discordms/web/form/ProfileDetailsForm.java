package pl.bmstefanski.discordms.web.form;

import java.io.Serializable;

public class ProfileDetailsForm implements Serializable {

  private String name;
  private String surname;
  private String description;

  public ProfileDetailsForm() {
    this.name = "N/A";
    this.surname = "N/A";
    this.description = "N/A";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
