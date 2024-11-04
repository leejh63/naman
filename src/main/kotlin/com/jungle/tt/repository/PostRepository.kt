package com.jungle.tt.repository

import com.jungle.tt.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
        fun findAllByOrderByCreateDateDesc(): List<Post>
}