package bcgov.jh.etk.jhetkcommon.dataaccess.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Class ChargeTypeMapsEntity.
 * @author HLiang
 */
@Entity
@Table(name = "dispute_statute_maps")
public class ChargeTypeMapsEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4014543649961741536L;

	/** The map id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MAP_ID")
	private Long MAP_ID;
	
	/** The icbc act txt. */
	@Column(name = "ICBC_ACT_TXT")
	private String ICBC_ACT_TXT;
	
	/** The csb act txt. */
	@Column(name = "CSB_ACT_TXT")
	private String CSB_ACT_TXT;
	
	/** The csb clause txt. */
	@Column(name = "CSB_CLAUSE_TXT")
	private String CSB_CLAUSE_TXT;
	
	/** The csb para txt. */
	@Column(name = "CSB_PARA_TXT")
	private String CSB_PARA_TXT;
	
	/** The csb sect txt. */
	@Column(name = "CSB_SECT_TXT")
	private String CSB_SECT_TXT;
	
	/** The csb subpara txt. */
	@Column(name = "CSB_SUBPARA_TXT")
	private String CSB_SUBPARA_TXT;
	
	/** The csb subsect txt. */
	@Column(name = "CSB_SUBSECT_TXT")
	private String CSB_SUBSECT_TXT;
		
	/** The icbc charge txt. */
	@Column(name = "ICBC_CHARGE_TXT")
	private String ICBC_CHARGE_TXT;
	
	/** The rsi act txt. */
	@Column(name = "RSI_ACT_TXT")
	private String RSI_ACT_TXT;
	
	/** The rsi charge txt. */
	@Column(name = "RSI_CHARGE_TXT")
	private String RSI_CHARGE_TXT;
	
	/** The short desc txt. */
	@Column(name = "SHORT_DESC_TXT")
	private String SHORT_DESC_TXT;

	/**
	 * Gets the map id.
	 *
	 * @return the map id
	 */
	public Long getMAP_ID() {
		return MAP_ID;
	}

	/**
	 * Sets the map id.
	 *
	 * @param mAP_ID the new map id
	 */
	public void setMAP_ID(Long mAP_ID) {
		MAP_ID = mAP_ID;
	}

	/**
	 * Gets the icbc act txt.
	 *
	 * @return the icbc act txt
	 */
	public String getICBC_ACT_TXT() {
		return ICBC_ACT_TXT;
	}

	/**
	 * Sets the icbc act txt.
	 *
	 * @param iCBC_ACT_TXT the new icbc act txt
	 */
	public void setICBC_ACT_TXT(String iCBC_ACT_TXT) {
		ICBC_ACT_TXT = iCBC_ACT_TXT;
	}

	/**
	 * Gets the csb act txt.
	 *
	 * @return the csb act txt
	 */
	public String getCSB_ACT_TXT() {
		return CSB_ACT_TXT;
	}

	/**
	 * Sets the csb act txt.
	 *
	 * @param cSB_ACT_TXT the new csb act txt
	 */
	public void setCSB_ACT_TXT(String cSB_ACT_TXT) {
		CSB_ACT_TXT = cSB_ACT_TXT;
	}

	/**
	 * Gets the csb clause txt.
	 *
	 * @return the csb clause txt
	 */
	public String getCSB_CLAUSE_TXT() {
		return CSB_CLAUSE_TXT;
	}

	/**
	 * Sets the csb clause txt.
	 *
	 * @param cSB_CLAUSE_TXT the new csb clause txt
	 */
	public void setCSB_CLAUSE_TXT(String cSB_CLAUSE_TXT) {
		CSB_CLAUSE_TXT = cSB_CLAUSE_TXT;
	}

	/**
	 * Gets the csb para txt.
	 *
	 * @return the csb para txt
	 */
	public String getCSB_PARA_TXT() {
		return CSB_PARA_TXT;
	}

	/**
	 * Sets the csb para txt.
	 *
	 * @param cSB_PARA_TXT the new csb para txt
	 */
	public void setCSB_PARA_TXT(String cSB_PARA_TXT) {
		CSB_PARA_TXT = cSB_PARA_TXT;
	}

	/**
	 * Gets the csb sect txt.
	 *
	 * @return the csb sect txt
	 */
	public String getCSB_SECT_TXT() {
		return CSB_SECT_TXT;
	}

	/**
	 * Sets the csb sect txt.
	 *
	 * @param cSB_SECT_TXT the new csb sect txt
	 */
	public void setCSB_SECT_TXT(String cSB_SECT_TXT) {
		CSB_SECT_TXT = cSB_SECT_TXT;
	}

	/**
	 * Gets the csb subpara txt.
	 *
	 * @return the csb subpara txt
	 */
	public String getCSB_SUBPARA_TXT() {
		return CSB_SUBPARA_TXT;
	}

	/**
	 * Sets the csb subpara txt.
	 *
	 * @param cSB_SUBPARA_TXT the new csb subpara txt
	 */
	public void setCSB_SUBPARA_TXT(String cSB_SUBPARA_TXT) {
		CSB_SUBPARA_TXT = cSB_SUBPARA_TXT;
	}

	/**
	 * Gets the csb subsect txt.
	 *
	 * @return the csb subsect txt
	 */
	public String getCSB_SUBSECT_TXT() {
		return CSB_SUBSECT_TXT;
	}

	/**
	 * Sets the csb subsect txt.
	 *
	 * @param cSB_SUBSECT_TXT the new csb subsect txt
	 */
	public void setCSB_SUBSECT_TXT(String cSB_SUBSECT_TXT) {
		CSB_SUBSECT_TXT = cSB_SUBSECT_TXT;
	}

	/**
	 * Gets the icbc charge txt.
	 *
	 * @return the icbc charge txt
	 */
	public String getICBC_CHARGE_TXT() {
		return ICBC_CHARGE_TXT;
	}

	/**
	 * Sets the icbc charge txt.
	 *
	 * @param iCBC_CHARGE_TXT the new icbc charge txt
	 */
	public void setICBC_CHARGE_TXT(String iCBC_CHARGE_TXT) {
		ICBC_CHARGE_TXT = iCBC_CHARGE_TXT;
	}

	/**
	 * Gets the rsi act txt.
	 *
	 * @return the rsi act txt
	 */
	public String getRSI_ACT_TXT() {
		return RSI_ACT_TXT;
	}

	/**
	 * Sets the rsi act txt.
	 *
	 * @param rSI_ACT_TXT the new rsi act txt
	 */
	public void setRSI_ACT_TXT(String rSI_ACT_TXT) {
		RSI_ACT_TXT = rSI_ACT_TXT;
	}

	/**
	 * Gets the rsi charge txt.
	 *
	 * @return the rsi charge txt
	 */
	public String getRSI_CHARGE_TXT() {
		return RSI_CHARGE_TXT;
	}

	/**
	 * Sets the rsi charge txt.
	 *
	 * @param rSI_CHARGE_TXT the new rsi charge txt
	 */
	public void setRSI_CHARGE_TXT(String rSI_CHARGE_TXT) {
		RSI_CHARGE_TXT = rSI_CHARGE_TXT;
	}

	/**
	 * Gets the short desc txt.
	 *
	 * @return the short desc txt
	 */
	public String getSHORT_DESC_TXT() {
		return SHORT_DESC_TXT;
	}

	/**
	 * Sets the short desc txt.
	 *
	 * @param sHORT_DESC_TXT the new short desc txt
	 */
	public void setSHORT_DESC_TXT(String sHORT_DESC_TXT) {
		SHORT_DESC_TXT = sHORT_DESC_TXT;
	}

	
}
