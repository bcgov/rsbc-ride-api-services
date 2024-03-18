package bcgov.jh.etk.jhetkcommon.service;

import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.model.DisputedViolation;

/**
 * The Class ChargeTypeService.
 * @author HLiang
 */
@Service
public interface ChargeTypeService {

	/**
	 * Find charge types by ICBC charge txt.
	 *
	 * @param icbcChargeCode the icbc charge code
	 * @return the disputed violation
	 */
	public DisputedViolation findChargeTypesByICBCChargeTxt(final String icbcChargeCode);
}
