package vip.gg.community.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vip.gg.community.demo.service.LoginService;

/**
 * Creat by GG
 * Date on 2020/6/8  10:43 上午
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //LoginService loginService;

    @Autowired
    CustomizeAuthenticationProvider customizeAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/fonts/**");//web登陆时取消静态资源的拦截
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启安全策略 链式编程(定制安全机制)
        http.authorizeRequests()
                .antMatchers("/","/login","/question/**").permitAll()
                .anyRequest().authenticated();

                //  增加token，取消session
               // .addFilter(new TokenLoginFilter(authenticationManager()))
               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //启动默认登陆页面
        http.formLogin().loginPage("/login").loginProcessingUrl("/login").successHandler(new SuccessAuthenticationHandler());
        //关闭csrf
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(customizeAuthenticationProvider);
    }

}
