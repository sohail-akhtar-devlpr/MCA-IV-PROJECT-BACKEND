package com.webapp.codeathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.config.JwtUtil;
import com.webapp.codeathon.entity.SubAdmin;
import com.webapp.codeathon.repository.SubAdminRepository;
import com.webapp.codeathon.role.UserRole;

@Service
public class SubAdminService {

    @Autowired
    private SubAdminRepository subAdminRepository;
    
    @Autowired
    private JwtUtil jwtUtil;

    //save the subadmin
    public SubAdmin saveSubAdmin(SubAdmin subAdmin) {
    	subAdmin.setRole(UserRole.SUBADMIN);
        return subAdminRepository.save(subAdmin);
    }
    
    //get the subadmin infomation
    public SubAdmin findSubadminInfo(String jwt) {
    	String email = jwtUtil.getEmailFromJwt(jwt);
    	System.out.println("EMAIL:: "+email);
        return subAdminRepository.findByEmail(email);
    }

}
