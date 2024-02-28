package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumCardType;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class PaymentCardBankingFeeRateDetail.
 */
@Setter
@Getter
public class PaymentCardBankingFeeRateDetail {
	
	/** The payment card type. */
	private EnumCardType paymentCardType;
		
	/** The payment card desc. */
	private String paymentCardDesc;
	
	/** The bank fee. */
	private double bankFee;
	
	/** The bank txn fee. */
	private double bankTxnFee;
	
	/** The bcm txn fee. */
	private double bcmTxnFee;
	
	/** The pci bcm txn fee. */
	private double pciBcmTxnFee;
	
}
