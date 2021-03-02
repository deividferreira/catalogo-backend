package br.eti.deividferreira.catalogo.infra.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import br.eti.deividferreira.catalogo.domain.entities.security.Authorities;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Value("${backend-legal-pecas.oauth.resource-id}")
	private String resourceId;

	@Override
	public void configure(ResourceServerSecurityConfigurer configurer) {
		configurer.resourceId(this.resourceId).stateless(false);
	}
	
	@Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                .and()
                    .authorizeRequests()
                        .antMatchers("/api/security/**")
                            .hasAnyAuthority(Authorities.ADMINISTRATOR)
                        .antMatchers("/api/registration/**")
                            .hasAnyAuthority(Authorities.ADMINISTRATOR)
                        .antMatchers("/api/**")
                            .authenticated()
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .and()
                	.sessionManagement()
                		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
