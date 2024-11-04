package com.jungle.tt.controller

import com.jungle.tt.domain.Post
import com.jungle.tt.dto.CustomUser
import com.jungle.tt.dto.PostCreateRequest
import com.jungle.tt.dto.PostRespones
import com.jungle.tt.dto.PostUpdateRequest
import com.jungle.tt.service.Postservice
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: Postservice
) {

    @PostMapping()
    fun createPost(
        @RequestBody postCreateRequest: PostCreateRequest,
    ): PostRespones = postService.insertPost(postCreateRequest)

    @GetMapping("/{postId}")
    fun getOnePost(
        @PathVariable postId: Long,
    ): PostRespones {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .uId
        return postService.getPost(postId, userId)
    }

    @GetMapping("")
    fun getPosts():List<Post> = postService.getPosts()

    @PatchMapping("/patch/{postId}")
    fun patchPost(
        @PathVariable postId: Long,
        @RequestBody postUpdateRequest : PostUpdateRequest,
    ){
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .uId
        postService.updataPost(postId, postUpdateRequest, userId)

    }

    @DeleteMapping("/del/{postId}")
    fun deletePost(
        @PathVariable postId: Long,
    ){
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .uId
        postService.deletePost(postId, userId)
    }
}