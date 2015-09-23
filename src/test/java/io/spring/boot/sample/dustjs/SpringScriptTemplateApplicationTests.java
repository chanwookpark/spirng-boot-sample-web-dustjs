package io.spring.boot.sample.dustjs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringScriptTemplateApplication.class)
@WebAppConfiguration
public class SpringScriptTemplateApplicationTests {

	@Test
	public void contextLoads() {
	}

}
