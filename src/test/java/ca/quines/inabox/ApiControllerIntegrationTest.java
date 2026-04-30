package ca.quines.inabox;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest
@AutoConfigureRestTestClient
public class ApiControllerIntegrationTest {

	@Autowired
	private RestTestClient restTestClient;

	@Test
	void testOverviewWithoutAuth() {
		restTestClient.get().uri("/api/overview")
	        .exchange()
	        .expectStatus().isUnauthorized();
	}

	@Test
	void testOverviewWithAuth() {
		String basicAuth = "Basic " + Base64.getEncoder().encodeToString("admin:password".getBytes());

	    restTestClient.get().uri("/api/overview")
	        .header(HttpHeaders.AUTHORIZATION, basicAuth)
	        .exchange()
	        .expectStatus().isOk()
	        .expectBody(String.class).consumeWith(result -> {
	        	assertNotNull(result.getResponseBody());
	        });
	}

}
