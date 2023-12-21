package gt.com.tigo.fixed_assets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "gt.com.tigo.fixed_assets.dao.portal",
        entityManagerFactoryRef = "portalEntityManager",
        transactionManagerRef = "portalTransactionManager"
)
public class PortalConfig {

    @Value("${spring.datasource.portal.jndi-name}")
    private String portalJndi;

    @Bean(destroyMethod = "")
    @Primary
    public DataSource portalDataSource() {
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        return lookup.getDataSource(this.portalJndi);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean portalEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(portalDataSource());

        em.setPackagesToScan("gt.com.tigo.fixed_assets.model.portal");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager portalTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(portalEntityManager().getObject());

        return transactionManager;
    }

}
