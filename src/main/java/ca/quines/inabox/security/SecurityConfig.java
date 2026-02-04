package ca.quines.inabox.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
/**
 * {@see #SecurityConfig(String)}
 */
public class SecurityConfig {

	private final IpAddressMatcher subnetMatcher;
	private final IpAddressMatcher localhostV4 = new IpAddressMatcher("127.0.0.1");
	private final IpAddressMatcher localhostV6 = new IpAddressMatcher("::1");

	/**
	 * Provide a CIDR block as app.security.allowed-range to disable CSRF checking
	 * for local addresses, useful for iPhones or other devices on your local network to
	 * use the REST services directly, through Shortcuts or similar apps.
	 * 
	 * @param range
	 *   The ":" after the variable name provides a default value (empty string)
	 */
	public SecurityConfig(@Value("${app.security.allowed-range:}") String range) {
		if (range != null && !range.isBlank()) {
			this.subnetMatcher = new IpAddressMatcher(range);
		} else {
			this.subnetMatcher = null;
		}
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// 1. Ignore CSRF only if the request is from the local network
				.csrf(csrf -> csrf.ignoringRequestMatchers(this::isLocalNetwork))

				// 2. Authorization: All requests still require a Basic Auth header
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

				// 3. Authentication: Enable Basic Auth security
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}

	private boolean isLocalNetwork(HttpServletRequest request) {
		boolean isLocalhost = localhostV4.matches(request) || localhostV6.matches(request);

		// Only check the CIDR matcher if it was initialized.
		return isLocalhost || (subnetMatcher != null && subnetMatcher.matches(request));
	}

}
