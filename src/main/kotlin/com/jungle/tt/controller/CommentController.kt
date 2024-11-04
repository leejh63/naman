package com.jungle.tt.controller

import com.jungle.tt.dto.BaseResponse
import com.jungle.tt.dto.CommentDto
import com.jungle.tt.dto.CustomUser
import com.jungle.tt.service.CommentSevice
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/arti/comment")
class CommentController (
    private val commentService: CommentSevice
){
    @PostMapping("/newcomments/{postid}")
    fun comment(
        @PathVariable postid: Long,
        @RequestBody commentDtoRequest: CommentDto.Request
    ) : BaseResponse<CommentDto.Response>
    {
        val uId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .uId
        val response = commentService.createComment(uId, postid, commentDtoRequest)
        return BaseResponse(data = response)

    }
}