package fa.training.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableMongoAuditing(modifyOnCreate = false, auditorAwareRef = "")
@EnableCaching
public class MockProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockProjectBackendApplication.class, args);
	}

}
