package com.imaginato.homeworkmvvm.exts

import com.imaginato.homeworkmvvm.data.local.login.UserEntity
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse

fun LoginResponse.asUserEntity(header: String?): UserEntity? {
    this.userId?.let {
        return UserEntity(
            it,
            this.userName,
            header,
            this.isDeleted
        )
    }
    return null
}