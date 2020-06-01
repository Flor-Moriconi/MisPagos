package com.florm.mispagos.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.florm.mispagos.R
import android.content.Intent
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.florm.mispagos.viewmodels.SuccessViewModel

class SuccessActivity : AppCompatActivity() {

    private var viewModel: SuccessViewModel? = null

    private var finishButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        bindViewElements()
        addListeners()
        createViewModel()
    }

    private fun bindViewElements() {
        finishButton = findViewById(R.id.btn_finish)
    }

    private fun addListeners() {
        finishButton?.setOnClickListener {
            cleanData()
            gotHome()
        }
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(this).get(SuccessViewModel::class.java)
    }

    private fun gotHome() {
        val intent = Intent(applicationContext, AmountInputActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun cleanData() {
        viewModel?.cleanAllData()
    }

}