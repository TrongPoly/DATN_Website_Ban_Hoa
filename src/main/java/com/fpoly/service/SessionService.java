package com.fpoly.service;

public interface SessionService {
	 void setSession(String key, Object value);
	    Object getSession(String key);
	    void removeSession(String key);
}
