package com.jh.dyamicDataSource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.jh.dyamicDataSource.dao.pgsql", sqlSessionTemplateRef  = "pgsqlSqlSessionTemplate")
public class DataSourcePgqlConfig {
    @Bean(name = "pgsqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pgsql")
    public DataSource pgsqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pgsqlSqlSessionFactory")
    public SqlSessionFactory pgsqlSqlSessionFactory(@Qualifier("pgsqlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/pgsql/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "pgsqlTransactionManager")
    public DataSourceTransactionManager pgsqlTransactionManager(@Qualifier("pgsqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "pgsqlSqlSessionTemplate")
    public SqlSessionTemplate pgsqlSqlSessionTemplate(@Qualifier("pgsqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
