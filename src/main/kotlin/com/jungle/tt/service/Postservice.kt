package com.jungle.tt.service

import com.jungle.tt.domain.Post
import com.jungle.tt.repository.PostRepository
import com.jungle.tt.dto.PostCreateRequest
import com.jungle.tt.dto.PostRespones
import com.jungle.tt.dto.PostUpdateRequest
import com.jungle.tt.exception.InvalidInputException
import com.jungle.tt.repository.UserRepository
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull


@Service
@Transactional
class Postservice(
//    private val PostRepository: PostRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getPost(postId: Long, userId: Long): PostRespones {
        val post =postRepository.findById(postId).getOrNull()
            ?: throw InvalidInputException("post", "게시글(${postId})이 존재하지 않는 게시글입니다.")

        return PostRespones(
            title = post.title,
            content = post.content,
            createDate = post.createDate,
            createdBy = post.createdBy
        )
    }

    @Transactional(readOnly = true)
    fun getPosts():List<Post>{
        return postRepository.findAllByOrderByCreateDateDesc()
    }

    @Transactional
    fun insertPost(postCreateRequest: PostCreateRequest) : PostRespones {
        val post: Post =
            postRepository.save(
                Post.of(
                    postCreateRequest.title,
                    postCreateRequest.content,
                    ))

        return PostRespones(
            title = post.title,
            content = post.content,
            createDate = post.createDate,
            createdBy = post.createdBy
        )
    }

    fun updataPost(
        id: Long,
        postUpdateRequest: PostUpdateRequest,
        userId : Long,
    ) {
        val post= postRepository.findById(id).orElseThrow()

        val user = userRepository.findById(userId).getOrNull()
            ?: throw BadRequestException("유저 없음")

        if(post.createdBy != user.userId){
            throw InvalidInputException("userId", "유저아이디 접근불가")
        }

//        if(!post.checkPass(postUpdateRequest.password)){
//            throw BadRequestException("Wrong password")
//        }

         if(!postUpdateRequest.title.isNullOrBlank()) {
            post.updateTitle(postUpdateRequest.title)
         }

        if(!postUpdateRequest.content.isNullOrBlank()){
             post.updateContent(postUpdateRequest.content)
        }

    }

    fun deletePost(id:Long, userId: Long){
        val post = postRepository.findById(id).orElseThrow()

//        if(!post.checkPass(password)){
//            throw BadRequestException("Wrong password")
//        }

        val user = userRepository.findById(userId).getOrNull()
            ?: throw BadRequestException("유저 없음")

        if(post.createdBy != user.userId){
            throw InvalidInputException("userId", "유저아이디 접근불가")
        }

        postRepository.delete(post)
    }

}