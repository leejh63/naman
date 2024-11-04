package com.jungle.tt.dto

import com.jungle.tt.status.EnumStatus

data class BaseResponse<T>(
    val enumStatus: String = EnumStatus.SUCCESS.name,
    val data: T? = null,
    val message: String = EnumStatus.SUCCESS.msg,
)
