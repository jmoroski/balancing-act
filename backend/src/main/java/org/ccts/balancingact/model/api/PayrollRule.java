package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PayrollRule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-23T05:20:45.976Z")

public class PayrollRule   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("payrollId")
  private String payrollId = null;

  @JsonProperty("frequency")
  private RuleFrequency frequency = null;

  @JsonProperty("startDate")
  private LocalDate startDate = null;

  @JsonProperty("endDate")
  private LocalDate endDate = null;

  @JsonProperty("items")
  @Valid
  private List<PayrollRuleItem> items = null;

  public PayrollRule id(String id) {
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

  public PayrollRule name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PayrollRule payrollId(String payrollId) {
    this.payrollId = payrollId;
    return this;
  }

   /**
   * Get payrollId
   * @return payrollId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPayrollId() {
    return payrollId;
  }

  public void setPayrollId(String payrollId) {
    this.payrollId = payrollId;
  }

  public PayrollRule frequency(RuleFrequency frequency) {
    this.frequency = frequency;
    return this;
  }

   /**
   * Get frequency
   * @return frequency
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public RuleFrequency getFrequency() {
    return frequency;
  }

  public void setFrequency(RuleFrequency frequency) {
    this.frequency = frequency;
  }

  public PayrollRule startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * Get startDate
   * @return startDate
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public PayrollRule endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

   /**
   * Get endDate
   * @return endDate
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public PayrollRule items(List<PayrollRuleItem> items) {
    this.items = items;
    return this;
  }

  public PayrollRule addItemsItem(PayrollRuleItem itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<PayrollRuleItem>();
    }
    this.items.add(itemsItem);
    return this;
  }

   /**
   * Get items
   * @return items
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PayrollRuleItem> getItems() {
    return items;
  }

  public void setItems(List<PayrollRuleItem> items) {
    this.items = items;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayrollRule payrollRule = (PayrollRule) o;
    return Objects.equals(this.id, payrollRule.id) &&
        Objects.equals(this.name, payrollRule.name) &&
        Objects.equals(this.payrollId, payrollRule.payrollId) &&
        Objects.equals(this.frequency, payrollRule.frequency) &&
        Objects.equals(this.startDate, payrollRule.startDate) &&
        Objects.equals(this.endDate, payrollRule.endDate) &&
        Objects.equals(this.items, payrollRule.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, payrollId, frequency, startDate, endDate, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayrollRule {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    payrollId: ").append(toIndentedString(payrollId)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

