package bcgov.jh.etk.jhetkcommon.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bcgov.jh.etk.jhetkcommon.dataaccess.repository.AgencyCodeMapsRepository;
import bcgov.jh.etk.jhetkcommon.service.AgencyCodeService;

@Component
public class AgencyCodeServiceImpl implements AgencyCodeService {
	
	private static Logger logger = LoggerFactory.getLogger(AgencyCodeServiceImpl.class);

	@Autowired(required = false)
	AgencyCodeMapsRepository agencyCodeMapsRepository;
	
	/**
	 * Find CSB Agency Id
	 */
	public List<String> getCsbAgencyId(String enforcementJurisdictionCd, String enforcementOrgUnitCode) {
		logger.debug("Look for agency info using enforcementJurisdictionCd: {} and enforcementOrgUnitCd: {}", enforcementJurisdictionCd, enforcementOrgUnitCode);
		enforcementOrgUnitCode = enforcementOrgUnitCode == null ? "" : enforcementOrgUnitCode;
		return agencyCodeMapsRepository.findCsbAgencyId(enforcementJurisdictionCd, enforcementOrgUnitCode);
	}
}
