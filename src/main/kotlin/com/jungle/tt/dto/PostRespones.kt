package com.jungle.tt.dto

import java.time.LocalDateTime

data class PostRespones(
    val title: String,
    val content: String,
    val createDate: LocalDateTime,
    val createdBy: String,
)
