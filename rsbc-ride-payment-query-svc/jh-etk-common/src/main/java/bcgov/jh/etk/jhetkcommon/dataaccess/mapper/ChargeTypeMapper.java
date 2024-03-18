package bcgov.jh.etk.jhetkcommon.dataaccess.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.ChargeTypeMapsEntity;
import bcgov.jh.etk.jhetkcommon.model.DisputedViolation;

/**
 * The Class ChargeTypeMapper.
 * @author HLiang
 */
public class ChargeTypeMapper {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ChargeTypeMapper.class);
	
	/**
	 * Map to disputed violation.
	 *
	 * @param entity the entity
	 * @return the disputed violation
	 */
	public static DisputedViolation mapToDisputedViolation(final ChargeTypeMapsEntity entity) {
		logger.debug("Map chargeTypeEntity violation object");
		DisputedViolation violation = new DisputedViolation();
		violation.setAct(entity.getCSB_ACT_TXT());
		violation.setCharge(entity.getSHORT_DESC_TXT());
		violation.setClause(entity.getCSB_CLAUSE_TXT());
		violation.setCompressed_section(entity.getICBC_CHARGE_TXT());
		violation.setParagraph(entity.getCSB_PARA_TXT());
		violation.setSection(entity.getCSB_SECT_TXT());
		violation.setSub_paragraph(entity.getCSB_SUBPARA_TXT());
		violation.setSub_section(entity.getCSB_SUBSECT_TXT());
		return violation;
	}
}
