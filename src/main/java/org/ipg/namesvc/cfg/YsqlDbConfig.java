package org.ipg.namesvc.cfg;

import com.yugabyte.data.jdbc.datasource.YugabyteTransactionManager;
import com.yugabyte.data.jdbc.repository.config.AbstractYugabyteJdbcConfiguration;
import com.yugabyte.data.jdbc.repository.config.EnableYsqlRepositories;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.ipg.namesvc.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableYsqlRepositories(basePackageClasses = EmployeeRepository.class)
public class YsqlDbConfig extends AbstractYugabyteJdbcConfiguration {

	/**
	 *  NOTE -- this is for a hackathon, if this were real we'd externalize these
	 *  and managed credentials as secrets, etc.
	 *
	 *  This DB probably no longer exists by the time you're reading this message.
	 *
	 * @return a YGB datasource
	 */
	@Bean
	DataSource dataSource() {
		String hostName = "20.119.89.195";
		String port = "5433";
		Properties poolProperties = new Properties();
		poolProperties.setProperty("dataSourceClassName", "com.yugabyte.ysql.YBClusterAwareDataSource");
		poolProperties.setProperty("maximumPoolSize", String.valueOf(5));
		poolProperties.setProperty("dataSource.serverName", hostName);
		poolProperties.setProperty("dataSource.portNumber", port);
		poolProperties.setProperty("dataSource.databaseName", "yugabyte");
		poolProperties.setProperty("dataSource.user", "yugabyte");
		poolProperties.setProperty("dataSource.password", "Hackathon22!");
		//poolProperties.setProperty("dataSource.loadBalance", "false"); //LB is not working
		poolProperties.setProperty("dataSource.additionalEndpoints",
				"20.62.193.167:5433,20.121.115.88:5433");
		HikariConfig hikariConfig = new HikariConfig(poolProperties);
		hikariConfig.setMaxLifetime(60000); // only let connecitons sit idle for 60 seconds
		return new HikariDataSource(hikariConfig);
	}

	@Bean
	JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	TransactionManager transactionManager(DataSource dataSource) {
		return new YugabyteTransactionManager(dataSource);
	}

}