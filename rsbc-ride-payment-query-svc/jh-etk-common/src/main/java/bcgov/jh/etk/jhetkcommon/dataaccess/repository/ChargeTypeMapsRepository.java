package bcgov.jh.etk.jhetkcommon.dataaccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.ChargeTypeMapsEntity;


/**
 * The Interface ChargeTypeMapsRepository.
 * @author HLiang
 */
@Repository
public interface ChargeTypeMapsRepository extends JpaRepository<ChargeTypeMapsEntity, Long> {

	/**
	 * Find charge types by ICBC charge txt.
	 *
	 * @param icbcChargeCode the icbc charge text
	 * @return the list
	 */
	@Query("SELECT c FROM ChargeTypeMapsEntity c WHERE c.ICBC_CHARGE_TXT = :icbcChargeCode")
    List<ChargeTypeMapsEntity> findChargeTypesByICBCChargeTxt(@Param("icbcChargeCode") String icbcChargeCode);
}
