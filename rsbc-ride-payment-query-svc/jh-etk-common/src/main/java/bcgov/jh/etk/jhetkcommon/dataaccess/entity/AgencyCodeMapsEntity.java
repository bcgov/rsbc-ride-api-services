package bcgov.jh.etk.jhetkcommon.dataaccess.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENCY_CODE_MAPS")
public class AgencyCodeMapsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAP_ID")
	private Long mapId;

	@Column(name = "ENFORCEMENT_JURISDICTION_CD")
	private String enforcementJurisdictionCd;

	@Column(name = "CSB_AGENCY_ID")
	private String csbAgencyId;

	@Column(name = "ENFORCEMENT_ORG_UNIT_CD")
	private String enforcementOrgUnitCd;
	
	@Column(name = "DEFAULT_CSB_AGENCY")
	private boolean isDefaultCsbAgency;

	public Long getMapId() {
		return mapId;
	}

	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}

	public String getEnforcementJurisdictionCd() {
		return enforcementJurisdictionCd;
	}

	public void setEnforcementJurisdictionCd(String enforcementJurisdictionCd) {
		this.enforcementJurisdictionCd = enforcementJurisdictionCd;
	}

	public String getCsbAgencyId() {
		return csbAgencyId;
	}

	public void setCsbAgencyId(String csbAgencyId) {
		this.csbAgencyId = csbAgencyId;
	}

	public String getEnforcementOrgUnitCd() {
		return enforcementOrgUnitCd;
	}

	public void setEnforcementOrgUnitCd(String enforcementOrgUnitCd) {
		this.enforcementOrgUnitCd = enforcementOrgUnitCd;
	}

	public boolean isDefaultCsbAgency() {
		return isDefaultCsbAgency;
	}

	public void setDefaultCsbAgency(boolean isDefaultCsbAgency) {
		this.isDefaultCsbAgency = isDefaultCsbAgency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mapId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgencyCodeMapsEntity other = (AgencyCodeMapsEntity) obj;
		return Objects.equals(mapId, other.mapId);
	}

	@Override
	public String toString() {
		return "AgencyCodeMapsEntity [mapId=" + mapId + ", enforcementJurisdictionCd=" + enforcementJurisdictionCd
				+ ", csbAgencyId=" + csbAgencyId + ", enforcementOrgUnitCd=" + enforcementOrgUnitCd
				+ ", isDefaultCsbAgency=" + isDefaultCsbAgency + "]";
	}
}