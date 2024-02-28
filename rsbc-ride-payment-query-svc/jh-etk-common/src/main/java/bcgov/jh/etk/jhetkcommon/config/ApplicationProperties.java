/*
 * 
 */
package bcgov.jh.etk.jhetkcommon.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This is the centralized place to manage the properties used in common.
 * @author HLiang
 */
@Component
public class ApplicationProperties {

	/** The service connection timeout in MS. */
	public static int serviceConnectionTimeoutInMS;
    
    /** The service read timeout in MS. */
	public static int serviceReadTimeoutInMS;
    
	/** The email from. */
	public static String emailFrom;
	
	/** The email to. */
	public static String emailTo;
	
	/** The email subject prefix. */
	public static String emailSubjectPrefix;
	
	/** The retry count. */
	public static int retryCount;
    
    /** The wait in MS. */
    public static int waitInMS;
    
    /** The email URL prefix. */
	public static String emailURLPrefix;
	
	/** The swagger base package. */
	public static String swaggerBasePackage;
    
	/** The prime ticket reprocess service uri. */
	public static String PRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI;

	/** The ftp file management uri. */
	public static String FTP_FILE_MANAGEMENT_URI;
	
	/** The mre versions to include. */
	public static String MRE_VERSIONS_TO_INCLUDE;
	
	/** The db version. */
    public static String dbVersion;
        
    /** The pod IP. */
    public static String podIP;
		
	/** The pod name. */
	public static String podName;
	
	/** The component name. */
	public static String componentName;
	
	/**
	 * Gets the pod info.
	 *
	 * @return the pod info
	 */
	public static String getPodInfo() {
		return podName + "," + podIP;
	}
		
	/**
	 * Sets the component name.
	 *
	 * @param componentName the new component name
	 */
	@Value("${spring.application.name:''}")
	private void setComponentName (String componentName) {
		ApplicationProperties.componentName = componentName;
	}
	
	/**
	 * Sets the pod IP.
	 *
	 * @param podIP the new pod IP
	 */
	@Value("${pod.ip:''}")
	private void setPodIP (String podIP) {
		ApplicationProperties.podIP = podIP;
	}
	
	/**
	 * Sets the pod name.
	 *
	 * @param podName the new pod name
	 */
	@Value("${pod.name:''}")
	private void setPodName(String podName) {
		ApplicationProperties.podName = podName;
	}

    /**
     * Sets the db version.
     *
     * @param dbVersion the new db version
     */
    @Value("${db.version:''}")
    private void setDbVersion(String dbVersion) {
    	if (StringUtils.isNoneBlank(dbVersion)) {
    		dbVersion = dbVersion.trim();
    	}
		ApplicationProperties.dbVersion = dbVersion;
	}
    
    /**
     * Sets the service connection timeout in MS.
     *
     * @param serviceConnectionTimeoutInMS the new service connection timeout in MS
     */
    @Value("${service.connection.timeout.ms:0}")
    private void setServiceConnectionTimeoutInMS(int serviceConnectionTimeoutInMS) {
		ApplicationProperties.serviceConnectionTimeoutInMS = serviceConnectionTimeoutInMS;
	}

    /**
     * Sets the service read timeout in MS.
     *
     * @param serviceReadTimeoutInMS the new service read timeout in MS
     */
    @Value("${service.read.timeout.ms:0}")
	private void setServiceReadTimeoutInMS(int serviceReadTimeoutInMS) {
		ApplicationProperties.serviceReadTimeoutInMS = serviceReadTimeoutInMS;
	}

    /**
     * Sets the email from.
     *
     * @param emailFrom the new email from
     */
    @Value("${custom.emailFrom:''}")
	private void setEmailFrom(String emailFrom) {
    	ApplicationProperties.emailFrom = emailFrom;
	}

    /**
     * Sets the email to.
     *
     * @param emailTo the new email to
     */
    @Value("${custom.emailTo:''}")
	private void setEmailTo(String emailTo) {
    	ApplicationProperties.emailTo = emailTo;
	}

    /**
     * Sets the email subject prefix.
     *
     * @param emailSubjectPrefix the new email subject prefix
     */
    @Value("${custom.error.subject.prefix:null}")
	private void setEmailSubjectPrefix(String emailSubjectPrefix) {
    	ApplicationProperties.emailSubjectPrefix = emailSubjectPrefix;
	}

    /**
     * Sets the retry count.
     *
     * @param retryCount the new retry count
     */
    @Value("${service.retry.count:1}")
    private void setRetryCount(int retryCount) {
		ApplicationProperties.retryCount = retryCount;
	}

    /**
     * Sets the wait in MS.
     *
     * @param waitInMS the new wait in MS
     */
    @Value("${service.retry.wait.ms:0}")
    private void setWaitInMS(int waitInMS) {
		ApplicationProperties.waitInMS = waitInMS;
	}

    /**
     * Sets the email URL prefix.
     *
     * @param emailURLPrefix the new email URL prefix
     */
    @Value("${custom.error.url.prefix:''}")
    private void setEmailURLPrefix(String emailURLPrefix) {
		ApplicationProperties.emailURLPrefix = emailURLPrefix;
	}

    /**
     * Sets the swagger base package.
     *
     * @param swaggerBasePackage the new swagger base package
     */
    @Value("${swagger.base.package:''}")
	private void setSwaggerBasePackage(String swaggerBasePackage) {
		ApplicationProperties.swaggerBasePackage = swaggerBasePackage;
	}

    /**
     * Sets the prime adapter ticket reprocess service uri.
     *
     * @param pRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI the new prime adapter ticket reprocess service uri
     */
    @Value("${prime_adapter_ticket_reprocess_service_endpoint_url:}")
    private void setPRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI(String pRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI) {
		ApplicationProperties.PRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI = pRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI;
	}


    /**
     * Sets the ftp file management uri.
     *
     * @param fTP_FILE_MANAGEMENT_URI the new ftp file management uri
     */
    @Value("${ftp_file_management_service_endpoint_url:}")
    private void setFTP_FILE_MANAGEMENT_URI(String fTP_FILE_MANAGEMENT_URI) {
		ApplicationProperties.FTP_FILE_MANAGEMENT_URI = fTP_FILE_MANAGEMENT_URI;
	}
    
    /**
     * Sets the mre versions to include.
     *
     * @param MRE_VERSIONS_TO_INCLUDE the new mre versions to include
     */
    @Value("${mre.versions.to.include:}")
    private void setMRE_VERSIONS_TO_INCLUDE(String MRE_VERSIONS_TO_INCLUDE) {
		ApplicationProperties.MRE_VERSIONS_TO_INCLUDE = MRE_VERSIONS_TO_INCLUDE;
	}
}
