package bcgov.jh.etk.jhetkcommon.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import com.zaxxer.hikari.HikariDataSource;


/**
 * The Class DBConfiguration.
 * @author HLiang
 */
@Configuration
@EnableJdbcRepositories
public class DBConfiguration {
        
    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource", name = "jdbc-url")
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource() {
    	return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    
    /**
     * Jdbc template.
     *
     * @return the jdbc operations
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.datasource", name = "jdbc-url")
    JdbcTemplate jdbcTemplate() { 
        return new JdbcTemplate(dataSource());
    }
}
