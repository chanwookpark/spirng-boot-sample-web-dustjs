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
        registry.scriptTemplate().prefix("/dust/").suffix(".html");
    }

    @Bean
    public ScriptTemplateConfigurer scriptTemplateConfigurer() throws IOException {
        final ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setRenderFunction("render");
        configurer.setSharedEngine(true);

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        configurer.setEngine(engine);

        // TODO we needs refactoring if add STV.setScripts("folder")
        // configurer.setScripts("/scripts/dust.js", "/META-INF/resources/webjars/dustjs-linkedin/2.6.1/dust-full.js");
        List<String> scripts = new ArrayList<>();
        scripts.add("/scripts/dust.js");
        scripts.add("/META-INF/resources/webjars/dustjs-linkedin/2.6.1/dust-full.js");
        final ClassPathResource dir = new ClassPathResource("/dust/compiled");
        for (File f : dir.getFile().listFiles()) {
            scripts.add("/dust/compiled/" + f.getName());
        }
        configurer.setScripts(scripts.toArray(new String[scripts.size()]));

        return configurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/dust/**")) {
            registry.addResourceHandler("/dust/**").addResourceLocations("classpath:/dust/");
        }

        if (!registry.hasMappingForPattern("/scripts/**")) {
            registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/scripts/");
        }
    }
}
