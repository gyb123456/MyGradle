package com;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sys.biz.impl.ConstTypeBizImpl;

@Configuration
@ComponentScan(
	basePackages={"com.sys,com.base"},
	excludeFilters={
		@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
	}
)
//启用注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableTransactionManagement
public class RootConfig {

	/**配置C3P0
	 * */
	@Bean
	public ComboPooledDataSource dataSource() throws PropertyVetoException{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://10.1.30.207:3306/InfoIssue?characterEncoding=utf8");
		cpds.setUser("root");
		cpds.setPassword("root");
		return cpds;
	}
	
	/**Hibernate SessionFactory
	 * */
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource);
		sf.setPackagesToScan(new String[]{"com"});
		Properties props = new Properties();
		props.setProperty("dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		sf.setHibernateProperties(props);
		return sf;
	}
	
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
	@Bean
	public BeanPostProcessor persistenceTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}
