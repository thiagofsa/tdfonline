package br.com.tfdonline.config;

//alteradodo github na quarta..
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@EnableTransactionManagement
public class HibernateConf {
	@Autowired
     private Environment env;	

	
 	@Bean
	public SessionFactory sessionFactory() {
	      LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
	      lsfb.setDataSource(getDataSource());
	      lsfb.setPackagesToScan("br.com.tfdonline.modelo");
	      
	      //lsfb.setHibernateProperties(hibernateProperties());
	      try {
		     lsfb.afterPropertiesSet();
	      } catch (IOException e) {
		     e.printStackTrace();
	      }
	      return lsfb.getObject();
	}
        @Bean
	public DataSource getDataSource() {
	     BasicDataSource dataSource = new BasicDataSource();
	     dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	     dataSource.setUrl("jdbc:mysql://localhost:3306/tfdonline");
	     dataSource.setUsername("root");
	     dataSource.setPassword("654321abC!");
	     
	     
	     if (dataSource!=null){
	    	 System.out.println("DS Criado com sucessooooo!");
	     }
	     return dataSource;
	}
	@Bean
	public HibernateTransactionManager hibernateTransactionManager(){
	     return new HibernateTransactionManager(sessionFactory());
	}
      
} 
