package com.fpoly.serviceImpl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.fpoly.service.Base64Service;

@Service
public class Base64ServiceImpl implements Base64Service {

	@Override
	public String encode(String input) {
		return Base64.getEncoder().encodeToString(input.getBytes());
	}

	@Override
	public String decode(String encode) {
		byte[] decodedBytes = Base64.getDecoder().decode(encode);
		return new String(decodedBytes);
	}

}
