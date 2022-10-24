package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.bean.SystemUser;
import com.example.demo.dao.SystemUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private SystemUserRepository systemUserRepository;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		SystemUser user = systemUserRepository.findByAccount(account);
		if(user == null) {
			throw new UsernameNotFoundException("UsernameNotFound");
		}
		return new User(user.getAccount(),user.getPassword(),new ArrayList<>());
	}
}
