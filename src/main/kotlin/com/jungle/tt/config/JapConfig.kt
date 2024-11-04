package com.jungle.tt.config

import com.jungle.tt.dto.CustomUser
import com.jungle.tt.repository.UserRepository
import com.jungle.tt.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@EnableJpaAuditing
@Configuration
class JpaConfig(
    private val userService: UserService
) {
    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAware {
            val userId = (SecurityContextHolder
                .getContext()
                .authentication
                .principal as CustomUser)
                .uId
            Optional.of(userService.getCurrentUserId(userId))
        }
    }
}