//package com.dpw.lyl.join.good.job.foundation.security.config;
//
//import com.dpw.lyl.join.good.job.foundation.security.handler.MyAuthenticationFailureHandler;
//import com.dpw.lyl.join.good.job.foundation.security.handler.MyAuthenticationSuccessHandler;
//import com.dpw.lyl.join.good.job.foundation.security.service.TokenService;
//import com.octv.member.common.core.redis.RedisCache;
//import com.octv.member.framework.config.properties.IgnoredUrlsProperties;
//import com.octv.member.framework.handler.CustomAccessDeniedHandler;
//import com.octv.member.framework.handler.CustomExceptionTranslator;
//import com.octv.member.framework.handler.MyAuthenticationFailureHandler;
//import com.octv.member.framework.handler.MyAuthenticationSuccessHandler;
//import com.octv.member.framework.security.filter.WindowLoginFilter;
//import com.octv.member.framework.security.handle.AuthenticationEntryPointImpl;
//import com.octv.member.framework.security.handle.LogoutSuccessHandlerImpl;
//import com.octv.member.framework.web.service.SysPermissionService;
//import com.octv.member.framework.web.service.TokenService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//
///**
// * @Author: dengpw
// * @createTime: 2022年09月21 15:50:19
// * @version: 1.0.0
// * @date 2021年06月11日10:20
// * @desc
// */
//@RequiredArgsConstructor
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    public static final String LOGIN = "/login";
//    private final RedisConnectionFactory redisConnectionFactory;
//    @Autowired
//    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//    @Autowired
//    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private RedisCache redisCache;
//    @Autowired
//    private TokenService tokenService;
//    @Value("${token.signingKey}")
//    private String signingKey;
//    @Value("${token.secret}")
//    private String secret;
//    @Autowired
//    private SysPermissionService permissionService;
//    /**
//     * 认证失败处理类
//     */
//    @Autowired
//    private AuthenticationEntryPointImpl unauthorizedHandler;
//    /**
//     * 退出处理类
//     */
//    @Autowired
//    private LogoutSuccessHandlerImpl logoutSuccessHandler;
//
//    @Autowired
//    private IgnoredUrlsProperties ignoredUrlsProperties;
//
//    @Resource
//    private SMSSecurityConfig smsSecurityConfig;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        /**
//         *
//         * @Author: dengpw
//         * @createTime: 2022年09月21 15:50:19
//         * @version: 1.0.0
//         * @date2021/4/26
//         * @desc 将不需要鉴权的url进行封装 提取到业务模块中，
//         *       避免频繁修改公共模块造成难以维护
//         */
//        if (!CollectionUtils.isEmpty(ignoredUrlsProperties.getUrls())) {
//            for (String url : ignoredUrlsProperties.getUrls()) {
//                http.authorizeRequests().antMatchers(url).permitAll();
//            }
//
//        }
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler).accessDeniedHandler(new CustomAccessDeniedHandler())
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/*.html",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js"
//                ).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//        // 登录地址
//        http.formLogin().loginProcessingUrl("/login");
//        // 处理登录成功x
//        http.formLogin().successHandler(myAuthenticationSuccessHandler);
//        // 处理登录失败
//        http.formLogin().failureHandler(myAuthenticationFailureHandler);
//        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
//        // 添加JWT filter
//        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        // 开启跨域共享，跨域伪造请求限制=无效
//        http.cors().and().csrf().disable();
//        http.headers().frameOptions().disable();
//        //短信验证码登录
//        http.apply(smsSecurityConfig);
//    }
//
//
//    @Bean
//    public WindowLoginFilter authenticationFilter() throws Exception {
//        WindowLoginFilter filter = new WindowLoginFilter();
//        filter.setAuthenticationManager(authenticationManager);
//        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
//        filter.setRedisCache(redisCache);
//        filter.setTokenService(tokenService);
//        filter.setFilterProcessesUrl(LOGIN);
//        filter.setPermissionService(permissionService);
//        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
//
//        return filter;
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
//        authenticationEntryPoint.setExceptionTranslator(new CustomExceptionTranslator());
//        resources.authenticationEntryPoint(authenticationEntryPoint);
//    }
//
//
//
//}
