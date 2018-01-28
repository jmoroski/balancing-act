package org.ccts.balancingact.model.api;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ProgramGroup
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-01-27T20:37:19.433Z")

public class ProgramGroup   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("administrator")
  private Administrator administrator = null;

  @JsonProperty("studentCount")
  private BigDecimal studentCount = null;

  @JsonProperty("payrollRuleCount")
  private BigDecimal payrollRuleCount = null;

  @JsonProperty("serviceRuleCount")
  private BigDecimal serviceRuleCount = null;

  public ProgramGroup id(String id) {
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

  public ProgramGroup name(String name) {
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

  public ProgramGroup administrator(Administrator administrator) {
    this.administrator = administrator;
    return this;
  }

   /**
   * Get administrator
   * @return administrator
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public Administrator getAdministrator() {
    return administrator;
  }

  public void setAdministrator(Administrator administrator) {
    this.administrator = administrator;
  }

  public ProgramGroup studentCount(BigDecimal studentCount) {
    this.studentCount = studentCount;
    return this;
  }

   /**
   * Get studentCount
   * @return studentCount
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getStudentCount() {
    return studentCount;
  }

  public void setStudentCount(BigDecimal studentCount) {
    this.studentCount = studentCount;
  }

  public ProgramGroup payrollRuleCount(BigDecimal payrollRuleCount) {
    this.payrollRuleCount = payrollRuleCount;
    return this;
  }

   /**
   * Get payrollRuleCount
   * @return payrollRuleCount
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getPayrollRuleCount() {
    return payrollRuleCount;
  }

  public void setPayrollRuleCount(BigDecimal payrollRuleCount) {
    this.payrollRuleCount = payrollRuleCount;
  }

  public ProgramGroup serviceRuleCount(BigDecimal serviceRuleCount) {
    this.serviceRuleCount = serviceRuleCount;
    return this;
  }

   /**
   * Get serviceRuleCount
   * @return serviceRuleCount
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getServiceRuleCount() {
    return serviceRuleCount;
  }

  public void setServiceRuleCount(BigDecimal serviceRuleCount) {
    this.serviceRuleCount = serviceRuleCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProgramGroup programGroup = (ProgramGroup) o;
    return Objects.equals(this.id, programGroup.id) &&
        Objects.equals(this.name, programGroup.name) &&
        Objects.equals(this.administrator, programGroup.administrator) &&
        Objects.equals(this.studentCount, programGroup.studentCount) &&
        Objects.equals(this.payrollRuleCount, programGroup.payrollRuleCount) &&
        Objects.equals(this.serviceRuleCount, programGroup.serviceRuleCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, administrator, studentCount, payrollRuleCount, serviceRuleCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProgramGroup {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    administrator: ").append(toIndentedString(administrator)).append("\n");
    sb.append("    studentCount: ").append(toIndentedString(studentCount)).append("\n");
    sb.append("    payrollRuleCount: ").append(toIndentedString(payrollRuleCount)).append("\n");
    sb.append("    serviceRuleCount: ").append(toIndentedString(serviceRuleCount)).append("\n");
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

