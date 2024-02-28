package bcgov.jh.etk.jhetkcommon.dataaccess.type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import bcgov.jh.etk.jhetkcommon.model.PrimeIssuanceReconRecord;

public class IssuanceReconTypeArray implements java.sql.Array{
	
	private final PrimeIssuanceReconRecord[] irRecordArray;
    private final String stringValue;
    
    public IssuanceReconTypeArray(PrimeIssuanceReconRecord[] irRecordArray) {
        this.irRecordArray = irRecordArray;
        this.stringValue = arrayToIssuanceReconTypeArray(this.irRecordArray);
 
    }
    
    
    /**
     * Array to issuance recon type array.
     *
     * @param recordArray the record array
     * Sample return:
     * '{{TICKET_NO1,PRIME_SERVER_SENT_DTM1,TICKET_FILE_NAME1,TICKET_STATUS1,PRIME_SERVER_NAME1,RECON_FILE_NAME1},
	 *   {TICKET_NO2,PRIME_SERVER_SENT_DTM2,TICKET_FILE_NAME2,TICKET_STATUS2,PRIME_SERVER_NAME2,RECON_FILE_NAME2}}'
     * @return the string
     */
    public static String arrayToIssuanceReconTypeArray(PrimeIssuanceReconRecord[] recordArray) {
    	final int arrayLength;
        if ( recordArray == null || (arrayLength = recordArray.length) == 0) {
            return null;
        } 
        
        //Construct the string
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int si = 0; si < arrayLength; si++) {
        	if (recordArray[si] != null) {
        		sb.append("{");
        		sb.append(recordArray[si].getTicketNO() + ",");
        		sb.append(recordArray[si].getPrimeServerSentDTM() + ",");
        		sb.append(recordArray[si].getTicketFileName() + ",");
        		sb.append(recordArray[si].getTicketStatus() + ",");
        		sb.append(recordArray[si].getPrimeServerName() + ",");
        		sb.append(recordArray[si].getReconFileName());
        		sb.append("}");
        	}
        	if (si < arrayLength - 1) {
        		sb.append(",");
        	}
        }
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return stringValue;
    }
    
	@Override
	public void free() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getArray() throws SQLException {
		return irRecordArray == null ? null : Arrays.copyOf(irRecordArray, irRecordArray.length);
	}

	@Override
	public Object getArray(Map<String, Class<?>> arg0) throws SQLException {
		return getArray();
	}

	@Override
	public Object getArray(long index, int count) throws SQLException {
		return irRecordArray == null ? null : Arrays.copyOfRange(irRecordArray, (int)index, (int)index + count );
	}

	@Override
	public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException {
        return getArray(index, count);
    }

	@Override
	public int getBaseType() throws SQLException {
		return java.sql.Types.JAVA_OBJECT;
	}

	@Override
	public String getBaseTypeName() throws SQLException {
		return "text";
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ResultSet getResultSet(Map<String, Class<?>> arg0) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ResultSet getResultSet(long arg0, int arg1) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ResultSet getResultSet(long arg0, int arg1, Map<String, Class<?>> arg2) throws SQLException {
		throw new UnsupportedOperationException();
	}

}
