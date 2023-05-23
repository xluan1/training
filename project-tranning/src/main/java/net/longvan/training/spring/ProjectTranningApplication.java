package net.longvan.training.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.longvan.training.spring.model.User;
import net.longvan.training.spring.repository.UserRepository;

@SpringBootApplication
public class ProjectTranningApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCrypt;

	public static void main(String[] args) {
		SpringApplication.run(ProjectTranningApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User luan = new User("user01", "Luan", "06/09/2001", "Toi la Luan", "Nam", "Ninh Thuan", "089xxx", "online",
				"xuan@gmail.com", bCrypt.encode("123"));
		userRepository.save(luan);

		User hao = new User("user02", "Hao", "06/11/2001", "Toi la Hao", "Nam", "Ninh Thuan", "088xxx", "ofline",
				"hao@gmail.com", bCrypt.encode("123"));

		userRepository.save(hao);

		User vinh = new User("user03", "Vinh", "06/11/2001", "Toi la Vinh", "Nam", "Ninh Thuan", "087xxx", "online",
				"vinh@gmail.com", bCrypt.encode("123"));
		userRepository.save(vinh);
	}
}
