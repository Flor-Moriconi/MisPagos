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
import com.florm.mispagos.models.CardIssuer
import com.florm.mispagos.viewmodels.CardIssuerViewModel

class CardIssuerListActivity : AppCompatActivity(), CardIssuerAdapter.CardIssuerActivityBridge {
    private var viewModel: CardIssuerViewModel? = null

    private var listRecyclerView: RecyclerView? = null
    private var cardIssuerAdapter: CardIssuerAdapter? = null
    private var continueButton: Button? = null
    private var backButton: ImageButton? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_issuer)

        bindViewElements()
        createViewModel()
        addObservers()
        addListeners()
        initCardIssuers()
    }

    private fun bindViewElements() {
        continueButton = findViewById(R.id.btn_continue)
        backButton = findViewById(R.id.btn_back_arrow)
        listRecyclerView = findViewById(R.id.rv_card_issuer_list)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(this).get(CardIssuerViewModel::class.java)
    }

    private fun addListeners() {
        continueButton?.setOnClickListener {
            ProcessDataContainer.processDataContainer.cardIssuerSelected?.let { cardIssuer ->
                if(cardIssuer != null) {
                    goToInstallmentsActivity()
                } else { Toast.makeText(this, R.string.missing_card_issuer, Toast.LENGTH_LONG).show() }

            }?: Toast.makeText(this, R.string.missing_card_issuer, Toast.LENGTH_LONG).show()
        }

        backButton?.setOnClickListener {
            goBack()
        }
    }

    private fun addObservers() {
        viewModel?.cardIssuersList?.observe(this, Observer {
            if(it.count() > 0) {
                loadList(it)
            } else {
                showDialog()
            }
        })
    }

    private fun goBack() {
        viewModel?.cleanDataSelected()
        finish()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert_title_not_payment_method_found)
        builder.setMessage(R.string.alert_message_not_payment_method_found)
        builder.setPositiveButton(R.string.btn_accept) { _, _ -> goBack() }
        builder.show()
    }

    private fun loadList(list: List<CardIssuer>) {
        cardIssuerAdapter = CardIssuerAdapter(list.toMutableList(), this)
        listRecyclerView?.layoutManager = LinearLayoutManager(this)
        listRecyclerView?.adapter = cardIssuerAdapter

        progressBar?.visibility = View.GONE
    }

    private fun goToInstallmentsActivity() {
        val intent = Intent(this, InstallmentsListActivity::class.java)
        startActivity(intent)
    }

    private fun initCardIssuers() {
        viewModel?.getCardIssuers()
    }

    override fun onCardIssuerSelected(item: CardIssuer, checked: Boolean) {
        if(checked) {
            viewModel?.saveCardIssuerSelected(item)
        } else {
            viewModel?.cleanCardIssuerSelected()
        }
    }
}