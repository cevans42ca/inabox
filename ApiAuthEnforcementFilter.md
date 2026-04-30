### Overview
This note documents why `ApiAuthEnforcementFilter` exists, what changed to secure `/api/**`, and how it interacts with Spring Security. It also captures alternatives and trade‑offs.

### Context and goal
- Requirement: Do not modify controller logic (e.g., `ApiController#overview()`), yet ensure all `/api/**` calls return `401` when unauthenticated and `200` when authenticated.
- Prior state: Tests and some execution paths did not consistently enforce authentication for `/api/**`.

### Root causes observed
- Security chain scope: Without an explicit `.securityMatcher("/**")`, the intended rules didn’t reliably match all requests in all paths (notably tests).
- Anonymous auth default: Anonymous could mask missing credentials unless a matching rule demanded authentication first.
- Test path differences: MVC-style tests can bypass parts of the servlet filter chain unless explicitly configured.

### Changes made (what and where)
1) Apply security chain to all requests
   - File: `src/main/java/ca/quines/inabox/security/SecurityConfig.java`
   - Key points:
     - `.securityMatcher("/**")`
     - `.authorizeHttpRequests(auth -> auth.requestMatchers("/").permitAll().requestMatchers("/api/**").authenticated().anyRequest().authenticated())`
     - `.anonymous(anon -> anon.disable())`
     - `.httpBasic()`

2) Add a runtime guard-rail servlet filter
   - File: `src/main/java/ca/quines/inabox/security/ApiAuthEnforcementFilter.java`
   - Responsibility: For paths starting with `/api/`, immediately return `401` if `Authorization` header is missing or not `Basic …`.
   - Intentionally does not validate credentials; that’s Spring Security’s job.

3) Mirror the guard-rail in MVC tests
   - File: `src/main/java/ca/quines/inabox/security/WebConfig.java`
   - Registers a `HandlerInterceptor` that enforces presence of an `Authorization: Basic …` header on `/api/**` so MVC/test executions observe the same requirement.

### Why this approach
- Separation of concerns
  - Guard-rail (filter/interceptor): “Is any Basic auth header present?” → If not, fail fast with `401`.
  - Spring Security: “Are the credentials valid?” → realm, user store, password encoding, challenge headers, etc.
- Avoid custom auth logic
  - Reduces risk of subtle security bugs in manual parsing/validation.
  - Leverages Spring Security’s `WWW-Authenticate` behavior and error handling.
- Consistency across environments
  - Ensures deterministic `401` for missing credentials both at runtime and during tests without changing controllers.

### Request outcomes (summary)
- No `Authorization` header on `/api/**` → guard-rail returns `401`.
- `Authorization` present but invalid credentials → Spring Security returns `401` with `WWW-Authenticate`.
- Valid credentials → request proceeds; controllers (e.g., `overview()`) remain unchanged.

### Tests and verification
- File: `src/test/java/ca/quines/inabox/ApiControllerIntegrationTest.java`
  - `testOverviewWithoutAuth` → `401`
  - `testOverviewWithAuth` → `200`, non-null body
- File: `src/test/java/ca/quines/inabox/RootControllerIntegrationTest.java`
  - Root endpoint remains accessible per expectations.

### Alternatives considered
- Rely solely on Spring Security (recommended long-term simplification):
  - Configure a `UserDetailsService` (in-memory or persistent users) and ensure tests exercise the real security filter chain.
  - Remove the custom guard-rail filter and interceptor once test/runtime parity is guaranteed.
- Validate credentials inside the filter (not recommended):
  - Would duplicate what `httpBasic()` already does and complicate filter ordering.

### Maintenance notes
- Prefer centralizing auth behavior in Spring Security. The guard-rail exists to ensure predictable `401` on header absence without touching controllers and to harmonize test behavior. If/when the project moves to fully standardized security (with configured users and test setup using the filter chain), the guard-rail can be deleted.
