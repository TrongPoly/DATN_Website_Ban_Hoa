package com.fpoly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fpoly.model.Account;
import com.fpoly.service.AccountService;
import com.fpoly.service.Base64Service;
import com.fpoly.service.RoleService;
import com.fpoly.service.SessionService;
import com.fpoly.serviceImpl.MailService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	@Autowired
	SessionService sessionService;
	@Autowired
	AccountService accountService;
	@Autowired
	RoleService roleService;
	@Autowired
	MailService mailService;
	@Autowired
	Base64Service base64Service;

	@GetMapping("/userLogin")
	public Account getUser() {
		Account account = sessionService.getSession("user");
//		if (account == null) {
//			return null;
//		}
		return account;
	}

	@GetMapping("/account/verify/{email}")
	public RedirectView verifyEmail(@PathVariable("email") String emailEncoded) {
		String email = base64Service.decode(emailEncoded);
		Account account = accountService.findByid(email);
		// Tài khoản đã được kích hoạt
		if (account.getActive()) {
			return new RedirectView("/auth/determined");
		}
		account.setActive(true);
		accountService.saveAccount(account);
		return new RedirectView("/auth/success_verify");
	}

	@PostMapping("/account/register")
	public ResponseEntity<Account> register(@RequestBody Account account) {
		if (accountService.findByid(account.getEmail()) != null) {
			return ResponseEntity.badRequest().build();
		}
		account.setRole(roleService.findById(2));
		account.setActive(false);
		account.setLocked(false);
		accountService.saveAccount(account);
		String email = base64Service.encode(account.getEmail());
		try {
			String url = "http://localhost:8080/api/account/verify/" + email;
			mailService.activeAccountEmail(account.getEmail(), account.getFullName(), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/auth/verify")
	public ResponseEntity<String> requestVerifyEmail(@RequestParam("email") String email) {
		if (accountService.findByid(email) == null) {
			return ResponseEntity.notFound().build();
		}
		try {
			String emailEncoded = base64Service.encode(email);
			String url = "http://localhost:8080/api/account/verify/" + emailEncoded;
			mailService.activeAccountEmail(email, null, url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/auth/fogotPassword/{email}")
	public ResponseEntity<String> fogotPw(@PathVariable("email") String email) {
		if (accountService.findByid(email) == null) {
			return ResponseEntity.notFound().build();
		}
		int randomNumber = (int) (Math.random() * 9000) + 1000;
		String otp = String.valueOf(randomNumber);
		sessionService.setSession("otp", otp, 2);
		sessionService.setSession("email", email, 10);
		try {
			mailService.sendOTP(email, sessionService.getStringSession("otp"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/auth/confirm_otp")
	public ResponseEntity<String> confirmOtp(@RequestParam("otp") String otp) {
		if (otp.equals(sessionService.getStringSession("otp"))) {
			sessionService.setSession("checkConfirmOTP", "1", 10);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/auth/changePassword")
	public ResponseEntity<String> changePassword(@RequestParam("newPw") String newPassword) {
		if (sessionService.getStringSession("checkConfirmOTP") == null) {
			return ResponseEntity.badRequest().build();
		}
		try {
			String email = sessionService.getStringSession("email");
			Account account = accountService.findByid(email);
			account.setPassword(newPassword);
			accountService.saveAccount(account);
			sessionService.removeSession("checkConfirmOTP");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
}
