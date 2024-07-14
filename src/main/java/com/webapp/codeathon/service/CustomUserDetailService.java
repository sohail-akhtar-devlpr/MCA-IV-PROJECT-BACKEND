package com.webapp.codeathon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.webapp.codeathon.entity.Contestant;
import com.webapp.codeathon.entity.RootAdmin;
import com.webapp.codeathon.entity.SubAdmin;
import com.webapp.codeathon.exception.UserNotFoundException;
import com.webapp.codeathon.repository.ContestantRepository;
import com.webapp.codeathon.repository.RootAdminRepository;
import com.webapp.codeathon.repository.SubAdminRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	//RootAdmin Repository
	private RootAdminRepository rootAdminRepository;
	
	//SubAdmin Repository
	private SubAdminRepository subAdminRepository;
	
	//Contestant Repository
	private ContestantRepository contestantRepository;
	
	

	public CustomUserDetailService(RootAdminRepository rootAdminRepository,
			SubAdminRepository subAdminRepository,
			ContestantRepository contestantRepository) {
		super();
		this.rootAdminRepository = rootAdminRepository;
		this.subAdminRepository = subAdminRepository;
		this.contestantRepository=contestantRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) {
//		System.out.println("LOAD USER BY USERNAME 2");
		System.out.println("USER NAME: "+username);
		List<GrantedAuthority> authorities= new ArrayList<>();
		
		RootAdmin rootAdmin=rootAdminRepository.findByEmail(username);
		
		System.out.println("ROOT ADMIN DETAILS:"+rootAdmin);
		
		if(rootAdmin != null) {
			//THIS USER IS FROM import org.springframework.security.core.userdetails.User;
			return new User(rootAdmin.getEmail(),rootAdmin.getPassword(),authorities);
		}
		
		SubAdmin subAdmin= subAdminRepository.findByEmail(username);
		System.out.println("SUB ADMIN DETAILS:"+subAdmin);
		
		if(subAdmin != null) {
			//THIS USER IS FROM import org.springframework.security.core.userdetails.User;
			return new User(subAdmin.getEmail(),subAdmin.getPassword(),authorities);
		}
		
		Contestant contestant = contestantRepository.findByFacultyNumber(username);
		System.out.println("CONTESTANT DETAILS:"+contestant);
		
		if(contestant != null) {
			return new User(contestant.getFacultyNumber(),contestant.getEnrollmentNumber(), authorities);
		}
		
//		System.out.println("user not found with email "+username);
//		throw new UsernameNotFoundException("user not found with email-");
		throw new UserNotFoundException("user not found",username);
//		return null;
	}

}
