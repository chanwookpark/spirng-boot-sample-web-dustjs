package io.spring.boot.sample.web.dustjs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chanwook
 */
@Configuration
public class ScriptTemplateConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.scriptTemplate().prefix("/static/templates/").suffix(".html");
    }

    @Bean
    public ScriptTemplateConfigurer scriptTemplateConfigurer() throws IOException {
        final ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setRenderFunction("render");
        configurer.setSharedEngine(true);

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        configurer.setEngine(engine);

        addScripts(configurer);

        return configurer;
    }

    private void addScripts(ScriptTemplateConfigurer configurer) throws IOException {
        List<String> scripts = new ArrayList<>();
        scripts.add("/static/dust.js");
        scripts.add("/static/polyfill.js");
        scripts.add("/META-INF/resources/webjars/dustjs-linkedin/2.6.1/dust-full.js");

        // TODO we needs refactoring if add STV.setScripts("folder")
        final ClassPathResource dir = new ClassPathResource("/static/templates/compiled");
        for (File f : dir.getFile().listFiles()) {
            scripts.add("/static/templates/compiled/" + f.getName());
        }
        configurer.setScripts(scripts.toArray(new String[scripts.size()]));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/static/templates/**")) {
            registry.addResourceHandler("/static/templates/**").addResourceLocations("classpath:/static/templates/");
        }
    }
}
