package com.florm.mispagos.activities

import com.florm.mispagos.models.PaymentMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florm.mispagos.R

class PaymentMethodsAdapter(private var list: MutableList<PaymentMethod>, private var listener: PaymentMethodsActivityBridge): RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder>() {

    var positionChecked = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_payment_method, parent, false))
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox : CheckBox? = itemView.findViewById(R.id.checkbox_payment_method)
        var thumbnailImageView: ImageView? = itemView.findViewById(R.id.image_view)

        fun bind(item: PaymentMethod, position: Int) {
            checkbox?.text = item.name

            thumbnailImageView?.let {
                if(item.thumbnail != null) {

                    Glide.with(itemView.context)
                        .load(item.secureThumbnail)
                        .into(it)
                }
            }

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
                    listener.onPaymentSelected(item, c)
                }

                notifyDataSetChanged()
            }
        }
    }

    interface PaymentMethodsActivityBridge {
        fun onPaymentSelected(item: PaymentMethod, checked: Boolean)
    }
}