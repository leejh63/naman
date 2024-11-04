package com.jungle.tt.service

import com.jungle.tt.domain.Users
import com.jungle.tt.dto.CustomUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import com.jungle.tt.repository.UserRepository


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {
    override fun loadUserByUsername(userId: String): UserDetails =
        userRepository.findByUserId(userId)
            ?.let { createUserDetails(it) }
            ?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")

    private fun createUserDetails(users: Users): UserDetails =
        CustomUser(
            users.uId!!,
            users.userId,
            passwordEncoder.encode(users.password),
            users.usersRole!!.map { SimpleGrantedAuthority("ROLE_${it.role}") }
        )
}