/**
 * Auto generated class. Using http://www.jsonschema2pojo.org/ 
 * Parameters:
 * *Package: 
 * *Class name:
 * *Target language: Java
 * *Source type: JSON
 * *Annotation style: none
 * *Include getters and setters
 * *Property word delimiters: empty string
 */
package bcgov.jh.etk.jhetkcommon.model.ticketdispute;

import io.swagger.annotations.ApiModel;

/**
 * The Class Contravention.
 * @author HLiang
 */
@ApiModel("Contravention_ticketDispute")
public class Contravention {

    /** The number. */
    private String number;
    
    /** The dispute date. */
    private String dispute_date;
    
    /** The violation date. */
    private String violation_date;
    
    /** The date of service. */
    private String date_of_service;
    
    /** The violation city. */
    private String violation_city;
    
    /** The icbc act regulation. */
    private String icbc_act_regulation;
    
    /** The icbc compressed section. */
    private String icbc_compressed_section;
    
    /** The ticketed amount. */
    private String ticketed_amount;
    
    /** The dispute type code. */
    private String dispute_type_code;

    /**
     * Gets the number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number.
     *
     * @param number the new number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Gets the dispute date.
     *
     * @return the dispute date
     */
    public String getDispute_date() {
        return dispute_date;
    }

    /**
     * Sets the dispute date.
     *
     * @param dispute_date the new dispute date
     */
    public void setDispute_date(String dispute_date) {
        this.dispute_date = dispute_date;
    }

    /**
     * Gets the violation date.
     *
     * @return the violation date
     */
    public String getViolation_date() {
        return violation_date;
    }

    /**
     * Sets the violation date.
     *
     * @param violation_date the new violation date
     */
    public void setViolation_date(String violation_date) {
        this.violation_date = violation_date;
    }

    /**
     * Gets the date of service.
     *
     * @return the date of service
     */
    public String getDate_of_service() {
        return date_of_service;
    }

    /**
     * Sets the date of service.
     *
     * @param date_of_service the new date of service
     */
    public void setDate_of_service(String date_of_service) {
        this.date_of_service = date_of_service;
    }

    /**
     * Gets the violation city.
     *
     * @return the violation city
     */
    public String getViolation_city() {
        return violation_city;
    }

    /**
     * Sets the violation city.
     *
     * @param violation_city the new violation city
     */
    public void setViolation_city(String violation_city) {
        this.violation_city = violation_city;
    }

    /**
     * Gets the icbc act regulation.
     *
     * @return the icbc act regulation
     */
    public String getIcbc_act_regulation() {
        return icbc_act_regulation;
    }

    /**
     * Sets the icbc act regulation.
     *
     * @param icbc_act_regulation the new icbc act regulation
     */
    public void setIcbc_act_regulation(String icbc_act_regulation) {
        this.icbc_act_regulation = icbc_act_regulation;
    }

    /**
     * Gets the icbc compressed section.
     *
     * @return the icbc compressed section
     */
    public String getIcbc_compressed_section() {
        return icbc_compressed_section;
    }

    /**
     * Sets the icbc compressed section.
     *
     * @param icbc_compressed_section the new icbc compressed section
     */
    public void setIcbc_compressed_section(String icbc_compressed_section) {
        this.icbc_compressed_section = icbc_compressed_section;
    }

    /**
     * Gets the ticketed amount.
     *
     * @return the ticketed amount
     */
    public String getTicketed_amount() {
        return ticketed_amount;
    }

    /**
     * Sets the ticketed amount.
     *
     * @param ticketed_amount the new ticketed amount
     */
    public void setTicketed_amount(String ticketed_amount) {
        this.ticketed_amount = ticketed_amount;
    }

    /**
     * Gets the dispute type code.
     *
     * @return the dispute type code
     */
    public String getDispute_type_code() {
        return dispute_type_code;
    }

    /**
     * Sets the dispute type code.
     *
     * @param dispute_type_code the new dispute type code
     */
    public void setDispute_type_code(String dispute_type_code) {
        this.dispute_type_code = dispute_type_code;
    }

}
