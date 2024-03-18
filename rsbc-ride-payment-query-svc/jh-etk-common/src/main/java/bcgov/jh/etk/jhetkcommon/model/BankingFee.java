package bcgov.jh.etk.jhetkcommon.model;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumCardType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class BankingFee.
 */
@Setter
@Getter
public class BankingFee {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(BankingFee.class);
	
	/** The bank fee per cardtype. */
	private Map<EnumTicketType, BankingFeePerTicketType> bankFeePerTicketType = null;
	
	private String bankFeeDataCSV;
	
	/**
	 * Gets the bank fee data CSV.
	 *
	 * @return the bank fee data CSV
	 */
	public String getBankFeeDataCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(bankFeeDataCSV)) {
			return bankFeeDataCSV;
		}
		
		if (this.bankFeePerTicketType == null) {
			bankFeeDataCSV = "";
			return bankFeeDataCSV;
		}
		
		StringWriter writer = new StringWriter();
		
		try {
			Iterator it = this.bankFeePerTicketType.entrySet().iterator();
		    while (it.hasNext()) {
		    	
		        Map.Entry bankDataPerTicketType = (Map.Entry)it.next();
		        EnumTicketType ticketType = (EnumTicketType) bankDataPerTicketType.getKey();
		        BankingFeePerTicketType curBankingFeePerTicketType = (BankingFeePerTicketType) bankDataPerTicketType.getValue();
		        /**********************************************************************
		    	 * Write the type of ticket
		    	 **********************************************************************/
		    	writer.append(ticketType.getCodeDescription() + " banking fee report");
		    	writer.append(Const.LINE_SEPARATOR);
		    	writer.append(Const.LINE_SEPARATOR);
		    	
		    	
		    	/*********************************************************************
		    	 * Build first portion of the report: banking monthly txn per cardtype
		    	 *********************************************************************/
		    	
		        // Prepare header for months
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getTotalNumOfTxnPermonth() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getTotalNumOfTxnPermonth().entrySet().iterator();
		        	
		        	// add the empty column
		        	writer.append("");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry monthsEntry = (Entry) it1.next();
		        		Integer month = (Integer) monthsEntry.getKey();
		        		writer.append(convertMonth(String.valueOf(month)));
			    		writer.append(Const.COLUMN_DELIMITER);
			    		writer.append("");
			    		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        
		        // Prepare header for txn per months
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getTotalNumOfTxnPermonth() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getTotalNumOfTxnPermonth().entrySet().iterator();
		        	
		        	// add the empty column
		        	writer.append("Type");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry monthsEntry = (Entry) (Entry) it1.next();
		        		
		        		writer.append("# of txn");
		        		writer.append(Const.COLUMN_DELIMITER);
		        		writer.append("$ total");
			    		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        
		        // Write monthly txn
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		EnumCardType cardType = (EnumCardType)paymentTxnPerCardTypeEntry.getKey();
		        		BankingFeePerTicketTypeCardType bankingFeePerTicketTypeCardType = (BankingFeePerTicketTypeCardType)paymentTxnPerCardTypeEntry.getValue();
		        		
		        		writer.append(cardType.getCodeValue());
		        		writer.append(Const.COLUMN_DELIMITER);
		        		if (bankingFeePerTicketTypeCardType == null) {
		        			writer.append("");
		        			writer.append(Const.COLUMN_DELIMITER);
		        			writer.append("");
		        			writer.append(Const.COLUMN_DELIMITER);
		        		} else {
		        			Iterator it2 = bankingFeePerTicketTypeCardType.getMonthlyPaymentTxDetailsPerCardtype().entrySet().iterator();
		        			while (it2.hasNext()) {
		        				Map.Entry monthlyPaymentTxnDetailPerTicketTypeCardTypeEntry = (Entry) it2.next();
		        				MonthlyPaymentTxnDetailPerTicketTypeCardType monthlyPaymentTxnDetailPerTicketTypeCardType = (MonthlyPaymentTxnDetailPerTicketTypeCardType) monthlyPaymentTxnDetailPerTicketTypeCardTypeEntry.getValue();
		        				if (monthlyPaymentTxnDetailPerTicketTypeCardType != null) {
		        					writer.append(monthlyPaymentTxnDetailPerTicketTypeCardType != null ? String.valueOf(monthlyPaymentTxnDetailPerTicketTypeCardType.getNumOfTxnThisMonth()) : "");
				        			writer.append(Const.COLUMN_DELIMITER);
				        			writer.append(monthlyPaymentTxnDetailPerTicketTypeCardType != null ? monthlyPaymentTxnDetailPerTicketTypeCardType.getTotalPaymentThisMonthStr() : "");
				        			writer.append(Const.COLUMN_DELIMITER);
		        				} else {
			        				writer.append("");
				        			writer.append(Const.COLUMN_DELIMITER);
				        			writer.append("");
				        			writer.append(Const.COLUMN_DELIMITER);
		        				}
		        			}
		        		}
		        		writer.append(Const.LINE_SEPARATOR);
		        	}
		        }
		        
		        // write total txn per month
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getTotalNumOfTxnPermonth() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getTotalNumOfTxnPermonth().entrySet().iterator();
		        	
		        	// add the empty column
		        	writer.append("Total");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry monthsEntry = (Entry) it1.next();
		        		Integer monthlyTxn = (Integer) monthsEntry.getValue();
		        		writer.append(String.valueOf(monthlyTxn));
		        		writer.append(Const.COLUMN_DELIMITER);
		        		Double totalPayment = curBankingFeePerTicketType.getTotalPaymentPerMonth().get((Integer)monthsEntry.getKey());
		        		writer.append(String.valueOf(totalPayment));
			    		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        
		        //write multiple line-seperator
		        writer.append(Const.LINE_SEPARATOR);
		        writer.append(Const.LINE_SEPARATOR);
		        
		        /**********************************************************************
		         * Build 2nd portion of the report: banking fee per cardtype
		         **********************************************************************/
		        // Build the header using cardtypes
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	writer.append("Fees");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		EnumCardType cardType = (EnumCardType)paymentTxnPerCardTypeEntry.getKey();
		        		writer.append(cardType.getCodeValue());
		        		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append("Flat fees");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        // Build the row of Bank fee of each cardtype
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	writer.append("Bank fee");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		BankingFeePerTicketTypeCardType bankingFeePerTicketTypeCardType = (BankingFeePerTicketTypeCardType)paymentTxnPerCardTypeEntry.getValue();
		        		writer.append(bankingFeePerTicketTypeCardType == null ? "" : bankingFeePerTicketTypeCardType.getBankFeeStr());
		        		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append("");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        // Build the row of Bank txn fee of each cardtype
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	writer.append("Bank txn fee");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		BankingFeePerTicketTypeCardType bankingFeePerTicketTypeCardType = (BankingFeePerTicketTypeCardType)paymentTxnPerCardTypeEntry.getValue();
		        		writer.append(bankingFeePerTicketTypeCardType == null ? "" : bankingFeePerTicketTypeCardType.getBankTxnFeeStr());
		        		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append("");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        // Build the row of Prov Treasury fee of each cardtype
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	writer.append("Prov treasury fee");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		BankingFeePerTicketTypeCardType bankingFeePerTicketTypeCardType = (BankingFeePerTicketTypeCardType)paymentTxnPerCardTypeEntry.getValue();
		        		writer.append(bankingFeePerTicketTypeCardType == null ? "" : bankingFeePerTicketTypeCardType.getProvTreasuryFeeStr());
		        		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append("");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        
		        // Build the row of PCI Compliance Fee 
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	writer.append("PCI compliance fee");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		writer.append("");
		        		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append(curBankingFeePerTicketType.getTotalPCIComplianceFeeStr());
		        	writer.append(Const.COLUMN_DELIMITER);
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        // Build the row of total per cardtype
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	writer.append("Total per card type");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		BankingFeePerTicketTypeCardType bankingFeePerTicketTypeCardType = (BankingFeePerTicketTypeCardType)paymentTxnPerCardTypeEntry.getValue();
		        		writer.append(bankingFeePerTicketTypeCardType == null ? "" : bankingFeePerTicketTypeCardType.getTotalBankingFeePerCardTypeStr());
		        		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append(curBankingFeePerTicketType.getTotalPCIComplianceFeeStr());
		        	writer.append(Const.COLUMN_DELIMITER);
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        
		        // Build the row of quarterly total
		        if (curBankingFeePerTicketType != null && curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null) {
		        	Iterator it1 = curBankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().entrySet().iterator();
		        	writer.append("Total");
		        	writer.append(Const.COLUMN_DELIMITER);
		        	while (it1.hasNext()) {
		        		Map.Entry paymentTxnPerCardTypeEntry = (Entry) it1.next();
		        		writer.append("");
		        		writer.append(Const.COLUMN_DELIMITER);
		        	}
		        	writer.append(curBankingFeePerTicketType.getTotalBankingFeeStr());
		        	writer.append(Const.COLUMN_DELIMITER);
		        	writer.append(Const.LINE_SEPARATOR);
		        }
		        writer.append(Const.LINE_SEPARATOR);
		        writer.append(Const.LINE_SEPARATOR);
		        writer.append(Const.LINE_SEPARATOR);
		    }
		    
		    bankFeeDataCSV = writer.toString();
		} catch(Exception ee) {
			logger.error(ee.getMessage());
		} finally {
			try {
				//closing the writer
				writer.close();
			} catch(Exception ee) {
				logger.error(ee.getMessage());
			}
		}
	
		return bankFeeDataCSV;
	}

	/**
	 * Convert month.
	 *
	 * @param qmonth the qmonth. The format of qmonth: YYYYMM; e.g., 202101
	 * @return the string
	 */
	private String convertMonth(String qmonth) {
		String month = null;
		if (qmonth == null) {
			return null;
		}
		String yearpart = qmonth.substring(0, 4);
		String monthpart = qmonth.substring(4, 6);
		switch (monthpart) {
			case "01": month = "JAN"; break; 
			case "02": month = "FEB"; break;
			case "03": month = "MAR"; break;
			case "04": month = "APR"; break;
			case "05": month = "MAY"; break;
			case "06": month = "JUN"; break;
			case "07": month = "JUL"; break;
			case "08": month = "AUG"; break;
			case "09": month = "SEP"; break;
			case "10": month = "OCT"; break;
			case "11": month = "NOV"; break;
			case "12": month = "DEC"; break;
		}
		return yearpart + "-" + month;
	}
	
	private void getValue() {
		// TODO Auto-generated method stub
		
	}
	
}
