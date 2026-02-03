package ca.quines.inabox;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InaboxApplication {

	private static final Logger LOG = LoggerFactory.getLogger(InaboxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InaboxApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> LOG.atDebug()
				.setMessage("Let's inspect the beans provided by Spring Boot:\n{}")
				.addArgument(() -> {
					String[] beanNames = ctx.getBeanDefinitionNames();
					Arrays.sort(beanNames);
					// System.lineSeparator() ensures cross-platform compatibility.
					return String.join(System.lineSeparator(), beanNames);
				})
				.log();
	}

}
