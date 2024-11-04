package com.jungle.tt.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUser(
    val uId: Long,
    userId:String,
    password:String,
    authorities: Collection<GrantedAuthority>
) : User(userId, password, authorities)
