package io.spring.boot.sample.web.dustjs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class DustSampleController {

    @RequestMapping("/hello")
    public String hello(ModelMap model) {
        model.put("title", "Greeting!!");
        return "hello";
    }

    @RequestMapping("/partial")
    public String partial() {
        return "template3";
    }

}
