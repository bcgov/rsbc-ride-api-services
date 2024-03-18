package bcgov.jh.etk.jhetkcommon.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bcgov.jh.etk.jhetkcommon.model.PathConst;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * The Class SwaggerConfig.
 * @author HLiang
 */
@Configuration
public class SwaggerConfig {
	
   
    /**
     * Product api.
     *
     * @return the docket
     */
    @Bean
    @ConditionalOnProperty(prefix = "swagger", name = "base.package")
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(ApplicationProperties.swaggerBasePackage))
                //.paths(PathSelectors.any())
                //.paths(regex("/dispute.*"))
                //.paths(includePath("ticket-dispute"))
                //.paths(Predicates.not(PathSelectors.regex(PathConst.PATH_PING_REQUEST)))
                //.paths(Predicates.not(PathSelectors.regex(PathConst.PATH_TEST_REQUEST)))
                //.paths(Predicates.not(PathSelectors.regex(PathConst.PATH_READY_REQUEST)))
                .build();
             
    }
}