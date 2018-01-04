package org.ccts.balancingact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
@Import(PersistenceConfig.class)
@EnableScheduling
@ComponentScans({
    @ComponentScan(basePackages = "org.ccts.balancingact.task")
})
public class RootConfig {
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreemarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
        factoryBean.setTemplateLoaderPath("classpath:/reports/");

        return factoryBean;
    }
}
