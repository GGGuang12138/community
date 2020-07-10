package vip.gg.community.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

    //@Autowired
    //LoginService loginService;

    @Autowired
    FormAuthenticationProvider formAuthenticationProvider;

    @Autowired
    TokenAuthenticationProvider tokenAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/fonts/**");//web登陆时取消静态资源的拦截
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启安全策略 链式编程(定制安全机制)
        http.authorizeRequests()
                .antMatchers("/","/login","/question/**","/callback").permitAll()
                .anyRequest().authenticated();
                //and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                //  增加token，取消session
               // .addFilter(new TokenLoginFilter(authenticationManager())).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //Github的token认证
        http.addFilterBefore(new TokenAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        //启动默认登陆页面
        http.formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll();
        //关闭csrf
        http.csrf().disable();
    }
    //认证用户，
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(formAuthenticationProvider).authenticationProvider(tokenAuthenticationProvider);
    }

}
