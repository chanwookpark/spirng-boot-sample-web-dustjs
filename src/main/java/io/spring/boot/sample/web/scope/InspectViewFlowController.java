package io.spring.boot.sample.web.scope;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chanwook
 */
@Controller
public class InspectViewFlowController {

    // STV instance - 1?
    @RequestMapping("/dust/view1")
    public String view1() {
        return "template1";
    }

    // STV instance - 2?
    @RequestMapping("/dust/view2")
    public String view2() {
        return "template2";
    }
}
