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
package bcgov.jh.etk.jhetkcommon.model.disputestatusupdate;

import io.swagger.annotations.ApiModel;

/**
 * The Class Contravention.
 * @author HLiang
 */
@ApiModel("Contravention_disputeStatusUpdate")
public class Contravention {

    /** The number. */
    private String number;
    
    /** The action date. */
    private String action_date;
    
    /** The action code. */
    private String action_code;

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
     * Gets the action date.
     *
     * @return the action date
     */
    public String getAction_date() {
        return action_date;
    }

    /**
     * Sets the action date.
     *
     * @param action_date the new action date
     */
    public void setAction_date(String action_date) {
        this.action_date = action_date;
    }

    /**
     * Gets the action code.
     *
     * @return the action code
     */
    public String getAction_code() {
        return action_code;
    }

    /**
     * Sets the action code.
     *
     * @param action_code the new action code
     */
    public void setAction_code(String action_code) {
        this.action_code = action_code;
    }

}
