package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class UpdateTicketDataStoredProc.
 * @author HLiang
 */
@Service
public class UpdateTicketDataStoredProc extends StoredProcedureBase {

	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_UPDATE_TICKET_DATA";
	
	/** The Constant PARAM_IN_TICKET_ID. */
	private static final String PARAM_IN_TICKET_ID = "P_TICKET_ID";
	
	/** The Constant PARAM_IN_OUTBOUND_XML. */
	private static final String PARAM_IN_OUTBOUND_XML = "P_OUTBOUND_XML";
	
	/**
	 * Gets the stored proc name.
	 *
	 * @return the stored proc
	 */
	@Override
	protected String getStoredProcName() {
		return NAME_STORE_PROCEDURE;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the sql parameter[]
	 */
	@Override
	protected SqlParameter[] getParameters() {
		SqlParameter pTicketID = new SqlParameter(PARAM_IN_TICKET_ID, Types.INTEGER);
		SqlParameter pOutboundXml = new SqlParameter(PARAM_IN_OUTBOUND_XML, Types.VARCHAR);
		SqlParameter[] paramArray = {pTicketID, pOutboundXml};
		return paramArray;
	}

}
