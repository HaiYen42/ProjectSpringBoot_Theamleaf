package chinh.nguyen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView("admin/index");
        return modelAndView;
    }
    @GetMapping("/error.404")
    public ModelAndView error(){
        return new ModelAndView("/error.404");
    }
}
