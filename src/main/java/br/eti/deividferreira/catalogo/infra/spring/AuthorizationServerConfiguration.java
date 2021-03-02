package br.eti.deividferreira.catalogo.infra.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import br.eti.deividferreira.catalogo.domain.services.AuthenticationService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${backend-legal-pecas.oauth.resource-id}")
	private String resourceId;
	
	@Value("${backend-legal-pecas.jwt.signing-key: 44f87be5-4199-49b9-bd19-c581f0b48a55}")
	private String signingKey;

	@Value("${backend-legal-pecas.oauth.web-client.name}")
	private String webClientName;
	@Value("${backend-legal-pecas.oauth.web-client.secret}")
	private String webClientSecret;

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationService authenticationService;
	private final AuthenticationManager authenticationManager;

	public AuthorizationServerConfiguration(PasswordEncoder passwordEncoder,
			AuthenticationService authenticationService, AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.authenticationService = authenticationService;
		this.authenticationManager = authenticationManager;
	}
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .userDetailsService(this.authenticationService)
                .authenticationManager(this.authenticationManager)
                .accessTokenConverter(accessTokenConverter());
    }
	
	@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }
	
	@Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                    .withClient(this.webClientName)
                        .authorizedGrantTypes("password", "refresh_token")
                        .scopes("all")
                        .refreshTokenValiditySeconds(172800) // 48 horas
                        .resourceIds(this.resourceId)
                        .secret(this.passwordEncoder.encode(this.webClientSecret))
                        .accessTokenValiditySeconds(43200); // 12 horas
    }
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(this.signingKey);
		
		return converter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
}
