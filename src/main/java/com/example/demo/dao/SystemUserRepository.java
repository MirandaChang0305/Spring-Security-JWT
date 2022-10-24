package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.SystemUser;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser,Long>{
	
	SystemUser findByAccount(String account);

}
