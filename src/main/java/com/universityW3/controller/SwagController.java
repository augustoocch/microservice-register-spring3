package com.universityW3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SwagController {

        @RequestMapping("/")
        public ModelAndView index() {
            return new ModelAndView("redirect:/swagger-ui/");
        }

}