package ca.quines.inabox;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest
@AutoConfigureRestTestClient
public class HelloControllerIntegrationTest {

	@Autowired
	private RestTestClient restTestClient;

	@Test
	void testClientWithBasicAuth() {
		// Any credentials will authenticate with the test server.
		String basicAuth = "Basic " + Base64.getEncoder().encodeToString("foo:bar".getBytes());

	    restTestClient.get().uri("/hello")
	        .header(HttpHeaders.AUTHORIZATION, basicAuth)
	        .exchange()
	        .expectStatus().isOk()
	        .expectBody(String.class).consumeWith(result -> {
	        	assertTrue(result.getResponseBody().contains("Greetings"));
	        });
	}

}
