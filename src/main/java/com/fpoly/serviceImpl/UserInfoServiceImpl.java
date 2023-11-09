package com.fpoly.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.fpoly.model.Account;
import com.fpoly.model.Customer;
import com.fpoly.repository.AccountRepository;
import com.fpoly.service.CustomerService;
import com.fpoly.service.RoleService;
import com.fpoly.service.SessionService;
import com.fpoly.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	SessionService sessionService;
	@Autowired
	RoleService roleService;
	@Autowired
	CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account userInfo = accountRepository.findById(username).get();
		String password = userInfo.getPassword();
		String roles = userInfo.getRole().getRoleName();
		sessionService.setSession("user", userInfo, 300);
		return User.withUsername(username).password(password).roles(roles).build();
	}

	@Override
	public void loginFormOAuth2(OAuth2AuthenticationToken oauth2) {
	    String email = oauth2.getPrincipal().getAttribute("email");
	    Account account = accountRepository.findById(email).orElse(null);
	    UserDetails user = null;
	    
	    if (account != null) {
	        user = loadUserByUsername(email);
	    } else {
	        String password = Long.toHexString(System.currentTimeMillis());
	        Account account2 = new Account(email, password, roleService.findById(2), true);
	        accountRepository.save(account2);
	        String name = oauth2.getPrincipal().getAttribute("name");
	        Customer customer = new Customer(name, account2, "0123456789", true);
	        customerService.saveCustomer(customer);
	        user = loadUserByUsername(email);
	    }
	    System.out.println(oauth2);
	    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
