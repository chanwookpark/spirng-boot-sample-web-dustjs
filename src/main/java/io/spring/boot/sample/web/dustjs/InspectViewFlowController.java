package io.spring.boot.sample.web.dustjs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class InspectViewFlowController {

    // STV instance - 1?
    @RequestMapping("/view1")
    public String view1() {
        return "template1";
    }

    // STV instance - 2?
    @RequestMapping("/view2")
    public String view2() {
        return "template2";
    }
}
