package java2.shoppinglist.config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "java2.shoppinglist")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory) {
        return hibernateEntityManagerFactory.getSessionFactory();
    }

}
