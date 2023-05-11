package com.universityW3.service;


import com.universityW3.model.Admin;
import com.universityW3.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepo;

    @Override
    public List<Admin> listAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public void deleteAdmin(Admin admin) {
        adminRepo.delete(admin);
    }

    @Override
    public Admin findByMat(String adminFind) {

        List<Admin> allAdmins = adminRepo.findAll();
        try {
            List<Admin> listAndFind = allAdmins
                    .stream()
                    .filter(a -> ((a.getMat()).equals(adminFind)))
                    .collect(Collectors.toList());
            if (listAndFind.size() == 0) {
                return null;
            }
            return listAndFind.get(0);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return this.adminRepo.save(admin);
    }

    @Override
    public void save (Admin admin) {
        this.adminRepo.save(admin);
    }


}
