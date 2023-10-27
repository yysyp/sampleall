package ps.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class Fst2ndDatasourceConfiguration {


    @Bean
    @ConfigurationProperties("spring.datasource.fst")
    public DataSourceProperties fstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.snd")
    public DataSourceProperties sndDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.fst.hikari")
    public DataSource fstDataSource() {
        return fstDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSource sndDataSource() {
        return sndDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    JdbcTemplate sndJdbcTemplate(@Qualifier("sndDataSource") DataSource sndDataSource) {
        return new JdbcTemplate(sndDataSource);
    }
}
