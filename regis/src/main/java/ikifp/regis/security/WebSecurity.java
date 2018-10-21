package ikifp.regis.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity // konfigurowanie klasy odpowiedzialanej za firewall aplikacji
/*public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Value("${security.secret}")
	private String secret;
	@Value("${security.issuer}")
	private String issuer;
	@Value("${security.token_expiration_in_seconds}")
	private int tokenExpiration;
}

@Bean
public PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
}
*/