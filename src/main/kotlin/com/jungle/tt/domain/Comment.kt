package com.jungle.tt.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "comment")
class Comment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cId: Long = 0L,

    @Column(nullable = false)
    var postId: Long = 0L,

//    @Column(nullable = false)
//    var parentpostId: Long = 0L,

    @Column(nullable = false, length = 100)
    var commentcontent: String = "",

    @CreatedBy
    @Column(nullable = false)
    var commentcreatedBy: String = "",

    ){

}
