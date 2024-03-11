/**
 * Auto generated class. Using http://www.jsonschema2pojo.org/ 
 * Parameters:
 * *Package: bcgov.jh.etk.jhetkcommon.model.disputestatusupdate
 * *Class name: DisputeStatusUpdate
 * *Target language: Java
 * *Source type: JSON
 * *Annotation style: none
 * *Include getters and setters
 * *Property word delimiters: empty string
 */
package bcgov.jh.etk.jhetkcommon.model.disputestatusupdate;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bcgov.jh.etk.jhetkcommon.util.EvtEnrichUtil;

/**
 * The Class DisputeStatusUpdate.
 * @author HLiang
 */
public class DisputeStatusUpdate {

    /** The contravention. */
    private Contravention contravention;
    
    /** The disputant. */
    private Disputant disputant;

    /**
     * Gets the contravention.
     *
     * @return the contravention
     */
    public Contravention getContravention() {
        return contravention;
    }

    /**
     * Sets the contravention.
     *
     * @param contravention the new contravention
     */
    public void setContravention(Contravention contravention) {
        this.contravention = contravention;
    }

    /**
     * Gets the disputant.
     *
     * @return the disputant
     */
    public Disputant getDisputant() {
        return disputant;
    }

    /**
     * Sets the disputant.
     *
     * @param disputant the new disputant
     */
    public void setDisputant(Disputant disputant) {
        this.disputant = disputant;
    }

    /**
	 * Gets the ticket number.
	 *
	 * @return the ticket number
	 */
    @JsonIgnore
	public String getTicket_number() {
    	if (StringUtils.isEmpty(this.contravention)) {
    		return null;
    	}
		return EvtEnrichUtil.getTicketNumber(this.contravention.getNumber());
	}
	
	/**
	 * Gets the count number.
	 *
	 * @return the count number
	 */
    @JsonIgnore
	public String getCount_number() {
    	if (StringUtils.isEmpty(this.contravention)) {
    		return null;
    	}
		return EvtEnrichUtil.getCountNumber(this.contravention.getNumber());
	}
}
