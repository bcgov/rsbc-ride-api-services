package bcgov.jh.etk.jhetkcommon.dataaccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.AgencyCodeMapsEntity;


@Repository
public interface AgencyCodeMapsRepository extends JpaRepository<AgencyCodeMapsEntity, Long> {

	/**
	 * Find CSB Agency Id
	 */
	@Query(value = "SELECT c.CSB_AGENCY_ID "
			+ "FROM AGENCY_CODE_MAPS c "
			+ "WHERE c.ENFORCEMENT_JURISDICTION_CD = :enforcementJurisdictionCd AND c.ENFORCEMENT_ORG_UNIT_CD = :enforcementOrgUnitCd "
			+ "OR c.ENFORCEMENT_JURISDICTION_CD = :enforcementJurisdictionCd AND c.DEFAULT_CSB_AGENCY IS TRUE "
			+ "ORDER BY c.DEFAULT_CSB_AGENCY ASC ", nativeQuery = true)
    List<String> findCsbAgencyId(@Param("enforcementJurisdictionCd") String enforcementJurisdictionCd, @Param("enforcementOrgUnitCd") String enforcementOrgUnitCd);
}
