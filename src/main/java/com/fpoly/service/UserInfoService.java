package com.fpoly.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface UserInfoService extends UserDetailsService{

	void loginFormOAuth2(OAuth2AuthenticationToken oauth2);
	
}
