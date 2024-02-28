package bcgov.jh.etk.jhetkcommon.model;

import java.util.Map;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumCardType;
import bcgov.jh.etk.jhetkcommon.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankingFeePerTicketType {
	/** The payment txn details. */
	private Map<EnumCardType, BankingFeePerTicketTypeCardType> paymentTxnDetailsPerCardtype = null;
	
	/** The total num of txn permonth. */
	private Map<Integer, Integer> totalNumOfTxnPermonth = null;

	/** The total num of payment permonth. */
	private Map<Integer, Double> totalPaymentPerMonth = null;
	
	/** The total banking fee. */
	private double totalBankingFee = 0;
	
	/** The total PCI compliance fee. */
	private double totalPCIComplianceFee = 0;
	
	/**
	 * Gets the total banking fee.
	 *
	 * @return the total banking fee
	 */
	public String getTotalBankingFeeStr() {
		return StringUtil.doubleToString(totalBankingFee);
	}
	
	/**
	 * Gets the total PCI compliance fee.
	 *
	 * @return the total PCI compliance fee
	 */
	public String getTotalPCIComplianceFeeStr() {
		return StringUtil.doubleToString(totalPCIComplianceFee);
	}
}
