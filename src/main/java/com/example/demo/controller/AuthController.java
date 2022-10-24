package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.SystemUser;
import com.example.demo.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="認證授權")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	@ApiOperation(value="認證",notes="Login 用戶登入系統並在Response Header Authorization取得token")
	@PostMapping("/login")
	public ResponseEntity<Object> getAuthenticatedToken(@RequestBody SystemUser systemUser) throws Exception {
		String account = systemUser.getAccount();
		String password = systemUser.getPassword();
		try {
			auth(account, password);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		SystemUser user = new SystemUser();
		user.setAccount(account);
		user.setPassword(password);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("account", account);
		response.put("name", account);
		return ResponseEntity.ok()
				.header("Authorization", jwtUtil.generateToken(user))
				.body(response);
	}

	private void auth(String account, String password) throws Exception {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account, password));
	}

}
