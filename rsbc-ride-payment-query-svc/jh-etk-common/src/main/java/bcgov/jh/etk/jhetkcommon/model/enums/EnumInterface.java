package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;

/**
 * The Enum EnumInterface.
 * @author HLiang
 */
@Getter
public enum EnumInterface {
	
	/** The icbc cc. */
	ICBC_CC("ICBC_CC",	"Issuance: Send eVT to ICBC", 1, true), 
	
	/** The icbc qt. */
	ICBC_QT("ICBC_QT",	"Payment: Query ticket request to ICBC", 3, true),
	
	/** The icbc cr. */
	ICBC_CR("ICBC_CR",	"Payment: Create receipt in ICBC", 4, true),
	
	/** The vph fd. */
	VPH_FD("VPH_FD", "Issuance: Delete file from FTP folder", 2, false),
	
	/** The dispute recon. */
	ICBC_DR("ICBC_DR", "Dispute: Reconcile from ICBC", 5, true),
	
	/** The justin td. */
	JSTN_TD("JSTN_TD", "Dispute: Send dispute to JUSTIN", 6, true),
	
	/** The justin su. */
	JSTN_SU("JSTN_SU", "Dispute: Send status update to JUSTIN", 7, true),
	
	/** The jstn df. */
	JSTN_DF("JSTN_DF", "Dispute: Retrieve new findings from JUSTIN", 8, true),
	
	/** The evnt nt. */
	EVNT_NT("EVNT_NT", "Event notification: Send hub events to partners", 9, true),
	
	/** The vph g. */
	VPH_G("VPH_G", "General", 10, false);
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The display order. */
	private int displayOrder;
	
	/** The partner facing. */
	private boolean partnerFacing;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumInterface> mapCode;
	
	/**
	 * Instantiates a new enum interface.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 * @param displayOrder the display order
	 */
	private EnumInterface(final String codeValue, final String codeDescription, final int displayOrder, final boolean partnerFacing) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
		this.displayOrder = displayOrder;
		this.partnerFacing = partnerFacing;
	}
	
	static {
		mapCode = new HashMap<String, EnumInterface>();
		for (EnumInterface v : EnumInterface.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the partner facing interfaces.
	 *
	 * @return the partner facing interfaces
	 */
	public static HashMap<EnumInterface, EnumInterface> getPartnerFacingInterfaces() {
		HashMap<EnumInterface, EnumInterface> partnerFacingInterfaces = new HashMap<EnumInterface, EnumInterface>();
		partnerFacingInterfaces.put(EnumInterface.ICBC_CC, EnumInterface.ICBC_CC);
		partnerFacingInterfaces.put(EnumInterface.ICBC_CR, EnumInterface.ICBC_CR);
		partnerFacingInterfaces.put(EnumInterface.ICBC_DR, EnumInterface.ICBC_DR);
		partnerFacingInterfaces.put(EnumInterface.ICBC_QT, EnumInterface.ICBC_QT);
		partnerFacingInterfaces.put(EnumInterface.JSTN_SU, EnumInterface.JSTN_SU);
		partnerFacingInterfaces.put(EnumInterface.JSTN_TD, EnumInterface.JSTN_TD);
		partnerFacingInterfaces.put(EnumInterface.EVNT_NT, EnumInterface.EVNT_NT);
		//partnerFacingInterfaces.put(EnumInterface.JSTN_DF, EnumInterface.JSTN_DF);
		return partnerFacingInterfaces;
	}
	
	/**
	 * Are all interfaces match.
	 *
	 * @param interfaceMap the interface map
	 * @return true, if successful
	 */
	public static boolean areAllInterfacesMatch(HashMap<EnumInterface, EnumInterface> interfaceMap, HashMap<EnumInterface, EnumInterface> targetInterfaceMap) {
		boolean foundInPartnerFacingInterfaces = true;
		// if targetInterfaceMap is null, default it to partner facing interfaces
		if (targetInterfaceMap == null) {
			targetInterfaceMap = getPartnerFacingInterfaces();
		}
				
		Iterator<Entry<EnumInterface, EnumInterface>> it = interfaceMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<EnumInterface, EnumInterface> pair = (Map.Entry<EnumInterface, EnumInterface>)it.next();
            EnumInterface currentInterface = pair.getKey();
            if (targetInterfaceMap.get(currentInterface) == null) {
            	foundInPartnerFacingInterfaces = false;
			}
        }

        boolean foundInInterfaceMap = true;
        it = targetInterfaceMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<EnumInterface, EnumInterface> pair = (Map.Entry<EnumInterface, EnumInterface>)it.next();
            EnumInterface currentInterface = pair.getKey();
            if (interfaceMap.get(currentInterface) == null) {
            	foundInPartnerFacingInterfaces = false;
			}
        }
		return foundInPartnerFacingInterfaces && foundInInterfaceMap;
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumInterface getEnumfromCodeValue(String codeValue) {
		return mapCode.get(codeValue);
	}

}
