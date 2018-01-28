package org.ccts.balancingact.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PayrollRuleItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-23T05:20:45.976Z")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "itemType", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = CalculatedPayrollRuleItem.class, name = "CalculatedPayrollRuleItem"),
  @JsonSubTypes.Type(value = SimplePayrollRuleItem.class, name = "SimplePayrollRuleItem"),
})

public class PayrollRuleItem   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("payrollRuleId")
  private String payrollRuleId = null;

  @JsonProperty("itemType")
  private String itemType = null;

  public PayrollRuleItem id(String id) {
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

  public PayrollRuleItem description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PayrollRuleItem payrollRuleId(String payrollRuleId) {
    this.payrollRuleId = payrollRuleId;
    return this;
  }

   /**
   * Get payrollRuleId
   * @return payrollRuleId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getPayrollRuleId() {
    return payrollRuleId;
  }

  public void setPayrollRuleId(String payrollRuleId) {
    this.payrollRuleId = payrollRuleId;
  }

  public PayrollRuleItem itemType(String itemType) {
    this.itemType = itemType;
    return this;
  }

   /**
   * Get itemType
   * @return itemType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayrollRuleItem payrollRuleItem = (PayrollRuleItem) o;
    return Objects.equals(this.id, payrollRuleItem.id) &&
        Objects.equals(this.description, payrollRuleItem.description) &&
        Objects.equals(this.payrollRuleId, payrollRuleItem.payrollRuleId) &&
        Objects.equals(this.itemType, payrollRuleItem.itemType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, payrollRuleId, itemType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayrollRuleItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    payrollRuleId: ").append(toIndentedString(payrollRuleId)).append("\n");
    sb.append("    itemType: ").append(toIndentedString(itemType)).append("\n");
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

