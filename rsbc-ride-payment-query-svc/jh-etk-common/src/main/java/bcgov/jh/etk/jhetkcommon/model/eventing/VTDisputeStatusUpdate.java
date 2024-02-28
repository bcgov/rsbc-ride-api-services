/*
 * RSBC Hub Event API
 * # Summary Event API for the Road Safety BC Hub.  # Version naming convention  X.y - where x = major (breaking) changes, and y = minor (non-breaking) changes.  # Change log  | version | date       | description | | ------- | ---------- | -----------------------------| | 1.1     | 2020-01-30 | JHI-815 Initial event publish API| | 1.2     | 2020-02-19 | JHi-940 Add offender_type_name to EVT_Issuance data model| |         |            | JHi-940 Add non-breaking changes to support event lookup| | 1.3     | 2020-04-21 | JHI-1145 updated API to include additional data elements identified in BI-852 | | 1.4     | 2020-06-19 | JHI-1246 updated API to include payment transaction id | | 1.5     | 2020-08-05 | JHI-1474 Wording Number Field Required for Violation Records | | 1.6     | 2021-06-30 | JHI-1329 Add the ability to record additional ETK processing events | 
 *
 * OpenAPI spec version: 1.6
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package bcgov.jh.etk.jhetkcommon.model.eventing;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Violation ticket dispute status update
 */
@ApiModel(description = "Violation ticket dispute status update")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-07-02T15:57:15.869-07:00")
public class VTDisputeStatusUpdate {
  @JsonProperty("ticket_number")
  private String ticketNumber = null;

  @JsonProperty("count_number")
  private Integer countNumber = null;

  /**
   * P - Paid C - Cancelled
   */
  public enum DisputeActionCodeEnum {
    P("P"),
    
    C("C");

    private String value;

    DisputeActionCodeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DisputeActionCodeEnum fromValue(String text) {
      for (DisputeActionCodeEnum b : DisputeActionCodeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("dispute_action_code")
  private DisputeActionCodeEnum disputeActionCode = null;

  @JsonProperty("dispute_action_date")
  private String disputeActionDate = null;

  public VTDisputeStatusUpdate ticketNumber(String ticketNumber) {
    this.ticketNumber = ticketNumber;
    return this;
  }

   /**
   * Get ticketNumber
   * @return ticketNumber
  **/
  @ApiModelProperty(example = "EZ12345678", value = "")
  public String getTicketNumber() {
    return ticketNumber;
  }

  public void setTicketNumber(String ticketNumber) {
    this.ticketNumber = ticketNumber;
  }

  public VTDisputeStatusUpdate countNumber(Integer countNumber) {
    this.countNumber = countNumber;
    return this;
  }

   /**
   * Get countNumber
   * minimum: 1
   * @return countNumber
  **/
  @ApiModelProperty(example = "1", value = "")
  public Integer getCountNumber() {
    return countNumber;
  }

  public void setCountNumber(Integer countNumber) {
    this.countNumber = countNumber;
  }

  public VTDisputeStatusUpdate disputeActionCode(DisputeActionCodeEnum disputeActionCode) {
    this.disputeActionCode = disputeActionCode;
    return this;
  }

   /**
   * P - Paid C - Cancelled
   * @return disputeActionCode
  **/
  @ApiModelProperty(example = "P", value = "P - Paid C - Cancelled")
  public DisputeActionCodeEnum getDisputeActionCode() {
    return disputeActionCode;
  }

  public void setDisputeActionCode(DisputeActionCodeEnum disputeActionCode) {
    this.disputeActionCode = disputeActionCode;
  }

  public VTDisputeStatusUpdate disputeActionDate(String disputeActionDate) {
    this.disputeActionDate = disputeActionDate;
    return this;
  }

   /**
   * Get disputeActionDate
   * @return disputeActionDate
  **/
  @ApiModelProperty(example = "2019-09-01", value = "")
  public String getDisputeActionDate() {
    return disputeActionDate;
  }

  public void setDisputeActionDate(String disputeActionDate) {
    this.disputeActionDate = disputeActionDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VTDisputeStatusUpdate vtDisputeStatusUpdate = (VTDisputeStatusUpdate) o;
    return Objects.equals(this.ticketNumber, vtDisputeStatusUpdate.ticketNumber) &&
        Objects.equals(this.countNumber, vtDisputeStatusUpdate.countNumber) &&
        Objects.equals(this.disputeActionCode, vtDisputeStatusUpdate.disputeActionCode) &&
        Objects.equals(this.disputeActionDate, vtDisputeStatusUpdate.disputeActionDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketNumber, countNumber, disputeActionCode, disputeActionDate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VTDisputeStatusUpdate {\n");
    
    sb.append("    ticketNumber: ").append(toIndentedString(ticketNumber)).append("\n");
    sb.append("    countNumber: ").append(toIndentedString(countNumber)).append("\n");
    sb.append("    disputeActionCode: ").append(toIndentedString(disputeActionCode)).append("\n");
    sb.append("    disputeActionDate: ").append(toIndentedString(disputeActionDate)).append("\n");
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

