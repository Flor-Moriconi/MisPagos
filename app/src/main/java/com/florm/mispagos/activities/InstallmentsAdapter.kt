package com.florm.mispagos.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.florm.mispagos.R
import com.florm.mispagos.models.PayerCost

class InstallmentsAdapter(private var list: MutableList<PayerCost>, private var listener: InstallmentsActivityBridge): RecyclerView.Adapter<InstallmentsAdapter.ViewHolder>() {

    var positionChecked = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_installment, parent, false))
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox : CheckBox? = itemView.findViewById(R.id.checkbox_installment)

        fun bind(item: PayerCost, position: Int) {
            checkbox?.text = item.recommendedMessage

            checkbox?.isChecked = positionChecked == position

            checkbox?.setOnClickListener { it as CheckBox
                if(it.isChecked) {
                    checkbox?.isChecked = true
                    positionChecked = position
                } else {
                    checkbox?.isChecked = false
                    positionChecked = -1
                }

                checkbox?.isChecked?.let { c ->
                    listener.onPayerCostSelected(item, c)
                }

                notifyDataSetChanged()
            }
        }
    }

    interface InstallmentsActivityBridge {
        fun onPayerCostSelected(item: PayerCost, checked: Boolean)
    }

}