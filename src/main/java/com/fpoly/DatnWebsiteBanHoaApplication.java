package com.fpoly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.fpoly.repository")
@EntityScan("com.fpoly.model")
public class DatnWebsiteBanHoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatnWebsiteBanHoaApplication.class, args);
	}

}