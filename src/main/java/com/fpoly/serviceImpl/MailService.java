package com.fpoly.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
	JavaMailSender sender;

	public void activeAccountEmail(String email, String fullName, String url) throws Exception {
		try {
			// Tạo message
			MimeMessage message = sender.createMimeMessage();
			// Sử dụng Helper để thiết lập các thông tin cần thiết cho message
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom("trongnppc02979@fpt.edu.vn");
			helper.setTo(email);
			helper.setSubject("XÁC MINH TÀI KHOẢN");
			helper.setText("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
					+ "    <title>Xác minh tài khoản</title>\r\n" + "    <style>\r\n" + "        body {\r\n"
					+ "            font-family: Arial, sans-serif;\r\n" + "            color: #333333;\r\n"
					+ "        }\r\n" + "        .container {\r\n" + "            max-width: 600px;\r\n"
					+ "            margin: 0 auto;\r\n" + "            padding: 20px;\r\n"
					+ "            border: 1px solid #dddddd;\r\n" + "            border-radius: 5px;\r\n"
					+ "        }\r\n" + "        h1 {\r\n" + "            font-size: 24px;\r\n"
					+ "            margin-bottom: 20px;\r\n" + "        }\r\n" + "        p {\r\n"
					+ "            margin-bottom: 20px;\r\n" + "        }\r\n" + "        .button {\r\n"
					+ "            display: inline-block;\r\n" + "            background-color: #4CAF50;\r\n"
					+ "            color: white;\r\n" + "            padding: 10px 20px;\r\n"
					+ "            text-align: center;\r\n" + "            text-decoration: none;\r\n"
					+ "            cursor: pointer;\r\n" + "            border-radius: 5px;\r\n" + "        }\r\n"
					+ "        .button:hover {\r\n" + "            background-color: #45a049;\r\n" + "        }\r\n"
					+ "    </style>\r\n" + "</head>\r\n" + "<body>\r\n" + "    <div class=\"container\">\r\n"
					+ "        <h1>Xác minh tài khoản của bạn</h1>\r\n" + "        <p>Xin chào " + fullName
					+ ",</p>\r\n"
					+ "        <p>Cảm ơn bạn đã đăng ký tài khoản tại Shop Bán Hoa Daisy. Để hoàn tất quá trình đăng ký, vui lòng xác minh tài khoản bằng cách nhấp vào nút dưới đây:</p>\r\n"
					+ "<a class=\"button\" href=\""+url+"\" style=\"color: white;\">Xác minh tài khoản</a>\r\n"
					+ "        <p>Nếu bạn không thực hiện đăng ký tại Shop Bán Hoa Daisy, vui lòng bỏ qua email này.</p>\r\n"
					+ "        <p>Xin cảm ơn và chúc bạn có trải nghiệm tuyệt vời tại Shop Bán Hoa Daisy!</p>\r\n"
					+ "        <p>Trân trọng,</p>\r\n" + "    </div>\r\n" + "</body>\r\n" + "</html>", true);
			sender.send(message);
		} catch (Exception e) {
			throw e;
		}
	}
}
