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
        registry.scriptTemplate().prefix("/templates/").suffix(".html");
    }

    @Bean
    public ScriptTemplateConfigurer scriptTemplateConfigurer() throws IOException {
        final ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setRenderFunction("render");
        configurer.setSharedEngine(true);

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        configurer.setEngine(engine);

        // TODO we needs refactoring if add STV.setScripts("folder")
        addScripts(configurer);

        return configurer;
    }

    private void addScripts(ScriptTemplateConfigurer configurer) throws IOException {
        // configurer.setScripts("/dust.js", "/META-INF/resources/webjars/dustjs-linkedin/2.6.1/dust-full.js");
        List<String> scripts = new ArrayList<>();
        scripts.add("/dust.js");
        scripts.add("/META-INF/resources/webjars/dustjs-linkedin/2.6.1/dust-full.js");
        final ClassPathResource dir = new ClassPathResource("/templates/compiled");
        for (File f : dir.getFile().listFiles()) {
            scripts.add("/templates/compiled/" + f.getName());
        }
        configurer.setScripts(scripts.toArray(new String[scripts.size()]));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/templates/**")) {
            registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        }
    }
}
