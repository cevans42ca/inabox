package ca.quines.inabox;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MongoPropertyLogger implements CommandLineRunner {

    private final Environment env;

    public MongoPropertyLogger(Environment env) {
        this.env = env;
    }

    @Override
    public void run(String... args) {
        System.out.println("======= MONGODB CONFIG CHECK =======");
        System.out.println("Target Database: " + env.getProperty("spring.data.mongodb.database"));
        System.out.println("Host: " + env.getProperty("spring.data.mongodb.host"));
        System.out.println("Auth Database: " + env.getProperty("spring.data.mongodb.authentication-database"));
        System.out.println("====================================");
    }

}
