package bcgov.jh.etk.jhetkcommon.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * The Class AgencyCodeService.

 */
@Service
public interface AgencyCodeService {
	
	/**
	 * Find CSB Agency Id by enforcementJurisdictionCd and enforcementOrgUnitCd
	 * @return CSB Agency Id
	 */
	public List<String> getCsbAgencyId(String enforcementJurisdictionCd, String enforcementOrgUnitCode);
}
