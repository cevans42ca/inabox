package ca.quines.inabox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

@Configuration
@EnableMongoRepositories("ca.quines.inabox.dao")
public class SpringMongoConfiguration extends AbstractMongoClientConfiguration {

	@Value("${spring.mongodb.uri}")
	private String mongoUri;

	@Override
	protected String getDatabaseName() {
	    return "inabox";
	}

	@Override
	protected void configureClientSettings(MongoClientSettings.Builder builder) {
		builder.applyConnectionString(new ConnectionString(mongoUri));
	}

}
