package com.jungle.tt.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.jungle.tt.domain.Users
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class UserRequest(
    var uId: Long? = null,

    @field:NotBlank
    @JsonProperty("userId")
    private val _userId: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "영문, 숫자, 특수문자를 포함한 8~20자리로 입력."
    )
    @JsonProperty("password")
    private val _password: String?, // 암호화 필요

    @JsonProperty("name")
    private val _name: String?,

) {
    val userId: String
        get() = _userId!!
    val password: String
        get() = _password!!
    val name: String
        get() = _name!!

    fun toEntity(): Users =
        Users(uId, userId, password, name)
}

data class LoginDto(

    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId: String?,

    @field:NotBlank
    @JsonProperty("password")
    private val _password: String?, // 암호화 필요
){
    val loginId: String
        get() = _loginId!!
    val password: String
        get() = _password!!
}

data class UsersDtoResponse(
    val uId: Long,
    val loginId: String,
    val name: String,
)

data class UsersDtoDelete(
    var uId: Long
)

data class UsersDtoUpdate(
    var name: String,
)

