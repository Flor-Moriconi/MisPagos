package com.florm.mispagos.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.florm.mispagos.NumberTextWatcher
import com.florm.mispagos.R
import com.florm.mispagos.viewmodels.AmountInputViewModel

class AmountInputActivity: AppCompatActivity() {

    private var continueButton: Button? = null
    private var amountEditText: EditText? = null

    private var viewModel: AmountInputViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amount_input)

        bindViewElements()
        createViewModel()
        addListeners()
    }

    private fun bindViewElements() {
        continueButton = findViewById(R.id.btn_continue)
        amountEditText = findViewById(R.id.et_amount_entered)
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(this).get(AmountInputViewModel::class.java)
    }

    private fun addListeners() {
        continueButton?.setOnClickListener {
            val amount = amountEditText?.text.toString()
            if(amount.isNotEmpty()) {
                saveAmountEntered(amount)
                goToPaymentMethodsActivity()
            } else {
                Toast.makeText(this, R.string.complete_missing_amount, Toast.LENGTH_LONG).show()
            }
        }
        amountEditText?.let { editText ->
            editText.addTextChangedListener(NumberTextWatcher(editText, "#,###.##"))
        }
    }

    private fun goToPaymentMethodsActivity() {
        val intent = Intent(this, PaymentMethodsListActivity::class.java)
        startActivity(intent)
    }

    private fun saveAmountEntered(amount: String) {
        val amoutWithoutFormat = removeFormat(amount)
        viewModel?.saveAmountEntered(amoutWithoutFormat)
    }

    private fun removeFormat(amount: String): String {
        return amount.replace("[,]".toRegex(), "")
    }
}