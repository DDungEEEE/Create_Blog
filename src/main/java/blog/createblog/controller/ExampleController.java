package blog.createblog.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ExampleController {

    @GetMapping("thyemleaf/example")
    public String thymeleafExample(Model model){


        return "q";
    }
}
