package bcgov.jh.etk.jhetkcommon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.dataaccess.dao.EtkDaoService;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkWorkingData;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * The Class EtkService.
 * @author HLiang
 */
@Service
public class WorkingDataService {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(WorkingDataService.class);
	
	/** The vph dao. */
	@Autowired(required = false)
	EtkDaoService etkDao;
	
	/** The Constant WORKING_DATA_LEADER. */
	private static final String WORKING_DATA_LEADER = "leader";
	
	private static final String WORKING_DATA_PREFIX = "data_";

	private Integer currentLockNumber = null;
	
	private Boolean isRegisteredAsLeader = false;
	
	/**
	 * Creates the working data.
	 *
	 * @param dataName the data name
	 * @param dataValue the data value
	 * @param lockNumber the lock number
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean setData(String dataName, String dataValue) {
		try {
			return etkDao.createUpdateWorkingData(ApplicationProperties.componentName, WORKING_DATA_PREFIX + dataName.toLowerCase(), dataValue, this.getCurrentLockNumber());
		} catch (EtkDataAccessException e) {
			logger.error("Failed executing setData, exception: {}", e.toString() + "; " + e.getMessage());
		}
		return null;
	}


	/**
	 * Gets the working data.
	 *
	 * @param dataName the data name
	 * @return the working data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String getData(String dataName) {
		try {
			List<EtkWorkingData> rtnObj = etkDao.getWorkingData(ApplicationProperties.componentName, WORKING_DATA_PREFIX + dataName.toLowerCase());
			if (rtnObj != null && rtnObj.size() == 1 && rtnObj.get(0) != null) {
				return rtnObj.get(0).getDataValue();
			}
		} catch (EtkDataAccessException e) {
			logger.error("Failed executing getData, exception: {}", e.toString() + "; " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Clear the working data.
	 *
	 * @param dataName the data name
	 * @return the working data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean clearData(String dataName) {
		try {
			return etkDao.clearData(ApplicationProperties.componentName, WORKING_DATA_PREFIX + dataName.toLowerCase(), this.getCurrentLockNumber());
		} catch (EtkDataAccessException e) {
			logger.error("Failed executing clearData, exception: {}", e.toString() + "; " + e.getMessage());
		}
		return null;
	}

	
	/**
	 * Checks if the working data exists.
	 *
	 * @param dataName the data name
	 * @return the boolean
	 */
	public Boolean isDataExists(final String dataName) {
		String dataVal = this.getData(dataName);
		if (StringUtils.isNotBlank(dataVal)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * Register as leader pod.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void registerAsLeader() {
		try {
			// add the leader role to the newly selected pod
			addLeaderRoleToPod();
			
			// save the leader pod info to db
			Integer currentLockNumber = etkDao.createUpdateWorkingDataLeadPod(ApplicationProperties.componentName, ApplicationProperties.getPodInfo());
			
			this.currentLockNumber = currentLockNumber;
			
			isRegisteredAsLeader = true;
		} catch (Exception e) {
			logger.error("Failed executing registerAsLeader, exception: {}", e.toString() + "; " + e.getMessage());
			isRegisteredAsLeader = false;
		}
	}
	
	/**
	 * Gets the current lock numbers.
	 *
	 * @return the current lock numbers
	 */
	public Integer getCurrentLockNumber() {
		return this.currentLockNumber;
	}
	
	/**
	 * Am I lead pod.
	 *
	 * @return the boolean
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean isLeader() {
		try {
			return etkDao.amILeadPod(ApplicationProperties.componentName, ApplicationProperties.getPodInfo());
		} catch (EtkDataAccessException e) {
			logger.error("Failed executing isLeader, exception: {}", e.toString() + "; " + e.getMessage());
		}
		return null;
	}

	/**
	 * Checks if is ready.
	 *
	 * @return the boolean
	 */
	public Boolean isReady() {
		return isLeader() && isRegisteredAsLeader;
	}
	
	/**
	 * Gets the leader pod IP address.
	 *
	 * @return the leader
	 */
	public String getLeaderIP() {
		try {
			String dataVal = etkDao.getLeadPodInfo(ApplicationProperties.componentName, WORKING_DATA_LEADER);
			if (StringUtils.isNotBlank(dataVal)) {
				String[] leadPodInfo = dataVal.split(Const.COMMA);
				if (leadPodInfo != null && leadPodInfo.length == 2) {
					return leadPodInfo[1];
				}
			}
		} catch (EtkDataAccessException e) {
			logger.error("Failed getting leader IP, exception: {}", e.toString() + "; " + e.getMessage());
		}
		
		return null;
	}

	/**
	 * Adds the leader role to pod.
	 */
	private void addLeaderRoleToPod() {
		KubernetesClient client = null;
		try {
			Config config = new ConfigBuilder().build();
			client = new DefaultKubernetesClient(config);
	    	Map<String, String> roleLabel = new HashMap<String, String>();
	    	roleLabel.put("role", "leader");
	    	
	    	// https://github.com/fabric8io/kubernetes-client/blob/master/doc/CHEATSHEET.md
	    	// grab the component name from the leader pod
	    	Map<String, String> labelMaps = client.pods().inNamespace(client.getNamespace()).withName(ApplicationProperties.podName).get().getMetadata().getLabels();
	    	String componentName = labelMaps.get("component");
	    	
	    	// Remove the leader role from all pods
	    	for (Pod pod : client.pods().inNamespace(client.getNamespace()).withLabel(componentName).list().getItems()) {
	    		String podName = pod.getMetadata().getName();
	    		client.pods().inNamespace(client.getNamespace()).withName(podName).edit(
				  p -> new PodBuilder(p).editOrNewMetadata().removeFromLabels(roleLabel).endMetadata().build()
				);
	    	}
	        
	    	// add the label 'role' to the new leader pod
	    	client.pods().inNamespace(client.getNamespace()).withName(ApplicationProperties.podName).edit(
			 p -> new PodBuilder(p).editOrNewMetadata().addToLabels(roleLabel).endMetadata().build()
			);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	logger.error("Failed executing addLeaderRoleToPod, exception: {}", e.toString() + "; " + e.getMessage());
	    } finally {
	    	if (client != null) {
	    		client.close();
	    	}
	    }
	}
}
