package com.universityW3.service;


import com.universityW3.model.Admin;

import java.util.List;

public interface AdminService {

    public List<Admin> listAdmins();

    public void deleteAdmin(Admin admin);

    public Admin findByMat(String mat);

    public Admin updateAdmin(Admin admin);

    public void save(Admin admin);
}
