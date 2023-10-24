package com.fpoly.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.model.Account;
import com.fpoly.service.SessionService;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	HttpSession session;
	
    

    @Override
    public void setSession(String key, Object value) {
    	session.setMaxInactiveInterval(18000);
		session.setAttribute(key, value);
		}


    @Override
    public void removeSession(String key) {
		session.removeAttribute(key);
    }

	@Override
	public Account getSession(String key) {
		return (Account) session.getAttribute(key);
	}

}
