package com.jungle.tt.controller

import com.jungle.tt.authority.TokenInfo
import com.jungle.tt.dto.*
import com.jungle.tt.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView


@RestController
@RequestMapping("/api/users")
class UserController (
    private val userService : UserService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid userRequest: UserRequest): BaseResponse<Unit> {
        val resultMsg: String = userService.signUp(userRequest)
        return BaseResponse(message = resultMsg)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = userService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }

    @GetMapping("/info/{id}")
    fun searchMyInfo(@PathVariable id: Long): BaseResponse<UsersDtoResponse> {
        val response = userService.searchMyInfo(id)
        return BaseResponse(data = response)
    }

    @GetMapping("/info")
    fun searchMyInfo(): BaseResponse<UsersDtoResponse> {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .uId
        val response = userService.searchMyInfo(userId)
        return BaseResponse(data = response)
    }

    @PutMapping("/info")
    fun saveMyInfo(@RequestBody @Valid usersDtoUpdate: UsersDtoUpdate):
            BaseResponse<Unit> {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .uId
        val resultMsg: String = userService.saveMyInfo(userId ,usersDtoUpdate)
        return BaseResponse( message = resultMsg )
    }

    @DeleteMapping("/delete")
    fun deleteUser():
            BaseResponse<Unit> {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .uId
        val resultMsg: String = userService.deleteUserById(userId)
        return BaseResponse( message = resultMsg )
    }
}