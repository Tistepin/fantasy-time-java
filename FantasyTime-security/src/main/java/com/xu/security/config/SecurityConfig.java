package com.xu.security.config;


import com.xu.security.filter.TokenAuthenticationFilter;
import com.xu.security.filter.TokenLoginFilter;
import com.xu.security.utils.DefaultPasswordEncoder;
import com.xu.security.utils.TokenLogoutHandler;
import com.xu.security.utils.TokenManager;
import com.xu.security.utils.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: Security配置类
 * @author: 徐國紀
 * @author: F3863479
 * @createTime: 2023-01-2023/1/2-上午 10:56
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    DefaultPasswordEncoder defaultPasswordEncoder;
    @Autowired
    TokenManager tokenManager;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public SecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder, TokenManager tokenManager, StringRedisTemplate stringRedisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 登录
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    // 已经设置了md5加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())//没有权限访问
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/FT/index/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,stringRedisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, stringRedisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, stringRedisTemplate)).httpBasic();
        // 关闭 csrf
        http.csrf().disable();

    }

    /**
     * 配置哪些请求不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET);
        //swagger相关接口
        web.ignoring().antMatchers("/swagger-ui.html","/swagger-resources/**","/webjars/**","/*/api-docs","/works/user/regist");
    }


}
