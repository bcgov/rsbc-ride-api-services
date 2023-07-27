package ca.bc.gov.ride.geocodersvc.domain.model.google;

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
public class GoogleGeometry {

  @JsonProperty("location")
  private GoogleLocation location;

  @JsonProperty("location_type")
  private String locationType;

}

