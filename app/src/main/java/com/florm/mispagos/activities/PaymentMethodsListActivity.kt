package com.florm.mispagos.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.florm.mispagos.ProcessDataContainer
import com.florm.mispagos.R
import com.florm.mispagos.models.PaymentMethod
import com.florm.mispagos.viewmodels.PaymentMethodsViewModel

class PaymentMethodsListActivity : AppCompatActivity(), PaymentMethodsAdapter.PaymentMethodsActivityBridge {

    private lateinit var viewModel : PaymentMethodsViewModel

    private var listRecyclerView: RecyclerView? = null
    private var paymentMethodAdapter: PaymentMethodsAdapter? = null
    private var continueButton: Button? = null
    private var backButton: ImageButton? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_methods)

        bindViewElements()
        addListeners()
        createViewModel()
        addObservers()
        initPaymentMethods()
    }

    private fun bindViewElements() {
        listRecyclerView = findViewById(R.id.rv_payment_method_list)
        continueButton = findViewById(R.id.btn_continue)
        backButton = findViewById(R.id.btn_back_arrow)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun addListeners() {
        continueButton?.setOnClickListener {
            ProcessDataContainer.processDataContainer.paymentMethodSelected?.let { pm ->
                if(pm != null) {
                    goToCardIssuerActivity()
                } else {
                    Toast.makeText(this, R.string.missing_payment_method, Toast.LENGTH_LONG).show()
                }

            }?: Toast.makeText(this, R.string.missing_payment_method, Toast.LENGTH_LONG).show()
        }

        backButton?.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        viewModel.cleanData()
        finish()
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(this).get(PaymentMethodsViewModel::class.java)
    }

    private fun addObservers() {
        viewModel.paymentMethodsList.observe(this, Observer {
            loadList(it)
        })
    }

    private fun loadList(list: List<PaymentMethod>) {
        paymentMethodAdapter = PaymentMethodsAdapter(list.toMutableList(), this)
        listRecyclerView?.layoutManager = LinearLayoutManager(this)
        listRecyclerView?.adapter = paymentMethodAdapter

        progressBar?.visibility = View.GONE
    }

    private fun goToCardIssuerActivity() {
        val intent = Intent(this, CardIssuerListActivity::class.java)
        startActivity(intent)
    }

    private fun initPaymentMethods() {
        viewModel.getPaymentMethods()
    }

    override fun onPaymentSelected(item: PaymentMethod, checked: Boolean) {
        if(checked) {
            viewModel.savePaymentMethodSelected(item)
        } else {
            viewModel.cleanPaymentMethodSelected()
        }
    }

}