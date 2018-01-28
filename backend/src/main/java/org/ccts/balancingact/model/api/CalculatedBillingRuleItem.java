package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CalculatedBillingRuleItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T03:58:06.530Z")

public class CalculatedBillingRuleItem extends BillingRuleItem  {
  @JsonProperty("quantity")
  private BigDecimal quantity = null;

  @JsonProperty("rate")
  private BigDecimal rate = null;

  public CalculatedBillingRuleItem quantity(BigDecimal quantity) {
    this.quantity = quantity;
    return this;
  }

   /**
   * Get quantity
   * @return quantity
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public CalculatedBillingRuleItem rate(BigDecimal rate) {
    this.rate = rate;
    return this;
  }

   /**
   * Get rate
   * @return rate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalculatedBillingRuleItem calculatedBillingRuleItem = (CalculatedBillingRuleItem) o;
    return Objects.equals(this.quantity, calculatedBillingRuleItem.quantity) &&
        Objects.equals(this.rate, calculatedBillingRuleItem.rate) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantity, rate, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalculatedBillingRuleItem {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
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

