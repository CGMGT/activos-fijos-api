package gt.com.tigo.fixed_assets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "gt.com.tigo.fixed_assets.dao.ebs",
        entityManagerFactoryRef = "assetEntityManager",
        transactionManagerRef = "assetTransactionManager"
)
public class AssetConfig {

    @Value("${spring.datasource.assets.jndi-name}")
    private String assetsJndi;

    @Bean(destroyMethod = "")
    public DataSource assetsDataSource() {
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        return lookup.getDataSource(this.assetsJndi);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean assetEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(assetsDataSource());

        em.setPackagesToScan("gt.com.tigo.fixed_assets.model.ebs");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    public PlatformTransactionManager assetTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(assetEntityManager().getObject());

        return transactionManager;
    }

}
