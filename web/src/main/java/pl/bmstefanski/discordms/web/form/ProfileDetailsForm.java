package pl.bmstefanski.discordms.web.form;

import java.io.Serializable;

public class ProfileDetailsForm implements Serializable {

  private String name = "N/A";
  private String surname = "N/A";
  private String description = "N/A";

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
