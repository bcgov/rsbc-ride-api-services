package bcgov.jh.etk.jhetkcommon.dataaccess.type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFinding;

public class EtkDisputeFindingTypeArray implements java.sql.Array{
	
	private final EtkDisputeFinding[] irRecordArray;
    private final String stringValue;
    
    public EtkDisputeFindingTypeArray(EtkDisputeFinding[] irRecordArray) {
        this.irRecordArray = irRecordArray;
        this.stringValue = arrayToIssuanceReconTypeArray(this.irRecordArray);
 
    }
    
    
    /**
     * Array to issuance recon type array.
     *
     * @param recordArray the record array
     * Sample return:
     * '{{Justin_record_id1,Contravention_no1,Appearance_dt1,Finding_cd1,Finding_desc1},
	 *   {Justin_record_id2,Contravention_no2,Appearance_dt2,Finding_cd2,Finding_desc2}}'
     * @return the string
     */
    public static String arrayToIssuanceReconTypeArray(EtkDisputeFinding[] recordArray) {
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
        		sb.append(recordArray[si].getJustin_record_id() + ",");
        		sb.append(recordArray[si].getContravention_no() + ",");
        		sb.append(recordArray[si].getAppearance_dt() + ",");
        		sb.append(recordArray[si].getFinding_cd() + ",");
        		sb.append(StringUtils.isBlank(recordArray[si].getFinding_desc()) ? null : "\"" + recordArray[si].getFinding_desc() + "\"");
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
