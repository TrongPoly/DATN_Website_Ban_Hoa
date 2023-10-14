package com.fpoly.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fpoly.model.Account;
import com.fpoly.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService{

	private Map<String, Object> sessionMap;

    public SessionServiceImpl() {
        this.sessionMap = new HashMap<>();
    }

    @Override
    public void setSession(String key, Object value) {
        sessionMap.put(key, value);
    }


    @Override
    public void removeSession(String key) {
        sessionMap.remove(key);
    }

	@Override
	public Account getSession(String key) {
		   return (Account) sessionMap.get(key);
	}

}
