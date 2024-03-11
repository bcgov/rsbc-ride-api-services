package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MonthlyPaymentTxnDetailPerTicketTypeCardType {
	
	/** The num of txn this month. */
	private int numOfTxnThisMonth = 0;
	
	/** The total payment this month. */
	private double totalPaymentThisMonth = 0;
	
	/**
	 * Gets the total payment this month.
	 *
	 * @return the total payment this month
	 */
	public String getTotalPaymentThisMonthStr() {
		return StringUtil.doubleToString(totalPaymentThisMonth);
	}
	
}
