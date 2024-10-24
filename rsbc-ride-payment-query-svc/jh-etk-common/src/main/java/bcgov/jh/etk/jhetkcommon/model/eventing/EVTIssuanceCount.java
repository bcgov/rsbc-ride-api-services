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
 * Electronic violation ticket issuance count
 */
@ApiModel(description = "Electronic violation ticket issuance count")
@jakarta.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-07-02T15:57:15.869-07:00")
public class EVTIssuanceCount {
  @JsonProperty("count_number")
  private Integer countNumber = null;

  @JsonProperty("act_code")
  private String actCode = null;

  @JsonProperty("section_text")
  private String sectionText = null;

  @JsonProperty("section_desc")
  private String sectionDesc = null;

  @JsonProperty("fine_amount")
  private String fineAmount = null;

  @JsonProperty("wording_nbr")
  private Integer wordingNbr = null;

  public EVTIssuanceCount countNumber(Integer countNumber) {
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

  public EVTIssuanceCount actCode(String actCode) {
    this.actCode = actCode;
    return this;
  }

   /**
   * Get actCode
   * @return actCode
  **/
  @ApiModelProperty(example = "MR", value = "")
  public String getActCode() {
    return actCode;
  }

  public void setActCode(String actCode) {
    this.actCode = actCode;
  }

  public EVTIssuanceCount sectionText(String sectionText) {
    this.sectionText = sectionText;
    return this;
  }

   /**
   * Get sectionText
   * @return sectionText
  **/
  @ApiModelProperty(example = "30.10(4)", value = "")
  public String getSectionText() {
    return sectionText;
  }

  public void setSectionText(String sectionText) {
    this.sectionText = sectionText;
  }

  public EVTIssuanceCount sectionDesc(String sectionDesc) {
    this.sectionDesc = sectionDesc;
    return this;
  }

   /**
   * Get sectionDesc
   * @return sectionDesc
  **/
  @ApiModelProperty(example = "FAIL TO DISPLAY \"N\" SIGN IN VIOLATION OF DRIVER'S LICENCE CONDITION (S. 25(15) MV)", value = "")
  public String getSectionDesc() {
    return sectionDesc;
  }

  public void setSectionDesc(String sectionDesc) {
    this.sectionDesc = sectionDesc;
  }

  public EVTIssuanceCount fineAmount(String fineAmount) {
    this.fineAmount = fineAmount;
    return this;
  }

   /**
   * Get fineAmount
   * @return fineAmount
  **/
  @ApiModelProperty(example = "109.0", value = "")
  public String getFineAmount() {
    return fineAmount;
  }

  public void setFineAmount(String fineAmount) {
    this.fineAmount = fineAmount;
  }

  public EVTIssuanceCount wordingNbr(Integer wordingNbr) {
    this.wordingNbr = wordingNbr;
    return this;
  }

   /**
   * Get wordingNbr
   * @return wordingNbr
  **/
  @ApiModelProperty(example = "20", value = "")
  public Integer getWordingNbr() {
    return wordingNbr;
  }

  public void setWordingNbr(Integer wordingNbr) {
    this.wordingNbr = wordingNbr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EVTIssuanceCount evTIssuanceCount = (EVTIssuanceCount) o;
    return Objects.equals(this.countNumber, evTIssuanceCount.countNumber) &&
        Objects.equals(this.actCode, evTIssuanceCount.actCode) &&
        Objects.equals(this.sectionText, evTIssuanceCount.sectionText) &&
        Objects.equals(this.sectionDesc, evTIssuanceCount.sectionDesc) &&
        Objects.equals(this.fineAmount, evTIssuanceCount.fineAmount) &&
        Objects.equals(this.wordingNbr, evTIssuanceCount.wordingNbr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countNumber, actCode, sectionText, sectionDesc, fineAmount, wordingNbr);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EVTIssuanceCount {\n");
    
    sb.append("    countNumber: ").append(toIndentedString(countNumber)).append("\n");
    sb.append("    actCode: ").append(toIndentedString(actCode)).append("\n");
    sb.append("    sectionText: ").append(toIndentedString(sectionText)).append("\n");
    sb.append("    sectionDesc: ").append(toIndentedString(sectionDesc)).append("\n");
    sb.append("    fineAmount: ").append(toIndentedString(fineAmount)).append("\n");
    sb.append("    wordingNbr: ").append(toIndentedString(wordingNbr)).append("\n");
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

