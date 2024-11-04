package com.jungle.tt.dto

import com.jungle.tt.domain.Comment
import com.jungle.tt.domain.Post
import com.jungle.tt.domain.Users
import org.springframework.data.annotation.CreatedBy
import java.io.Serializable


class CommentDto {

    data class Request(
        val commentcontent: String
    ) : Serializable{
        fun toComment(
            post: Post,
        ): Comment{
            return Comment(commentcontent = this.commentcontent, postId = post.id)
        }
    }

    data class Response(
        val commentcontent: String,
        var commentcreatedBy: String,
        val userId: String,
    ) : Serializable{
        companion object{
            fun toResponse(comment: Comment, users: Users): Response{
                return Response(
                    commentcontent = comment.commentcontent,
                    commentcreatedBy = comment.commentcreatedBy,
                    userId = users.userId,
                )
            }
        }
    }
}