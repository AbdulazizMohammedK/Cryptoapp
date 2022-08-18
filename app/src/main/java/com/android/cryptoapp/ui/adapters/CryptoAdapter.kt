package com.android.cryptoapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptoapp.interfaces.OnClickListener
import com.android.cryptoapp.data.model.DataCrypto
import com.android.cryptoapp.databinding.ItemCryptoBinding


class CryptoAdapter(
    private var cryptoList: ArrayList<DataCrypto>,
    private var listener: OnClickListener
) : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            ItemCryptoBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val cryptoItem = cryptoList[position]
        holder.binding.apply {
            ("Name : " + cryptoItem.name).also { nameTextView.text = it }
            ("Price : " + cryptoItem.priceUsd).also { priceTextView.text = it }
            ("Rank : " + cryptoItem.rank).also { rankTextView.text = it }
            buttonDetails.setOnClickListener {
                listener.onClick(cryptoItem)
            }
        }
    }

    override fun getItemCount(): Int = cryptoList.size

    inner class CryptoViewHolder(val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setData(newItems: ArrayList<DataCrypto>) {
        cryptoList = newItems
    }
}