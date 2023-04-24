package miniproject2.server.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    // @Override
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    //     return super.authenticationManagerBean();
    // }

    // @Bean
    // public SecurityFilterChain filterChain2 (HttpSecurity http) throws Exception {

    //     AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    //     authenticationManagerBuilder.userDetailsService(customerUsersDetailsService);
    //     authenticationManager = authenticationManagerBuilder.build();

    //     http.csrf().disable().cors().disable().authorizeHttpRequests().requestMatchers("/user/login", "/user/signup", "/user/forgotPassword").permitAll()
    //         .anyRequest().authenticated()
    //         .and()
    //         .authenticationManager(authenticationManager)
    //         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    //         return http.build();

    // }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors().configurationSource(request-> new CorsConfiguration().applyPermitDefaultValues())
            .and()
            .csrf().disable()
            // .requiresChannel(channel -> channel.anyRequest().requiresSecure())
            .authorizeHttpRequests()
            .requestMatchers("/user/login", "/user/signup", "/user/forgotPassword", "/", "/3rdpartylicenses.txt", "/manifest.json",
             "/4.4717e5eb4a95ae1082e7.js", "/5.eae7db7e18e0633dcdfe.js", "/favicon.ico", "/food1.ad9d67dfb73a9b6f2010.jpg", "/index.html", "/main.a393017f731b9e49d7c9.js", 
            "/materialdesignicons-webfont.5cbc1ce0f8ee2c20e27b.woff2", "/materialdesignicons-webfont.49d3b4ee7b1dee3671b0.eot", "/materialdesignicons-webfont.d6f0edc61c6ca22474a7.html", 
            "/materialdesignicons-webfont.da5fb9dba31bb391d27e.woff", "/materialdesignicons-webfont.fa3815d439cf3406399e.ttf", "/polyfills.94daefd414b8355106ab.js", "/runtime.0b53df65c9d48fcbd279.js", "/styles.0fff239001061c6da782.css"
            , "/assests/**", "/images/**").permitAll()
            .anyRequest().authenticated()
            // .and().requiresChannel().requestMatchers("/user/login").requiresSecure()
            .and().exceptionHandling().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // if (protocol != null && protocol.equalsIgnoreCase("http")) {
            //     http.requiresChannel().anyRequest().requiresInsecure();
            // } else {
            //     http.requiresChannel().anyRequest().requiresSecure();
            // }
            //http.requiresChannel().anyRequest().requiresSecure();
            

            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

            
        return http.build();
    }

}
