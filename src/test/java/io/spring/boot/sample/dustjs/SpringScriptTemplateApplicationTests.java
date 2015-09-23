package io.spring.boot.sample.dustjs;

import io.spring.boot.sample.ScriptTemplateApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ScriptTemplateApplication.class)
@WebAppConfiguration
public class SpringScriptTemplateApplicationTests {

	@Test
	public void contextLoads() {
	}

}
