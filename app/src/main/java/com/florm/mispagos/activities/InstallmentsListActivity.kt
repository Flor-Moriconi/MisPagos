package com.florm.mispagos.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.florm.mispagos.ProcessDataContainer
import com.florm.mispagos.R
import com.florm.mispagos.models.Installment
import com.florm.mispagos.models.PayerCost
import com.florm.mispagos.viewmodels.InstallmentsViewModel

class InstallmentsListActivity : AppCompatActivity(), InstallmentsAdapter.InstallmentsActivityBridge {
    private var viewModel: InstallmentsViewModel? = null

    private var listRecyclerView: RecyclerView? = null
    private var installmentsAdapter: InstallmentsAdapter? = null
    private var continueButton: Button? = null
    private var backButton: ImageButton? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_installments)

        bindViewElements()
        createViewModel()
        addObservers()
        addListeners()
        initInstallments()
    }

    private fun bindViewElements() {
        listRecyclerView = findViewById(R.id.rv_payer_costs)
        continueButton = findViewById(R.id.btn_continue)
        backButton = findViewById(R.id.btn_back_arrow)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(this).get(InstallmentsViewModel::class.java)
    }

    private fun addListeners() {
        continueButton?.setOnClickListener {
            ProcessDataContainer.processDataContainer.payerCostSelected?.let { pc ->
                if(pc != null) {
                    goToSummaryActivity()
                } else { Toast.makeText(this, R.string.missing_installment, Toast.LENGTH_LONG).show() }
            }?: Toast.makeText(this, R.string.missing_installment, Toast.LENGTH_LONG).show()
        }

        backButton?.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        viewModel?.cleanDataSelected()
        finish()
    }

    private fun addObservers() {
        viewModel?.installmentList?.observe(this, Observer { list ->
            if(!list.isNullOrEmpty()) {
                list.first().payerCosts?.let {
                    loadList(it)
                }
            } else {
                showDialog()
            }
        })
    }

    private fun loadList(list: List<PayerCost>) {
        installmentsAdapter = InstallmentsAdapter(list.toMutableList(), this)
        listRecyclerView?.layoutManager = LinearLayoutManager(this)
        listRecyclerView?.adapter = installmentsAdapter
        progressBar?.visibility = View.GONE
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert_title_not_installment_found)
        builder.setMessage(R.string.alert_message_not_installment_found)
        builder.setPositiveButton(R.string.btn_accept) { _, _ -> goBack() }
        builder.show()
    }

    private fun goToSummaryActivity() {
        val intent = Intent(this, SummaryActivity::class.java)
        startActivity(intent)
    }

    override fun onPayerCostSelected(item: PayerCost, checked: Boolean) {
        if(checked) {
            viewModel?.savePayerCostSelected(item)
        } else {
            viewModel?.cleanPayerCostSelected()
        }
    }

    private fun initInstallments() {
        viewModel?.getInstallments()
    }

}