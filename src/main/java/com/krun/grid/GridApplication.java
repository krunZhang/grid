package com.krun.grid;

import com.krun.grid.entity.Administrator;
import com.krun.grid.repository.AdministratorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GridApplication {

	private final String USERNAME     = "admin";
	private final String RAW_PASSWORD = "admin";

	public static void main (String[] args) {
		SpringApplication.run(GridApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * 初始化管理员表
	 * @param repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(AdministratorRepository repository, PasswordEncoder encoder) {
		return args -> {
			int size = repository.findAll().size();
			if (size != 0) {
				// 已经有管理员了
				Administrator administrator = repository.findAll().get(0);
				System.out.println(encoder.matches(RAW_PASSWORD, administrator.getPassword()));
				return;
			}


			Administrator administrator = new Administrator();
			String password = encoder.encode(RAW_PASSWORD);
			administrator.setUsername(USERNAME);
			administrator.setPassword(password);
			repository.save(administrator);
		};
	}
}
