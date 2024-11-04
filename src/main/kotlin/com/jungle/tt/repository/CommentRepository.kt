package com.jungle.tt.repository

import com.jungle.tt.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CommentRepository: JpaRepository<Comment, Long> {

}