package br.ufg.inf.mds.strangecalendar;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// Procura por componentes a partir desse pacote.
@ComponentScan(basePackages = "br.ufg.inf.mds.strangecalendar")
@EnableTransactionManagement
// Ativa o Spring Data JPA
@EnableJpaRepositories
/**
 * Classe responsável pela configuração do Spring
 *
 * @author Isaias Tavares
 */
public class Config {

	/**
    * Carrega as configuracoes do properties
    *
    * @return as configuracoes do properties
    */
   @Bean
   public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
       PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
       ppc.setLocations(new ClassPathResource("/persistence.properties"));
       return ppc;
   }

}
