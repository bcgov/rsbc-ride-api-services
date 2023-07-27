package ca.bc.gov.ride.geocodersvc.domain.model.dataBc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataBcProperties {

  @JsonProperty("fullAddress")
  private String fullAddress;

  @JsonProperty("score")
  private Integer score;

  @JsonProperty("matchPrecision")
  private String matchPrecision;

  @JsonProperty("faults")
  private List<DataBcFault> faults;

}

