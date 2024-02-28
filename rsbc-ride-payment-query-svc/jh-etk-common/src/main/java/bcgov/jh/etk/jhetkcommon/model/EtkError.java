package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCategory;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorSeverity;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorStatus;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class VPHError.
 * @author HLiang
 */
@Setter
@Getter
public class EtkError implements Comparable<EtkError>{
	/** The Constant Interface_issuance_prefix. */
	private static final String Interface_issuance_prefix = "I";
	
	/** The Constant Interface_paymentQuery_prefix. */
	private static final String Interface_paymentQuery_prefix = "Q";
	
	/** The Constant Interface_receiptCreation_prefix. */
	private static final String Interface_receiptCreation_prefix = "C";
	
	/** The Constant Interface_fileDeletion_prefix. */
	private static final String Interface_fileDeletion_prefix = "I.4.2";
	
	/** The Constant Interface_ticketDispute_prefix. */
	private static final String Interface_ticketDispute_prefix = "D";
	
	/** The Constant Interface_statusUpdate_prefix. */
	private static final String Interface_statusUpdate_prefix = "S";
	
	/** The error ID. */
	private Long errorID;
	
	/** The error severity. */
	private EnumErrorSeverity errorSeverity;
	
	/** The error type. */
	private EnumErrorCategory errorCategory;
	
	/** The error status. */
	private EnumErrorStatus errorStatus;
	
	/** The create DT. */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDT;
	
	/** The update DT. */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDT;
	
	/** The error description. */
	private String errorDescription;
	
	/** The correlation ticket file name. */
	//private String correlationTicketFileName;
	
	/** The correlation ticket number. */
	private String correlationTicketNumber;
	
	/** The correlation contravention number. */
	private String correlationContraventionNumber;
	
	/** The assigned role. */
	private String assignedRole;
	
	/** The ICBC text. */
	private String ICBCText;
	
	/** The PRIME text. */
	private String PRIMEText;
	
	/** The JUSTIN text. */
	private String JUSTINText;
	
	/** The update user ID. */
	private String updateUserID;
	
	/** The error source. */
	private String errorSource;
	
	/** The error code. */
	private EnumErrorCode errorCode;
	
	/** The ticket file exist. */
	private boolean ticketFileExist;

	/** The vph interface. */
	@SuppressWarnings("unused")
	private EnumInterface vphInterface;
	
	/** The ticket ID. */
	private Integer correlationTicketID;
	
	/** The related ticket file name. */
	private String correlationTicketFileName;
	
	/**
	 * Gets the creates the DT.
	 *
	 * @return the creates the DT
	 */
	public LocalDateTime getCreateDT() {
		return DateUtil.toPacificLDT(createDT);
	}

	/**
	 * Gets the update DT.
	 *
	 * @return the update DT
	 */
	public LocalDateTime getUpdateDT() {
		return DateUtil.toPacificLDT(updateDT);
	}

	/**
	 * Gets the vph interface.
	 *
	 * @return the vph interface
	 */
	public EnumInterface getVphInterface() {
		return getVphInterfaceHelper(this.errorCode.getErrorCode());
	}

	/**
	 * Gets the error code string.
	 *
	 * @return the error code string
	 */
	public String getErrorCodeString() {
		return errorCode.getErrorCode();
	}
	
	/**
	 * Gets the vph interface helper.
	 *
	 * @param errorCode the error code
	 * @return the vph interface helper
	 */
	private EnumInterface getVphInterfaceHelper(final String errorCode) {
		if (StringUtils.isBlank(this.errorCode.getErrorCode())) {
			return EnumInterface.VPH_G;
		} 
		
		if (this.errorCode.getErrorCode().startsWith(Interface_fileDeletion_prefix)) {
			return EnumInterface.VPH_FD;	
		} 
		
		if (this.errorCode.getErrorCode().startsWith(Interface_issuance_prefix)) {
			return EnumInterface.ICBC_CC;	
		} 
		
		if (this.errorCode.getErrorCode().startsWith(Interface_paymentQuery_prefix)) {
			return EnumInterface.ICBC_QT;	
		} 
		
		if (this.errorCode.getErrorCode().startsWith(Interface_receiptCreation_prefix)) {
			return EnumInterface.ICBC_CR;	
		} 
		
		if (this.errorCode.getErrorCode().startsWith(Interface_ticketDispute_prefix)) {
			return EnumInterface.JSTN_TD;	
		}
		
		if (this.errorCode.getErrorCode().startsWith(Interface_statusUpdate_prefix)) {
			return EnumInterface.JSTN_SU;	
		}
		
		return EnumInterface.VPH_G;
	}

	@Override
	public int compareTo(EtkError arg0) {
		return arg0.getErrorID().compareTo(this.errorID);
	}
}
