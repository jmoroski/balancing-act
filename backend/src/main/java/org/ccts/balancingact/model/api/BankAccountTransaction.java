package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BankAccountTransaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-13T16:19:55.584Z")

public class BankAccountTransaction   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("accountId")
  private String accountId = null;

  @JsonProperty("accountName")
  private String accountName = null;

  @JsonProperty("transactionTime")
  private LocalDate transactionTime = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("name")
  private String name = null;

  public BankAccountTransaction id(String id) {
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

  public BankAccountTransaction accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

   /**
   * Get accountId
   * @return accountId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public BankAccountTransaction accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

   /**
   * Get accountName
   * @return accountName
  **/
  @ApiModelProperty(value = "")


  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public BankAccountTransaction name(String name) {
    this.name = name;
    return this;
  }

  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BankAccountTransaction transactionTime(LocalDate transactionTime) {
    this.transactionTime = transactionTime;
    return this;
  }

   /**
   * Get transactionTime
   * @return transactionTime
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public LocalDate getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(LocalDate transactionTime) {
    this.transactionTime = transactionTime;
  }

  public BankAccountTransaction amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccountTransaction bankAccountTransaction = (BankAccountTransaction) o;
    return Objects.equals(this.id, bankAccountTransaction.id) &&
        Objects.equals(this.accountId, bankAccountTransaction.accountId) &&
        Objects.equals(this.accountName, bankAccountTransaction.accountName) &&
        Objects.equals(this.transactionTime, bankAccountTransaction.transactionTime) &&
        Objects.equals(this.amount, bankAccountTransaction.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, accountId, accountName, transactionTime, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccountTransaction {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    transactionTime: ").append(toIndentedString(transactionTime)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

