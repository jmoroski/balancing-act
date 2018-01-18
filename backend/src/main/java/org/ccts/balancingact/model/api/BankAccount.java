package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BankAccount
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-13T16:19:55.584Z")

public class BankAccount   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("bankId")
  private String bankId = null;

  @JsonProperty("bankName")
  private String bankName = null;

  @JsonProperty("ownerId")
  private String ownerId = null;

  @JsonProperty("ownerFirstName")
  private String ownerFirstName = null;

  @JsonProperty("ownerLastName")
  private String ownerLastName = null;

  public BankAccount id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BankAccount name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BankAccount bankId(String bankId) {
    this.bankId = bankId;
    return this;
  }

   /**
   * Get bankId
   * @return bankId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public BankAccount bankName(String bankName) {
    this.bankName = bankName;
    return this;
  }

   /**
   * Get bankName
   * @return bankName
  **/
  @ApiModelProperty(value = "")


  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public BankAccount ownerId(String ownerId) {
    this.ownerId = ownerId;
    return this;
  }

   /**
   * Get ownerId
   * @return ownerId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public BankAccount ownerFirstName(String ownerFirstName) {
    this.ownerFirstName = ownerFirstName;
    return this;
  }

   /**
   * Get ownerFirstName
   * @return ownerFirstName
  **/
  @ApiModelProperty(value = "")


  public String getOwnerFirstName() {
    return ownerFirstName;
  }

  public void setOwnerFirstName(String ownerFirstName) {
    this.ownerFirstName = ownerFirstName;
  }

  public BankAccount ownerLastName(String ownerLastName) {
    this.ownerLastName = ownerLastName;
    return this;
  }

   /**
   * Get ownerLastName
   * @return ownerLastName
  **/
  @ApiModelProperty(value = "")


  public String getOwnerLastName() {
    return ownerLastName;
  }

  public void setOwnerLastName(String ownerLastName) {
    this.ownerLastName = ownerLastName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccount bankAccount = (BankAccount) o;
    return Objects.equals(this.id, bankAccount.id) &&
        Objects.equals(this.name, bankAccount.name) &&
        Objects.equals(this.bankId, bankAccount.bankId) &&
        Objects.equals(this.bankName, bankAccount.bankName) &&
        Objects.equals(this.ownerId, bankAccount.ownerId) &&
        Objects.equals(this.ownerFirstName, bankAccount.ownerFirstName) &&
        Objects.equals(this.ownerLastName, bankAccount.ownerLastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, bankId, bankName, ownerId, ownerFirstName, ownerLastName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccount {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    bankId: ").append(toIndentedString(bankId)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
    sb.append("    ownerFirstName: ").append(toIndentedString(ownerFirstName)).append("\n");
    sb.append("    ownerLastName: ").append(toIndentedString(ownerLastName)).append("\n");
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

