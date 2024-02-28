package bcgov.jh.etk.jhetkcommon.model;

import java.util.Map;

import bcgov.jh.etk.jhetkcommon.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class TicketPaymentTxnDetail.
 */

@Setter
@Getter
public class BankingFeePerTicketTypeCardType {
	
	/** The monthly payment tx details. */
	private Map<Integer, MonthlyPaymentTxnDetailPerTicketTypeCardType> monthlyPaymentTxDetailsPerCardtype = null;

	/** The bank fee. */
	private double bankFee = 0;
	
	/** The bank txn fee. */
	private double bankTxnFee = 0;
	
	/** The Prov treasury fee. */
	private double provTreasuryFee = 0;
	
	/** The total banking fee per card type. */
	private double totalBankingFeePerCardType = 0;
	
	public String getBankFeeStr() {
		return StringUtil.doubleToString(bankFee);
	}
	
	public String getBankTxnFeeStr() {
		return StringUtil.doubleToString(bankTxnFee);
	}
	
	public String getProvTreasuryFeeStr() {
		return StringUtil.doubleToString(provTreasuryFee);
	}
	
	public String getTotalBankingFeePerCardTypeStr() {
		return StringUtil.doubleToString(totalBankingFeePerCardType);
	}
}
