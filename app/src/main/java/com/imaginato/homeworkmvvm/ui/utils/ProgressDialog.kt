package com.imaginato.homeworkmvvm.ui.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import com.imaginato.homeworkmvvm.R

class ProgressDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_dialog)

        // Set the background of the dialog window to a semi-transparent color
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = params as WindowManager.LayoutParams

    }
}