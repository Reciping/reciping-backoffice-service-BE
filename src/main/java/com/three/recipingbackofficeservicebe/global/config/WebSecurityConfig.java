package com.three.recipingbackofficeservicebe.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

//    @Value("${spring.main.url}")
//    private String mainUrl;
//    @Value("${spring.sse.url}")
//    private String sseUrl;
//    @Value("${spring.front.url}")
//    private String frontUrl;
//
//    private final JwtUtil jwtUtil;
//    private final AuthenticationConfiguration authenticationConfiguration;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
//            throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil, userRepository,
//                passwordEncoder);
//        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
//        return filter;
//    }
//
//    @Bean
//    public JwtAuthorizationFilter jwtAuthorizationFilter() {
//        return new JwtAuthorizationFilter(jwtUtil);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf((csrf) -> csrf.disable());
//
//        http.sessionManagement((sessionManagement) ->
//                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//
//        http.authorizeHttpRequests((authorizeHttpRequests) ->
//                authorizeHttpRequests
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//                        .permitAll()
//                        .requestMatchers("/**").permitAll()
//                        .anyRequest().authenticated()
//        );
//
//        http.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList(mainUrl,frontUrl, sseUrl));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
//        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//
//        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers",
//                "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, "
//                        +
//                        "Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        source.registerCorsConfiguration("/stream/auctions/**", configuration);
//
//        return new CorsFilter(source);
//    }
}
