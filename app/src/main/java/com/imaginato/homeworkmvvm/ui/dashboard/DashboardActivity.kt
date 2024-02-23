package com.imaginato.homeworkmvvm.ui.dashboard

import android.os.Bundle
import com.imaginato.homeworkmvvm.databinding.AcitivityDashboardBinding
import com.imaginato.homeworkmvvm.ui.base.BaseActivity

class DashboardActivity : BaseActivity() {

    private lateinit var binding: AcitivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcitivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}