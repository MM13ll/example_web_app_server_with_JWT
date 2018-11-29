package uz.course.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.context.support.StandardServletEnvironment;
import uz.course.hibernate.config.PhysicalNamingStrategyImpl;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource(value = {"classpath:application.properties", "classpath:hibernate.properties"})
@PropertySource(name = "hibernate", value = "classpath:hibernate.properties")
@EnableTransactionManagement
public class HibernateConfig implements TransactionManagementConfigurer {

    //@Autowired
    private Environment environment;

    @Autowired
    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(environment.getProperty("db.driver"));
            dataSource.setJdbcUrl(environment.getProperty("db.url"));
            dataSource.setUser(environment.getProperty("db.username"));
            dataSource.setPassword(environment.getProperty("db.password"));
            dataSource.setMinPoolSize(environment.getProperty("hibernate.c3p0.min_size", Integer.class));
            dataSource.setAcquireIncrement(environment.getProperty("hibernate.c3p0.acquire_increment", Integer.class));
            dataSource.setMaxPoolSize(environment.getProperty("hibernate.c3p0.max_size", Integer.class));
            dataSource.setMaxStatements(environment.getProperty("hibernate.c3p0.max_statements", Integer.class));
            dataSource.getProperties().put("hibernate.physical_naming_strategy",
                    environment.getProperty("spring.jpa.properties.hibernate.physical_naming_strategy"));
            dataSource.getProperties().put("hibernate.ejb.naming_strategy",
                    environment.getProperty("spring.jpa.properties.hibernate.physical_naming_strategy"));
        } catch (Exception e) {
        }
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("uz.course.hibernate.domain");
        sessionFactoryBean.setPhysicalNamingStrategy(new PhysicalNamingStrategyImpl());

        Properties properties = (Properties) ((StandardServletEnvironment) environment).getPropertySources().get("hibernate").getSource();
        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    @Lazy
    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = null;
        try {
            transactionManager = new HibernateTransactionManager(sessionFactory());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionManager;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}