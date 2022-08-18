package com.android.cryptoapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.cryptoapp.utils.Constant
import com.android.cryptoapp.data.model.DataCrypto
import com.android.cryptoapp.databinding.FragmentCryptoDetailsBinding

class CryptoDetailsFragment : BaseFragment<FragmentCryptoDetailsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCryptoDetailsBinding =
        FragmentCryptoDetailsBinding::inflate
    private lateinit var crypto: DataCrypto
    override fun setup() {
        crypto = arguments?.getParcelable(Constant.KEY_SEND_DATA)!!
        setDataInDetailsFragment()
        binding.buyButton.setOnClickListener {
            openBrowser()
        }
    }

    private fun setDataInDetailsFragment() {
        binding.apply {
            nameTextView.text = crypto.name
            ("Price : " + crypto.priceUsd).also { priceTextView.text = it }
            ("Rank : " + crypto.rank).also { rankTextView.text = it }
            ("Symbol : " + crypto.symbol).also { symbolTextView.text = it }
        }
    }

    private fun openBrowser(){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(crypto.explorer))
        startActivity(browserIntent)
    }
}