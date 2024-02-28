package bcgov.jh.etk.jhetkcommon.util;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bcgov.jh.etk.jhetkcommon.model.Const;

/**
 * The Class StringUtil.
 */
public class StringUtil {
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(StringUtil.class); 
	
	private static ObjectMapper objectMapper = null;
	
	/**
	 * Object to json string.
	 *
	 * @param inObj the in obj
	 * @return the string
	 */
	public static synchronized String objectToJsonString(Object inObj) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(inObj);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			return "";
		}
	}
	
	/**
	 * Json string to object.
	 * @param <T>
	 *
	 * @param jsonString the json string
	 * @return the object
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@SuppressWarnings("unchecked")
	public static synchronized <T> Object jsonStringToObject(String jsonString, Class<?> T) throws IOException {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		
		if (StringUtils.isNotBlank(jsonString)) {
        	// convert JSON string to object
			return (T)objectMapper.readValue(jsonString, T);
        }
		return null;
	}
	
	/**
	 * Checks if a property value is configured.
	 *
	 * @param propertyValue the property value
	 * @return true, if is property value configured
	 */
	public static boolean isPropertyValueConfigured(String propertyValue) {
		if (StringUtils.isBlank(propertyValue) || Const.VALUE_NOT_SET.equals(propertyValue)) {
			return false;
		}
		return true;
	}
	
	/** The Constant df. */
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public static String doubleToString(double inVal) {
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(inVal);
	}
}
