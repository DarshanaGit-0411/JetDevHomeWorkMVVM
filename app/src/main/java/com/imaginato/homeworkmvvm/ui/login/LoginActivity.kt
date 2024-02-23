package com.imaginato.homeworkmvvm.ui.login

import android.os.Bundle
import com.imaginato.homeworkmvvm.databinding.ActivityLoginBinding
import com.imaginato.homeworkmvvm.ui.base.BaseActivity
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener {
            // call login api
        }
    }
}