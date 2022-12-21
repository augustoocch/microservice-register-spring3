package com.universityW3.controller;

import com.universityW3.model.Admin;
import com.universityW3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.TreeSet;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value="/validate-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> loginRequest(@RequestBody Admin admin) {
        if (adminService.findByMat(admin.getMat()) != null) {
            if (admin.getPassword().equals((adminService.findByMat(admin.getMat())).getPassword())) {
                return new ResponseEntity<String>("Administrator finded properly, credentials Ok!! Admin P.ID: " + admin.getMat(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<String>("Credentials not accepted!!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<String>("Credentials not accepted!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/new-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> newAdmin(@RequestBody Admin admin) {
        if(adminService.findByMat(admin.getMat()) != null) {
            return new ResponseEntity<String>("Administrator already registered!!", HttpStatus.BAD_REQUEST);
        } else {
            adminService.save(admin);
            return new ResponseEntity<String>("Administrator with P.ID: " + admin.getMat() + " saved properly: ", HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = "/update-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> updateAdmin (@RequestBody Admin admin) {
        if(adminService.findByMat(admin.getMat()) != null) {
            Admin newAdmin = adminService.findByMat(admin.getMat());
            if(newAdmin.getName() != admin.getName() || newAdmin.getSurname() != admin.getSurname()) {
                return new ResponseEntity<String>("Administrator is incorrect!!", HttpStatus.BAD_REQUEST);
            }
            adminService.updateAdmin(admin);
            return new ResponseEntity<String>("Administrator with P.ID: " + admin.getMat() +" updated properly", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("Inexistent Administrator, update is impossible", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value="/delete-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> deleteAdmin(@RequestBody Admin admin) {
        if (adminService.findByMat(admin.getMat()) == null) {
            return new ResponseEntity<String>("Inexistent Administrator", HttpStatus.BAD_REQUEST);
        } else {
            Admin newAdmin = adminService.findByMat(admin.getMat());
            if(newAdmin.getId() != admin.getId()) {
                return new ResponseEntity<String>("Personal Identification is incorrect!!", HttpStatus.BAD_REQUEST);
            }
            adminService.deleteAdmin(admin);
            return new ResponseEntity<>("Administrator with P.ID" +  admin.getMat() + " deleted properly: ", HttpStatus.ACCEPTED);
        }
    }
}
