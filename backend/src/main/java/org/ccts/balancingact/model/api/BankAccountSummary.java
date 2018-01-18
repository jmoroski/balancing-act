package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BankAccountSummary
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-13T16:19:55.584Z")

public class BankAccountSummary   {
  @JsonProperty("accountId")
  private String accountId = null;

  @JsonProperty("accountName")
  private String accountName = null;

  @JsonProperty("ownerFirstName")
  private String ownerFirstName = null;

  @JsonProperty("ownerLastName")
  private String ownerLastName = null;

  @JsonProperty("bankName")
  private String bankName = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  public BankAccountSummary accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

   /**
   * Get accountId
   * @return accountId
  **/
  @ApiModelProperty(value = "")


  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public BankAccountSummary ownerFirstName(String ownerFirstName) {
    this.ownerFirstName = ownerFirstName;
    return this;
  }

  public BankAccountSummary accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
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

  public BankAccountSummary ownerLastName(String ownerLastName) {
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

  public BankAccountSummary bankName(String bankName) {
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

  public BankAccountSummary balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * @return balance
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccountSummary bankAccountSummary = (BankAccountSummary) o;
    return Objects.equals(this.accountId, bankAccountSummary.accountId) &&
        Objects.equals(this.ownerFirstName, bankAccountSummary.ownerFirstName) &&
        Objects.equals(this.ownerLastName, bankAccountSummary.ownerLastName) &&
        Objects.equals(this.bankName, bankAccountSummary.bankName) &&
        Objects.equals(this.balance, bankAccountSummary.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, ownerFirstName, ownerLastName, bankName, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccountSummary {\n");

    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    ownerFirstName: ").append(toIndentedString(ownerFirstName)).append("\n");
    sb.append("    ownerLastName: ").append(toIndentedString(ownerLastName)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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

