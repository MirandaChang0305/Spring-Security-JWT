package com.example.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.bean.SystemUser;
import com.example.demo.dao.SystemUserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private final Log logger = LogFactory.getLog(JwtUtil.class);

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private SystemUserRepository systemUserRepository;

	private static final long TOKEN_VALIDITY = 60 * 60; // 1hr

	private static final String ACCOUNT = "account";
	
	private String userAccount = null;
	
	public String generateToken(SystemUser userInput) {
		SystemUser user = systemUserRepository.findByAccount(userInput.getAccount());
		return doGenerateToken(user.getAccount());
	}

	private String doGenerateToken(String account) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(ACCOUNT, account);
		Long timestamp = System.currentTimeMillis();
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public boolean isTokenValid(String jwtToken) {
		if(jwtToken == null) {
			return false;
		}
		try {
			String account = getAccount(jwtToken);
			SystemUser user = systemUserRepository.findByAccount(account);
			return user.getAccount().equals(account) && !isExpired(jwtToken);
		}catch(Exception e) {
			logger.error("Error:",e);
			return false;
		}
	}
	
	public String getUserAccount() {
		return userAccount;
	}

	private String getAccount(String jwtToken) throws Exception {
		userAccount = (String) getClaims(jwtToken).get(ACCOUNT);
		return userAccount;
	}

	private boolean isExpired(String jwtToken) throws Exception {
		Date exDate = getClaims(jwtToken).getExpiration();
		return exDate.before(new Date());
	}

	private Claims getClaims(String jwtToken) throws Exception{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
	}
}
