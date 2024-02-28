package bcgov.jh.etk.jhetkcommon.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.ChargeTypeMapsEntity;
import bcgov.jh.etk.jhetkcommon.dataaccess.mapper.ChargeTypeMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.repository.ChargeTypeMapsRepository;
import bcgov.jh.etk.jhetkcommon.model.DisputedViolation;
import bcgov.jh.etk.jhetkcommon.service.ChargeTypeService;

/**
 * The Class ChargeTypeServiceImpl.
 * @author HLiang
 */
@Component
public class ChargeTypeServiceImpl implements ChargeTypeService {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ChargeTypeServiceImpl.class);

	/** The charge type maps repository. */
	@Autowired(required = false)
	ChargeTypeMapsRepository chargeTypeMapsRepository;
	
	/**
	 * Find charge types by ICBC charge txt.
	 *
	 * @param icbcChargeCode the icbc charge code
	 * @return the disputed violation
	 */
	public DisputedViolation findChargeTypesByICBCChargeTxt(final String icbcChargeCode) {
		logger.debug("Look for charge details for compressed code: {}", icbcChargeCode);
		List<ChargeTypeMapsEntity> chargeTypes = chargeTypeMapsRepository.findChargeTypesByICBCChargeTxt(icbcChargeCode);
		if (chargeTypes == null || chargeTypes.size() < 1) {
			return null;
		}
		return ChargeTypeMapper.mapToDisputedViolation(chargeTypes.get(0));
	}
}
