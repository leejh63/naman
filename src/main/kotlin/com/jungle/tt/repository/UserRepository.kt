package com.jungle.tt.repository

import com.jungle.tt.domain.Users
import com.jungle.tt.domain.UsersRole
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, Long> {
    fun findByUserId(userId: String): Users?
}

interface UserRoleRepository : JpaRepository<UsersRole, Long>{
    fun deleteAllByUsers(users: Users)
}