package com.imaginato.homeworkmvvm.ui.dashboard

import android.content.Intent
import android.os.Bundle
import com.imaginato.homeworkmvvm.databinding.AcitivityDashboardBinding
import com.imaginato.homeworkmvvm.ui.base.BaseActivity
import com.imaginato.homeworkmvvm.ui.login.LoginActivity

class DashboardActivity : BaseActivity() {

    private lateinit var binding: AcitivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.btnLogout.setOnClickListener {
            // clear preference data and move to login screen
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}