package ca.bc.gov.ride.geocodersvc.domain.model.google;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
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

@JsonTypeName("googleRaw")
public class GoogleRaw {

  @JsonProperty("results")
  private List<GoogleResults> results;

}

