package com.universityW3.controller;

import com.universityW3.DAO.AdminDao;
import com.universityW3.model.Admin;
import com.universityW3.model.Roles;
import com.universityW3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping(value="/find-admin/{admin}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> findAdmin(@PathVariable(name ="admin", required = true) String mat) {
        if (adminService.findByMat(mat) != null) {
                Admin admin = new Admin();
                return new ResponseEntity("Administrator found with mat: " + admin.getMat(), HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity("Administrator not found with mat: " + mat, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


    @PostMapping(value="/validate-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Admin> loginRequest(@RequestBody Admin admin) {
        if (adminService.findByMat(admin.getMat()) != null) {
            if (admin.getPassword().equals((adminService.findByMat(admin.getMat())).getPassword())) {
                return new ResponseEntity(admin.getMat(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(admin.getMat(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity(admin.getMat(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/new-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> newAdmin(@RequestBody Admin admin) {
        if(adminService.findByMat(admin.getMat()) != null) {
            return new ResponseEntity("Admin: " + admin.getMat() + " already exist", HttpStatus.BAD_REQUEST);
        } else {
            AdminDao adminDao = null;
            Set<Roles> rolesSet = admin.getRoles();

            Set<String> roleString = admin.getRoles()
                  .stream()
                  .map(r -> "ROLE_" + r.getRole())
                  .collect(Collectors.toSet());

            MainSecurity mainSecurity = new MainSecurity();
            String passwEncode = mainSecurity.passowrdEncrypt(admin.getPassword());

            admin.setPassword(passwEncode);
            admin.setRoles(rolesSet);
            adminService.save(admin);

            return new ResponseEntity("Admin created succesfully with mat: " +admin.getMat(), HttpStatus.ACCEPTED);
        }
    }



    @PatchMapping(value = "/update-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateAdmin (@RequestBody Admin admin) {
        if(adminService.findByMat(admin.getMat()) != null) {
            AdminDao adminDao = null;
            Set<Roles> rolesSet = admin.getRoles();

            Set<String> roleString = admin.getRoles()
                    .stream()
                    .map(r -> "ROLE_" + r.getRole())
                    .collect(Collectors.toSet());

            MainSecurity mainSecurity = new MainSecurity();
            String passwEncode = mainSecurity.passowrdEncrypt(admin.getPassword());

            admin.setPassword(passwEncode);
            admin.setRoles(rolesSet);
            adminService.save(admin);

            return new ResponseEntity("Administrator created succesfully with mat: " +admin.getMat(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Administrator: " + admin.getMat() + " doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="/delete-admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteAdmin(@RequestBody Admin admin) {
        if (adminService.findByMat(admin.getMat()) == null) {
            return new ResponseEntity("Administrator: " + admin.getMat() + " doesn't exist", HttpStatus.BAD_REQUEST);
        } else {
            Admin newAdmin = adminService.findByMat(admin.getMat());
            if(!newAdmin.getSurname().equals(admin.getSurname()) || !newAdmin.getName().equals(admin.getName())) {
                return new ResponseEntity("Wrong authentication", HttpStatus.BAD_REQUEST);
            }
            adminService.deleteAdmin(admin);
            return new ResponseEntity("Administrator: " + admin.getMat() + " deleted properly", HttpStatus.ACCEPTED);
        }
    }
}
