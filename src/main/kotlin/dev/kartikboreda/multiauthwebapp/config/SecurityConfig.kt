package dev.kartikboreda.multiauthwebapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.context.HttpSessionSecurityContextRepository


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http {
            securityContext {
                securityContextRepository = HttpSessionSecurityContextRepository()
            }
            authorizeHttpRequests {
                authorize("/login", permitAll)
                authorize("/error", permitAll)
                authorize("/404", permitAll)
                authorize("/500", permitAll)
                authorize(anyRequest, authenticated)
            }
            formLogin {
                loginPage = "/login"
//                loginProcessingUrl = "/pages/login"
                defaultSuccessUrl("/home", true)
//                failureUrl = "/login?error"
                permitAll()
            }
            oauth2Login {
                loginPage = "/login"
                defaultSuccessUrl("/home", true)
            }
            logout {
                logoutSuccessUrl = "/login?logout"
                permitAll = true
            }
        }


        return http.build()
    }




    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()


    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
//        var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
//        println(passwordEncoder.encode("password"))

//        val password = "{bcrypt}\$2a\$10\$IUZuqwMAiJcS7c8PcNYIc.Ihu9Rl7rr3845yHyb798jirMSaUwRgq"

        val user = User.withUsername("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }


}
