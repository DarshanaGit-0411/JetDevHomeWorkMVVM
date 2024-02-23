package com.imaginato.homeworkmvvm.domain.login

import com.imaginato.homeworkmvvm.data.local.login.UserEntity
import com.imaginato.homeworkmvvm.data.remote.base.Result
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun callLogin(loginRequest: LoginRequest): Flow<Result<UserEntity>>
}