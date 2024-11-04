package com.jungle.tt.domain

import com.jungle.tt.dto.UsersDtoResponse
import com.jungle.tt.status.ROLE
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_loginId" ,columnNames = ["userId"])]
)

class Users (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var uId: Long? = null,

    @Column(nullable = false, updatable = false, length = 50)
    val userId: String,

    @Column(nullable = false, length = 50)
    val password: String, // 암호화 필요

    @Column(nullable = false, length = 10)
    var name: String

){
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    val usersRole: List<UsersRole>? = null

    fun toDto(): UsersDtoResponse =
        UsersDtoResponse(
            uId!!,
            userId,
            name,
        )

    fun updateName(name: String){
        this.name = name
    }


}

@Entity
class UsersRole(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var roleId: Long? = null,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    val role: ROLE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_users_role_user_id"))
    val users: Users,

    )

