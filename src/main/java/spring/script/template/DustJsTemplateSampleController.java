package spring.script.template;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class DustJsTemplateSampleController {

    @RequestMapping("/dust/hello")
    public String hello(ModelMap model) {
        model.put("title", "Greeting!!");
        return "hello";
    }

}
