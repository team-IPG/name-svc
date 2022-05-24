package org.ipg.namesvc.cfg;

//XXX: RETIRED
//@Configuration
//@EnableYsqlRepositories(basePackageClasses = EmployeeRepository.class)
//public class YsqlDbConfig extends AbstractYugabyteJdbcConfiguration {
public class YsqlDbConfig {

	/**
	 *  This DB no longer exists
	 *
	 * @return a YGB datasource
	 */

//	@Bean
//	DataSource dataSource() {
//		String hostName = "20.119.89.195";
//		String port = "5433";
//		Properties poolProperties = new Properties();
//		poolProperties.setProperty("dataSourceClassName", "com.yugabyte.ysql.YBClusterAwareDataSource");
//		poolProperties.setProperty("maximumPoolSize", String.valueOf(5));
//		poolProperties.setProperty("dataSource.serverName", hostName);
//		poolProperties.setProperty("dataSource.portNumber", port);
//		poolProperties.setProperty("dataSource.databaseName", "yugabyte");
//		poolProperties.setProperty("dataSource.user", "yugabyte");
//		//poolProperties.setProperty("dataSource.loadBalance", "false"); //LB is not working
//		poolProperties.setProperty("dataSource.additionalEndpoints",
//				"20.62.193.167:5433,20.121.115.88:5433");
//		HikariConfig hikariConfig = new HikariConfig(poolProperties);
//		hikariConfig.setMaxLifetime(60000); // only let connecitons sit idle for 60 seconds
//		return new HikariDataSource(hikariConfig);
//	}
//
//	@Bean
//	JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//
//	@Bean
//	NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
//		return new NamedParameterJdbcTemplate(dataSource);
//	}
//
//	@Bean
//	TransactionManager transactionManager(DataSource dataSource) {
//		return new YugabyteTransactionManager(dataSource);
//	}

}