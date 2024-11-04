package com.jungle.tt.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(
    name = "posts",
    indexes = [
        Index(columnList = "title"),
        Index(columnList = "created_By"),
//        Index(columnList = "createdAt"),
    ])

class Post (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0L,

    @Column(nullable = false, length = 255)
    var title:String,

    @Column(nullable = false, length = 6553)
    var content:String,

    @Column(nullable = false, updatable = false)
    val createDate: LocalDateTime = LocalDateTime.now(),

    @CreatedBy
    @Column(nullable = false)
    var createdBy: String = ""
){

    companion object{
        fun of(title: String, content: String):
                Post = Post(
                            title = title,
                            content = content,
                    )
    }

    fun updateTitle(title: String){
        this.title = title
    }
    fun updateContent(content: String){
        this.content = content
    }

}


