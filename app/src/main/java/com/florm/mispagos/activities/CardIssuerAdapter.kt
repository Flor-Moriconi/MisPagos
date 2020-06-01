package com.florm.mispagos.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.florm.mispagos.R
import com.florm.mispagos.models.CardIssuer

class CardIssuerAdapter(private var list: MutableList<CardIssuer>, private var listener: CardIssuerActivityBridge): RecyclerView.Adapter<CardIssuerAdapter.ViewHolder>() {

    var positionChecked = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card_issuer, parent, false))
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox : CheckBox? = itemView.findViewById(R.id.checkbox_card_issuer)
        var thumbnailImageView: ImageView? = itemView.findViewById(R.id.image_view)

        fun bind(item: CardIssuer, position: Int) {
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
                    listener.onCardIssuerSelected(item, c)
                }

                notifyDataSetChanged()
            }
        }
    }

    interface CardIssuerActivityBridge {
        fun onCardIssuerSelected(item: CardIssuer, checked: Boolean)
    }
}