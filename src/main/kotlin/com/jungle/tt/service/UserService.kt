package com.jungle.tt.service

import com.jungle.tt.authority.JwtTokenProvider
import com.jungle.tt.authority.TokenInfo
import com.jungle.tt.domain.Users
import com.jungle.tt.domain.UsersRole
import com.jungle.tt.dto.*
import com.jungle.tt.repository.UserRepository
import com.jungle.tt.exception.InvalidInputException
import com.jungle.tt.repository.UserRoleRepository
import com.jungle.tt.status.ROLE
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull


@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val userRoleRepository: UserRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun signUp(userRequest: UserRequest): String {
        var users: Users? = userRepository.findByUserId(userRequest.userId)
        if (users != null) {
            throw InvalidInputException("userId", "이미 등록된 ID")
        }

        users = userRequest.toEntity()
        userRepository.save(users)

        val usersRole: UsersRole = UsersRole(null, ROLE.USERS, users)
        userRoleRepository.save(usersRole)

        return "가입완료"
    }

    fun login(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }

    fun searchMyInfo(id: Long): UsersDtoResponse {
        val users = userRepository.findById(id).getOrNull()
            ?: throw InvalidInputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")
        return users.toDto()
    }

    fun saveMyInfo(uId:Long, usersDtoUpdate: UsersDtoUpdate): String {
        val users = userRepository.findById(uId).getOrNull()
            ?: throw InvalidInputException("id", "회원번호(${uId})가 존재하지 않는 유저입니다.")

        users.updateName(usersDtoUpdate.name)
        userRepository.save(users)
        return "수정 완료되었습니다."
    }

    @Transactional
    fun deleteUserById(uId:Long): String {
        val users = userRepository.findById(uId).getOrNull()
            ?: throw InvalidInputException("id", "회원번호(${uId})가 존재하지 않는 유저입니다.")

        // UsersRole 엔티티 먼저 삭제
        userRoleRepository.deleteAllByUsers(users)
        // 그 다음에 Users 엔티티 삭제
        userRepository.delete(users)
        return "삭제 성공" // 삭제 성공
    }

    @Transactional
    fun getCurrentUserId(uId: Long) : String {
        //todo 예외처리 필요
            val user = userRepository.findById(uId).get().userId

            return user
        }
}