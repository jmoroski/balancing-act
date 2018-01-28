package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * BillingRuleItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-18T03:58:06.530Z")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "itemType", visible = true )
@JsonSubTypes({
  @JsonSubTypes.Type(value = SimpleBillingRuleItem.class, name = "SimpleBillingRuleItem"),
  @JsonSubTypes.Type(value = CalculatedBillingRuleItem.class, name = "CalculatedBillingRuleItem"),
})

public class BillingRuleItem   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("billingRuleId")
  private String billingRuleId = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Gets or Sets itemType
   */
  public enum ItemTypeEnum {
    SIMPLE("Simple"),

    CALCULATED("Calculated");

    private String value;

    ItemTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ItemTypeEnum fromValue(String text) {
      for (ItemTypeEnum b : ItemTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("itemType")
  private ItemTypeEnum itemType = null;

  public BillingRuleItem id(String id) {
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

  public BillingRuleItem billingRuleId(String billingRuleId) {
    this.billingRuleId = billingRuleId;
    return this;
  }

   /**
   * Get billingRuleId
   * @return billingRuleId
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBillingRuleId() {
    return billingRuleId;
  }

  public void setBillingRuleId(String billingRuleId) {
    this.billingRuleId = billingRuleId;
  }

  public BillingRuleItem description(String description) {
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

  public BillingRuleItem itemType(ItemTypeEnum itemType) {
    this.itemType = itemType;
    return this;
  }

   /**
   * Get itemType
   * @return itemType
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public ItemTypeEnum getItemType() {
    return itemType;
  }

  public void setItemType(ItemTypeEnum itemType) {
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
    BillingRuleItem billingRuleItem = (BillingRuleItem) o;
    return Objects.equals(this.id, billingRuleItem.id) &&
        Objects.equals(this.billingRuleId, billingRuleItem.billingRuleId) &&
        Objects.equals(this.description, billingRuleItem.description) &&
        Objects.equals(this.itemType, billingRuleItem.itemType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, billingRuleId, description, itemType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BillingRuleItem {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    billingRuleId: ").append(toIndentedString(billingRuleId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

