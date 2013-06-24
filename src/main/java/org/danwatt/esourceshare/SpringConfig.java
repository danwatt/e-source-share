package org.danwatt.esourceshare;

import static java.lang.Boolean.TRUE;
import static org.hibernate.cfg.Environment.DIALECT;
import static org.hibernate.cfg.Environment.FORMAT_SQL;
import static org.hibernate.cfg.Environment.GENERATE_STATISTICS;
import static org.hibernate.cfg.Environment.HBM2DDL_AUTO;
import static org.hibernate.cfg.Environment.SHOW_SQL;
import static org.hibernate.cfg.Environment.USE_SQL_COMMENTS;
import static org.hibernate.ejb.AvailableSettings.NAMING_STRATEGY;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.danwatt.esourceshare.model.Source;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate3.AbstractSessionFactoryBean;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = SpringConfig.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/views/");
		vr.setSuffix(".jsp");
		return vr;
	}

	@Bean
	public String hibernateDialect() {
		return H2Dialect.class.getName();
	}

	@Bean
	public boolean hibernateShowSql() {
		return true;
	}

	@Bean
	public DataSource dataSource() {
		return new SimpleDriverDataSource(new org.h2.Driver(), "jdbc:h2:mem:processdb;DB_CLOSE_DELAY=-1");
	}
	
	@Bean
	@Autowired
	public AnnotationSessionFactoryBean sessionFactory(String hibernateDialect, boolean hibernateShowSql, DataSource dataSource) {
		AnnotationSessionFactoryBean b = new AnnotationSessionFactoryBean();
		b.setDataSource(dataSource);
		String p = Source.class.getPackage().getName();
		b.setPackagesToScan(new String[] { p });
		Properties properties = jpaProperties(hibernateDialect);
		b.setHibernateProperties(properties);
		HashMap<String, Object> listeners = new HashMap<String, Object>();
		b.setEventListeners(listeners);
		return b;
	}

	private Properties jpaProperties(String hibernateDialect) {
		Properties properties = new Properties();
		properties.setProperty(DIALECT, hibernateDialect);
		properties.setProperty(HBM2DDL_AUTO,"create");
		properties.setProperty("hibernate.generateDdl",TRUE.toString());
		properties.setProperty("hbm2ddl.auto",TRUE.toString());
		properties.setProperty(GENERATE_STATISTICS, TRUE.toString());
		properties.setProperty(SHOW_SQL, TRUE.toString());
		properties.setProperty(FORMAT_SQL, TRUE.toString());
		properties.setProperty(USE_SQL_COMMENTS, TRUE.toString());
		properties.setProperty(NAMING_STRATEGY, ImprovedNamingStrategy.class.getName());
		return properties;
	}

	@Bean
	@Autowired
	public AbstractPlatformTransactionManager transactionManager(AbstractSessionFactoryBean bean) {
		HibernateTransactionManager tm = new HibernateTransactionManager();
		tm.setSessionFactory(bean.getObject());
		return tm;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
