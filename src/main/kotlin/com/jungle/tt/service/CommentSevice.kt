package com.jungle.tt.service

import com.jungle.tt.dto.CommentDto
import com.jungle.tt.exception.InvalidInputException
import com.jungle.tt.repository.CommentRepository
import com.jungle.tt.repository.PostRepository
import com.jungle.tt.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
class CommentSevice(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
){
    fun createComment(userid:Long, postid : Long, commentDtoRequest: CommentDto.Request):CommentDto.Response{
        val posts = postRepository.findById(postid).getOrNull()
            ?: throw InvalidInputException("id", " 포스트 없음.")

        val users = userRepository.findById(userid).getOrNull()
            ?: throw InvalidInputException("id", "회원번호(${userid})가 존재하지 않는 유저입니다.")

        val comment = commentRepository.save(commentDtoRequest.toComment(posts))

        return CommentDto.Response.toResponse(comment, users)
    }
}