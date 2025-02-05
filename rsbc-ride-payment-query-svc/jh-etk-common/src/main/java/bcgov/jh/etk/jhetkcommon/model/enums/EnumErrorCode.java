package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumErrorCodes.
 * @author HLiang
 */
public enum EnumErrorCode {
	//general error
	G00("G.0.0", "General error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	I00("I.0.0", "Issuance general error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	I01("I.0.1", "Failed to parse XML", "DATA", "INFO", "Inform PRIMECorp regarding the mal-formed eVT XML file", true),
	I02("I.0.2", "Failed to connect to SFEG", "CONN", "FATAL", "Contact eTK application support for further investigation", false),
	I03("I.0.3", "Failed to move eVT to the processing folder", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	I11("I.1.1", "Failed to trigger eVT process", "OTHER", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK issuance interface general status\n" +
			"- Check for eTK configuration changes\n" +
			"- Check the eTK's issuancesvc component's operational status", false),
	I12("I.1.2", "The same eVT received", "DATA", "INFO", "Contact PRIMECorp regarding the duplicate ticket number", true),
	I13("I.1.3", "eVT with missing ticket number received", "DATA", "INFO", "Inform PRIMECorp regarding the incomplete eVT XML file", true),
	I14("I.1.4", "eVT with duplicate ticket number received", "DATA", "INFO", "Contact PRIMECorp regarding the duplicate ticket number", true),
	I15("I.1.5", "eVT with an old enforcement agency name received", "DATA", "INFO", "Contact PRIMECorp regarding the old enforcement agency name", true),
	I21("I.2.1", "Failed to pass business validation checks", "DATA", "INFO", "Inform PRIMECorp regarding the invalid eVT XML file", true),
	I22("I.2.2", "Failed to pass business warning rules checks", "DATA", "INFO", "Inform PRIMECorp and ICBC regarding the missing data element warning", true),
	I23("I.2.3", "eVT with unknown charge code", "DATA", "INFO", "Inform PRIMECorp regarding the unknown charge code (STATUTE_ACT + LEGAL_STATUTE_SECTION + STATUTE_WORDING)", true),
	I24("I.2.4", "eVT with incorrect charge text", "DATA", "INFO", "Inform PRIMECorp regarding the incorrect charge text", true),
	I26("I.2.6", "eVT with incorrect amount of penalty", "DATA", "INFO", "Inform PRIMECorp regarding the incorrect amount of penalty", true),
	I25("I.2.5", "eVT with repealed charge code", "DATA", "INFO", "Inform issuing agency regarding the repealed charge code - ticket will be cancelled by ICBC (STATUTE_ACT + LEGAL_STATUTE_SECTION + STATUTE_WORDING)	", true),
	I31("I.3.1", "Failed to update eVT processing state before sending eVT", "CONN", "FATAL", "Perform the each of the following steps until issue is resolved:\n" + 
			"- Check the eTK issuance interface general status\n" + 
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	I32("I.3.2", "Failed to update eVT details with outbound XML before sending eVT", "CONN", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK issuance interface general status\n" + 
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	I33("I.3.3", "eVT failed to transition out of READY or PROCESSING states", "OTHER", "WARNING", "Manually update the eVT state from PROCESSSING or READY to QUEUED, and then re-queue the ticket to re-process it", false),
	I41("I.4.1", "Failed to send eVT to ICBC", "CONN", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check eTK's issuancesvc and primeadapter server logs for connectivity details\n" +
			"- Contact ISB to acquire SSG server log for additional connectivity errors from SSG to ICBC; e.g., password error or certificate expired error", true),
	I42("I.4.2", "Failed to delete eVT file", "OTHER", "WARNING", "Perform the each of the following steps until issue is resolved:\n" + 
			"- Review all errors associated with the ticket number in question\n" + 
			"- Manually delete the file through the file management tool in the support console, if this is a duplicate eVT file\n" +
			"- Contact eTK application support for further investigation", false),
	I43("I.4.3", "Failed to generate valid ICBC XML", "DATA", "INFO", "Inform PRIMECorp regarding the mal-formed eVT XML file", true),
	I44("I.4.4", "eVT issued using unknown MRE version", "DATA", "INFO", "Perform the each of the following steps until issue is resolved:\n" +
			"- Inform PRIMECorp that an unknown MRE was used to issue this ticket\n" + 
			"- Inform the issuing agency regarding the impacted ticket and coordinate cancelling or resending of ticket using correct MRE version\n" +
			"- Schedule and coordinate a ETK Change Request with PRIMECorp, ICBC, CSB and eTK application support to add new MRE version if required", true),
	I45("I.4.5", "Missing issuing / witnessing officer last name", "DATA", "INFO", "Inform PRIMECorp and ICBC regarding the missing data element warning", true),
	I46("I.4.6", "eVT issued using old MRE version", "DATA", "INFO", "Inform PRIMECorp and the issuing agency that an old MRE was used to issue this ticket", true),
	I51("I.5.1", "Failed to update eVT processing state after sending eVT", "OTHER", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK issuance interface general status\n" + 
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	I70("I.7.0", "General issuance reconciliation processing error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	I71("I.7.1", "Issuance reconciliation mismatch error", "DATA", "INFO", "Contact ICBC for further investigation", false),
	
	Q00("Q.0.0", "General payment query error", "OTHER", "WARNING", "Contact RIDE application support for further investigation", false),
	Q11("Q.1.1", "Failed to get payment query response from ICBC", "CONN", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Contact ISB to acquire SSG server log for additional connectivity errors from SSG to ICBC; e.g., password error or SSG downtime,\n" +
			"- Contact RIDE application support for further investigation", false),
	Q21("Q.2.1", "Failed to insert query event into DB", "DATA", "WARNING", "Contact RIDE application support for further investigation", false),
	Q31("Q.3.1", "Failed to send event to producer API", "CONN", "WARNING", "", false),

	
	R00("R.0.0", "General payment receipt processing error", "OTHER", "INFO", "Contact eTK application support for further investigation", false),
	R11("R.1.1", "Failed to get confirmation from ICBC for payment receipt creation", "CONN", "INFO", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK payment interface general status\n" +
			"- Check for eTK configuration changes\n" +
			"- Check the eTK's paymentsvc and icbcadapter components' operational status\n" +
			"- Contact eTK application support for further investigation", false),
	R12("R.1.2", "Bad Payment Receipt Creation Request", "DATA", "INFO", "Contact eTK application support for further investigation", false),
	R21("R.2.1", "Failed to insert receipt event into DB", "OTHER", "INFO", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK payment interface general status\n" +
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	R22("R.2.2", "Duplicate receipt payment request received", "DATA", "WARNING", "Inform PayBC regarding the duplicate receipt payment request", false),
	
	// ticket dispute errors
	D00("D.0.0", "General ticket dispute processing error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	D11("D.1.1", "Ticket dispute parsing error", "DATA", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Review parsing error and contact ICBC for further investigation\n" +
			"- Contact eTK application support for further investigation", true),
	D12("D.1.2", "Database access error", "OTHER", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	D13("D.1.3", "Duplicate dispute request received", "DATA", "WARNING", "Inform ICBC regarding the duplicate dispute request", true),
	D14("D.1.4", "Invalid dispute date", "DATA", "WARNING", "Inform ICBC that the dispute date is either missing or incorrectly formatted", true),
	D21("D.2.1", "jiadapter component communication error", "CONN", "FATAL", "Contact eTK application support for further investigation", false),
	D31("D.3.1", "Ticket dispute parsing error", "DATA", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Review parsing error and contact ICBC for further investigation\n" +
			"- Contact eTK application support for further investigation", true),
	D41("D.4.1", "Dispute package with incorrect ticket data detected. Ticket XML automatically replaced using original ticket data", "DATA", "INFO", "Inform ICBC and CSB regarding the automated data fix", true),
	D42("D.4.2", "Dispute package with incorrect ticket data detected. Original ticket XML not available; data not fixed", "DATA", "INFO", "Inform ICBC and CSB regarding the data error (detected but not fixed)", true),
	D51("D.5.1", "Ticket dispute statute mapping look up error", "DATA", "WARNING", "Contact eTK application support for further investigation", true),
	D61("D.6.1", "Error converting to JUSTIN API data model", "DATA", "WARNING", "Contact eTK application support for further investigation", true),
	D62("D.6.2", "Database access error", "OTHER", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	D71("D.7.1", "JUSTIN Interface communication error", "CONN", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check eTK's disputesvc and jiadapter server logs for connectivity details\n" + 
			"- Contact ISB to acquire SSG server log for additional connectivity errors from SSG to JUSTIN Interface; e.g., password error or SSG downtime", false),
	D80("D.8.0", "General dispute reconciliation processing error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	D81("D.8.1", "ICBC SFTP connectivity error", "CONN", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Manually connect to ICBC SFTP server to confirm connectivity\n" +
			"- Check for eTK configuration changes\n" +
			"- Contact eTK application support for further investigation", false),
	D82("D.8.2", "Dispute reconciliation mismatch error", "DATA", "INFO", "Review specific discrepancies and investigate as required", true),
	D83("D.8.3", "Hub failed to run dispute reconciliation after 5 tries", "CONN", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Manually connect to ICBC SFTP server to confirm connectivity\n" +
			"- Check for eTK configuration changes\n" +
			"- Contact eTK application support for further investigation", false),
	D84("D.8.4", "Dispute record failed to transition out of READY or PROCESSING states", "OTHER", "WARNING", "Manually update the dispute record state from PROCESSSING or READY to QUEUED, and then re-queue the record to re-process it", false),

	D85("D.8.5", "Missing Enforcement field for Dispute", "DATA", "INFO", "Inform Justin regarding the missing data element warning", false),

	//dispute status update errors
	U00("U.0.0", "General dispute status update processing error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	U11("U.1.1", "Dispute status update parsing error", "DATA", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Review parsing error and contact ICBC for further investigation\n" +
			"- Contact eTK application support for further investigation", true),
	U12("U.1.2", "Database access error", "OTHER", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	U13("U.1.3", "Duplicate dispute status update request received", "DATA", "WARNING", "Inform ICBC regarding the duplicate dispute status update request", true),
	U21("U.2.1", "jiadapter component communication error", "CONN", "FATAL", "Contact eTK application support for further investigation", false),
	U31("U.3.1", "Dispute status update parsing error", "DATA", "WARNING", "Inform ICBC regarding the dispute status update message parsing error", true),
	U41("U.4.1", "Error converting to JUSTIN API data model", "DATA", "WARNING", "Contact eTK application support for further investigation", true),
	U42("U.4.2", "Database access error", "OTHER", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	U51("U.5.1", "JUSTIN Interface communication error", "CONN", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check eTK's disputesvc and jiadapter server logs for connectivity details\n" +
			"- Contact ISB to acquire SSG server log for additional connectivity errors from SSG to JUSTIN Interface; e.g., password error or SSG downtime", false),
	U52("U.5.2", "Dispute status update record failed to transition out of READY or PROCESSING states", "OTHER", "WARNING", "Manually update the dispute status update record state from PROCESSSING or READY to QUEUED, and then re-queue the record to re-process it", false),

	// Dispute Finding errors
	F00("F.0.0", "General dispute finding processing error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	F11("F.1.1", "jiadapter component communication error", "CONN", "FATAL", "Contact eTK application support for further investigation", false),
	F12("F.1.2", "JUSTIN Interface not available", "CONN", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK dispute findings interface general status\n" +
			"- Check for eTK configuration changes\n" +
			"- Check the eTK's disputesvc and jiadapter components' operational status\n" +
			"- Contact eTK application support for further investigation", false),
	F13("F.1.3", "JUSTIN Interface communication failure due to configuration error", "CONN", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK dispute findings interface general status\n" +
			"- Check for eTK configuration changes\n" +
			"- Check the eTK's disputesvc and jiadapter components' operational status\n" +
			"- Contact eTK application support for further investigation", false),
	F14("F.1.4", "Error processing the dispute findings data received from Justin Interface", "DATA", "WARNING", "Contact eTK application support for further investigation", false),
	F21("F.2.1", "Database access error", "CONN", "FATAL", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check for general database connectivity error\n" +
			"- Contact eTK application support for further investigation", false),
	F31("F.3.1", "Duplicate findings error", "DATA", "WARNING", "Review the attached duplicate finding records", false),

	// BI events errors
	E00("E.0.0", "Eventing general error", "OTHER", "WARNING", "Contact eTK application support for further investigation", false),
	E11("E.1.1", "Event target communication error", "CONN", "WARNING", "Perform the each of the following steps until issue is resolved:\n" +
			"- Check the eTK eventing interface general status\n" +
			"- Check for eTK configuration changes\n" +
			"- Review event target configuration to ensure the target end point is specified correctly\n" +
			"- Contact eTK application support for further investigation", false);

	/** The code value. */
	private String errorCode;
	
	/** The error summary. */
	private String errorSummary;
	
	/** The error category. */
	private String errorCategory;
	
	/** The error severity. */
	private String errorSeverity;
	
	private String resolutionStep;
	
	private Boolean silentErrorWhileInterfaceResuming;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumErrorCode> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param errorCode the error code
	 * @param errorSummary the error summary
	 * @param errorCategory the error category
	 * @param errorSeverity the error severity
	 */
	private EnumErrorCode(String errorCode, String errorSummary, String errorCategory, String errorSeverity, String resolutionStep, Boolean silentErrorWhileInterfaceResuming) {
		this.errorCode = errorCode;
		this.errorSummary = errorSummary;
		this.errorCategory = errorCategory;
		this.errorSeverity = errorSeverity;
		this.resolutionStep = resolutionStep;
		this.silentErrorWhileInterfaceResuming = silentErrorWhileInterfaceResuming;
	}
	
	static {
		mapCode = new HashMap<String, EnumErrorCode>();
		for (EnumErrorCode v : EnumErrorCode.values()) {
			mapCode.put(v.errorCode, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumErrorCode getEnumfromCodeValue(String codeValue) {
		return mapCode.get(codeValue);
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error summary.
	 *
	 * @return the error summary
	 */
	public String getErrorSummary() {
		return errorSummary;
	}

	/**
	 * Sets the error summary.
	 *
	 * @param errorSummary the new error summary
	 */
	public void setErrorSummary(String errorSummary) {
		this.errorSummary = errorSummary;
	}

	/**
	 * Gets the error category.
	 *
	 * @return the error category
	 */
	public String getErrorCategory() {
		return errorCategory;
	}

	/**
	 * Sets the error category.
	 *
	 * @param errorCategory the new error category
	 */
	public void setErrorCategory(String errorCategory) {
		this.errorCategory = errorCategory;
	}

	/**
	 * Gets the error severity.
	 *
	 * @return the error severity
	 */
	public String getErrorSeverity() {
		return errorSeverity;
	}

	/**
	 * Sets the error severity.
	 *
	 * @param errorSeverity the new error severity
	 */
	public void setErrorSeverity(String errorSeverity) {
		this.errorSeverity = errorSeverity;
	}

	
	/**
	 * Gets the resolution step.
	 *
	 * @return the resolution step
	 */
	public String getResolutionStep() {
		return resolutionStep;
	}

	/**
	 * Sets the resolution step.
	 *
	 * @param resolutionStep the new resolution step
	 */
	public void setResolutionStep(String resolutionStep) {
		this.resolutionStep = resolutionStep;
	}

	/**
	 * Gets the mapcode.
	 *
	 * @return the mapcode
	 */
	public static Map<String, EnumErrorCode> getMapcode() {
		return mapCode;
	}

	/**
	 * Gets the silent error while interface resuming.
	 *
	 * @return the silent error while interface resuming
	 */
	public Boolean getSilentErrorWhileInterfaceResuming() {
		return silentErrorWhileInterfaceResuming;
	}

	/**
	 * Sets the silent error while interface resuming.
	 *
	 * @param silentErrorWhileInterfaceResuming the new silent error while interface resuming
	 */
	public void setSilentErrorWhileInterfaceResuming(Boolean silentErrorWhileInterfaceResuming) {
		this.silentErrorWhileInterfaceResuming = silentErrorWhileInterfaceResuming;
	}
	
	/**
	 * Gets the related interface based on the error code
	 *
	 * @return the interface
	 */
	public EnumInterface getInterface() {
		String errorCodePrefix = null;
		errorCodePrefix = this.getErrorCode().substring(0, 1);
		switch (errorCodePrefix) {
			case "I": return EnumInterface.ICBC_CC;
			case "Q": return EnumInterface.ICBC_QT;
			case "R": return EnumInterface.ICBC_CR;
			case "D": return EnumInterface.JSTN_TD;
			case "U": return EnumInterface.JSTN_SU;
			case "F": return EnumInterface.JSTN_DF;
			case "E": return EnumInterface.EVNT_NT;
		}
		return null;
	}
}
