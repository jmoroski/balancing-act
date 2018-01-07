package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User   {

  private @Valid String id = null;
  private @Valid String firstName = null;
  private @Valid String lastName = null;
  private @Valid String userType = null;
  private @Valid ContactInfo contactInfo = new ContactInfo();

  /**
   **/
  public User id(String id) {
    this.id = id;
    return this;
  }


  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   **/
  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }


  @ApiModelProperty(required = true, value = "")
  @JsonProperty("firstName")
  @NotNull
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   **/
  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }


  @ApiModelProperty(required = true, value = "")
  @JsonProperty("lastName")
  @NotNull
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   **/
  public User userType(String userType) {
    this.userType = userType;
    return this;
  }


  @ApiModelProperty(required = true, value = "")
  @JsonProperty("userType")
  @NotNull
  public String getUserType() {
    return userType;
  }
  public void setUserType(String userType) {
    this.userType = userType;
  }

  /**
   **/
  public User contactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
    return this;
  }


  @ApiModelProperty(value = "")
  @JsonProperty("contactInfo")
  public ContactInfo getContactInfo() {
    return contactInfo;
  }
  public void setContactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) &&
        Objects.equals(firstName, user.firstName) &&
        Objects.equals(lastName, user.lastName) &&
        Objects.equals(userType, user.userType) &&
        Objects.equals(contactInfo, user.contactInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, userType, contactInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
    sb.append("    contactInfo: ").append(toIndentedString(contactInfo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

