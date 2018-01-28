package org.ccts.balancingact.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets RuleFrequency
 */
public enum RuleFrequency {
  
  BIMONTHLY("Bimonthly"),
  
  MONTHLY("Monthly"),
  
  NONE("None");

  private String value;

  RuleFrequency(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static RuleFrequency fromValue(String text) {
    for (RuleFrequency b : RuleFrequency.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

