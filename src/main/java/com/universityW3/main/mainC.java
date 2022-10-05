package com.universityW3.main;


import com.universityW3.repository.UserServiceImpl;
import com.universityW3.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class mainC {
    

	public static void main(String[] args) {
		SpringApplication.run(mainC.class, args);
	}
}

  /*  
    public void main (String[] args) {   
        
        userImpl = new UserServiceImpl();
        userImpl.save(new User("Juan", "Ochoa", "juanocho@hotmail", "22234355", "Argentina","Buenos Aires", 1102));
        List<User> userList = userImpl.findAll();
        
        for(User u : userList) {
            return u;
        } 
        
        return null;
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserServiceImpl userImpl;
    
    private static final Logger log = LoggerFactory.getLogger(mainC.class);



*/

