package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.local.login.UserDao
import com.imaginato.homeworkmvvm.data.local.login.UserEntity
import com.imaginato.homeworkmvvm.data.remote.base.HEADER
import com.imaginato.homeworkmvvm.data.remote.base.HTTP_ERROR
import com.imaginato.homeworkmvvm.data.remote.base.NO_INTERNET_CONNECTION
import com.imaginato.homeworkmvvm.data.remote.base.Result
import com.imaginato.homeworkmvvm.data.remote.base.SOMETHING_WENT_WRONG
import com.imaginato.homeworkmvvm.data.remote.base.STATUS_CODE_SUCCESS
import com.imaginato.homeworkmvvm.data.remote.base.UNKNOWN_ERROR
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.domain.login.LoginRepository
import com.imaginato.homeworkmvvm.exts.asUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class LoginDataRepository(
    private var api: LoginApi,
    private val userDao: UserDao,
) : LoginRepository {
    override suspend fun callLogin(loginRequest: LoginRequest) = flow {
        try {
            api.callLogin(loginRequest).apply {
                val data = body()?.data
                if (isSuccessful && data != null) {
                    if (body()?.errorCode == STATUS_CODE_SUCCESS) {
                        val user = data.asUserEntity(headers()[HEADER])
                        //insert userdata in DB
                        insertUserToDB(user)
                        emit(Result.Success(user))
                    } else {
                        emit(Result.Error(body()?.errorMessage))
                    }
                } else {
                    emit(Result.Error(SOMETHING_WENT_WRONG))
                }
            }
        } catch (e: IOException) {
            // Handle IOException, no internet connection
            emit(Result.Error(NO_INTERNET_CONNECTION))
        } catch (e: HttpException) {
            // Handle HTTP exceptions
            emit(Result.Error("$HTTP_ERROR ${e.code()}"))
        } catch (e: Exception) {
            // Handle other exceptions
            emit(Result.Error("$UNKNOWN_ERROR ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun insertUserToDB(userEntity: UserEntity?) {
        userEntity?.let {
            withContext(Dispatchers.IO) {
                userDao.insertUser(it)
            }
        }
    }

}