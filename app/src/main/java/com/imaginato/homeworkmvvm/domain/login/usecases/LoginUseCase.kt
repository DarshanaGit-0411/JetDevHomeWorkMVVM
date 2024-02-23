package com.imaginato.homeworkmvvm.domain.login.usecases

import com.imaginato.homeworkmvvm.data.local.login.UserEntity
import com.imaginato.homeworkmvvm.data.remote.base.Result
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.domain.base.BaseUseCase
import com.imaginato.homeworkmvvm.domain.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension

@OptIn(KoinApiExtension::class)
class LoginUseCase(private val loginRepository: LoginRepository) : BaseUseCase {
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Result<UserEntity>> {
        return loginRepository.callLogin(loginRequest)
    }
}