package com.fpoly.service;

import com.fpoly.model.Account;

public interface SessionService {
	void setSession(String key, Object value, int minute);

	Account getSession(String key);

	void removeSession(String key);

	String getStringSession(String key);
	
}
