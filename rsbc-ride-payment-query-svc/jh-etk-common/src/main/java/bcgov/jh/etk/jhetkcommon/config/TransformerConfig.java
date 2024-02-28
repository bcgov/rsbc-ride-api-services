package bcgov.jh.etk.jhetkcommon.config;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class TransformerConfig {

	/** The transformation XSLMCOT. */
	@Value("classpath:/canonical_transformationMCOT.xsl")
	private Resource transformationXSLMCOT;

	/** The transformation XSLOCOT. */
	@Value("classpath:/canonical_transformationOCOT.xsl")
	private Resource transformationXSLOCOT;
	
	/**
	 * Transformer MCOT.
	 *
	 * @return the transformer
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TransformerConfigurationException the transformer configuration exception
	 * @throws TransformerFactoryConfigurationError the transformer factory configuration error
	 */
	@Bean
	public Transformer transformerMCOT() throws IOException, TransformerConfigurationException, TransformerFactoryConfigurationError {
		TransformerFactory factory = TransformerFactory.newInstance();
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		StreamSource style = new StreamSource(transformationXSLMCOT.getInputStream());

		return factory.newTransformer(style);
	}

	/**
	 * Transformer OCOT.
	 *
	 * @return the transformer
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TransformerConfigurationException the transformer configuration exception
	 * @throws TransformerFactoryConfigurationError the transformer factory configuration error
	 */
	@Bean
	public Transformer transformerOCOT() throws IOException, TransformerConfigurationException, TransformerFactoryConfigurationError
	{
		TransformerFactory factory = TransformerFactory.newInstance();
		factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
		StreamSource style = new StreamSource(transformationXSLOCOT.getInputStream());

		return factory.newTransformer(style);
	}
	
}