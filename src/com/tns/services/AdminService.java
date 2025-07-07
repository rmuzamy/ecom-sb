package com.tns.services;

import com.tns.entities.Admin;
import java.util.List;
import java.util.ArrayList;

public class AdminService {
	private List<Admin> adminList = new ArrayList<>();

    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    public List<Admin> getAdmins() {
        return adminList;
    }
}
