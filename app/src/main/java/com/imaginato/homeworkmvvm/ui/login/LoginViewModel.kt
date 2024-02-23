package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.local.login.UserEntity
import com.imaginato.homeworkmvvm.data.remote.base.Result
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequest
import com.imaginato.homeworkmvvm.domain.login.usecases.LoginUseCase
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import com.imaginato.homeworkmvvm.ui.utils.PASSWORD_CANNOT_BE_EMPTY
import com.imaginato.homeworkmvvm.ui.utils.PASSWORD_MUST_BE_GREAT_THEN_6_CHARACTER
import com.imaginato.homeworkmvvm.ui.utils.USERNAME_CANNOT_BE_EMPTY
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {
    private var _loginUserLiveData: MutableLiveData<UserEntity> = MutableLiveData()
    val userEntityLiveData: LiveData<UserEntity>
        get() {
            return _loginUserLiveData
        }

    /**
     * if valid credential then call api by handling success and failed response
     */
    fun callLogin(userName: String, password: String) {
        if (isValidLoginCred(userName,password)) {
            viewModelScope.launch {
                val loginRequest = LoginRequest(userName, password)
                loginUseCase.doLogin(loginRequest).onStart {
                    _progress.value = true
                }.catch {
                    _errorMessage.value = it.message
                    _progress.value = false
                }.collect { result ->
                    when (result) {
                        is Result.Error -> {
                            _errorMessage.value = result.message
                            _progress.value = false
                        }

                        is Result.Loading -> {
                            _progress.value = true
                        }

                        is Result.Success -> {
                            _progress.value = false
                            result.data?.let {
                                _loginUserLiveData.value = it
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Checking email and password validation
     */
    private fun isValidLoginCred(userName: String, password: String): Boolean {
        if (userName.isEmpty()) {
            _errorMessage.value = USERNAME_CANNOT_BE_EMPTY
            return false
        } else if (password.isEmpty()) {
            _errorMessage.value = PASSWORD_CANNOT_BE_EMPTY
            return false
        } else if (password.length < 6) {
            _errorMessage.value = PASSWORD_MUST_BE_GREAT_THEN_6_CHARACTER
            return false
        }
        return true
    }
}