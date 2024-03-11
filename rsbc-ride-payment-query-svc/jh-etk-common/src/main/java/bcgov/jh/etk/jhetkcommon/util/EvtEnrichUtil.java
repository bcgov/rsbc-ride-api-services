package bcgov.jh.etk.jhetkcommon.util;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class EvtEnrichUtil.
 * @author HLiang
 */
public class EvtEnrichUtil {
    /**
     * Gets the ticket number. This is the contravention number minus the count number
     *
     * @param contraventionNumber the contravention number
     * @return the ticket number
     */
    public static String getTicketNumber(final String contraventionNumber) {
    	if (StringUtils.isEmpty(contraventionNumber)) {
    		return null;
    	}
    	
    	int len = contraventionNumber.length();
    	return contraventionNumber.substring(0, len - 1);
    }
    
    /**
     * Gets the count number. This field is in the contravention number, the last digit in the contravention number.
     *
     * @param contraventionNumber the contravention number
     * @return the count number
     */
    public static String getCountNumber(final String contraventionNumber) {
    	if (StringUtils.isEmpty(contraventionNumber)) {
    		return null;
    	}
    	
    	int len = contraventionNumber.length();
    	return contraventionNumber.substring(len - 1, len);
    }
    
    
}
