package spring.script.template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;

/**
 * @author chanwook
 */
@Configuration
public class ScriptTemplateConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.scriptTemplate();
    }

    @Bean
    public ScriptTemplateConfigurer scriptTemplateConfigurer() {
        final ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        configurer.setScripts("dust/dust.js", "/META-INF/resources/webjars/dustjs-linkedin/2.6.1/dust-full.js");
        configurer.setRenderFunction("render");
        return configurer;
    }
}
