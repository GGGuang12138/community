package vip.gg.community.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Creat by GG
 * Date on 2020/6/8  10:43 上午
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    FormAuthenticationProvider formAuthenticationProvider;

    @Autowired
    TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    SuccessAuthenticationHandler successAuthenticationHandler;

    @Autowired
    FailAuthenticationHandler failAuthenticationHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/fonts/**");//web登陆时取消静态资源的拦截
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启安全策略 链式编程(定制安全机制)
        http.authorizeRequests()
                .antMatchers("/","/login","/question/**","/callback","/error").permitAll()
                .anyRequest().authenticated();
                //and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                //  增加token，取消session
               // .addFilter(new TokenLoginFilter(authenticationManager())).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //Github的token认证
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //启动默认登陆页面
        http.formLogin().loginPage("/login").loginProcessingUrl("/login")
                .successHandler(successAuthenticationHandler)
                .failureHandler(failAuthenticationHandler);
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll();
        //关闭csrf
        http.csrf().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(formAuthenticationProvider).authenticationProvider(tokenAuthenticationProvider);
    }
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        TokenAuthenticationFilter filter = new TokenAuthenticationFilter(authenticationManager);
        filter.setAuthenticationSuccessHandler(successAuthenticationHandler);
        filter.setAuthenticationFailureHandler(failAuthenticationHandler);
        return filter;
    }
}
