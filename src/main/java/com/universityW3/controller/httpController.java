
package com.universityW3.controller;


import com.universityW3.model.User;
import com.universityW3.repository.UserServiceImpl;
import javax.enterprise.inject.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class httpController {
    
    
    @Autowired
    UserServiceImpl serviceLayer;

    @RequestMapping (value= "/login", method = RequestMethod.POST)
    public String loginInfo (@ModelAttribute User user, BindingResult errors, Model model) {
      serviceLayer.findByEmail(user);
      return null;  
    }
    
    @RequestMapping (value = "/register", method = RequestMethod.POST)
    public String registerInfo (@ModelAttribute User user, BindingResult errors, Model model){
        serviceLayer.save(user);
        return null;
    }

}
