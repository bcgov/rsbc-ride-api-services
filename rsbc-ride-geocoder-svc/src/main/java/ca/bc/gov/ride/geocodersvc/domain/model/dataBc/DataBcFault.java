package ca.bc.gov.ride.geocodersvc.domain.model.dataBc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataBcFault {

  @JsonProperty("value")
  private String value;

  @JsonProperty("element")
  private String element;

  @JsonProperty("fault")
  private String fault;

  @JsonProperty("penalty")
  private Integer penalty;

}

