package com.universityW3.controller;

import com.universityW3.model.User;
import com.universityW3.repository.UserServInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class loginController {
    
    @Autowired
    private UserServInterface user;
    
    @PostMapping("/login") 
    public String login (@ModelAttribute (name = "user") User usrLogin, Model m){
       
      //String password = usrLogin.getPassword();
      //String usr = usrLogin.getEmail();  
      
      if (user.findByEmail(usrLogin) != null) {
          
          return "logSucces";
      }
        return "logSucces";
    }
    
}
