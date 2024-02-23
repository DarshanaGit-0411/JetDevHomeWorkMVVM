package com.imaginato.homeworkmvvm.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.imaginato.homeworkmvvm.R
import com.imaginato.homeworkmvvm.databinding.ActivityLoginBinding
import com.imaginato.homeworkmvvm.ui.base.BaseActivity
import com.imaginato.homeworkmvvm.ui.dashboard.DashboardActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
        initViewModel(viewModel)
        initObserve()
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener {
            // call login api
            viewModel.callLogin(
                binding.edtUserName.text.toString(),
                binding.edtPassword.text.toString()
            )
        }
    }

    private fun initObserve() {
        viewModel.userEntityLiveData.observe(this) {
            val username = it.name
            Toast.makeText(this, getString(R.string.login_with_user_, username), Toast.LENGTH_SHORT).show()
            binding.edtUserName.setText("")
            binding.edtPassword.setText("")
            moveToDashboard()
        }
    }

    private fun moveToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}