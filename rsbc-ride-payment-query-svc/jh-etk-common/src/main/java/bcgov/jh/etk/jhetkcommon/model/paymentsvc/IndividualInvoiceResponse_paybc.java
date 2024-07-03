package bcgov.jh.etk.jhetkcommon.model.paymentsvc;


import lombok.Getter;
import lombok.Setter;

/**
 * The Class IndividualInvoiceResponse.
 */
@Setter
@Getter
public class IndividualInvoiceResponse_paybc {
	
	/** The invoice number. */
	private String invoice_number;
	
	/** The pbc ref number. */
	private String pbc_ref_number;
	
	/** The party number. */
	private String party_number;
	
	/** The party name. */
	private String party_name;
	
	/** The party surname. */
	private String party_surname;
	
	/** The party given name 1. */
	private String party_given_name1;
	
	/** The given name 2. */
	private String party_given_name2;
	
	/** The account number. */
	private String account_number;
	
	/** The site number. */
	private String site_number;	
	
	/** The cust trx type. */
	private String cust_trx_type;
	
	/** The term due date. */
	private String term_due_date;	
	
	/** The total. */
	private double total;
	
	/** The amount due. */
	private double amount_due;
	
	/** The attribute 1. */
	private String attribute1;
	
	/** The attribute 2. */
	private String attribute2;
	
	/** The attribute 3. */
	private String attribute3;
	
	/** The attribute 4. */
	private String attribute4;

	/** The Form number */
	private String evt_form_number;

	/** The act. */
	private String act;

	/** The section number. */
	private String section_number;

}
