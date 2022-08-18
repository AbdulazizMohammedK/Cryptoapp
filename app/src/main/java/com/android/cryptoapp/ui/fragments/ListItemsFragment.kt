package com.android.cryptoapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cryptoapp.utils.Constant
import com.android.cryptoapp.interfaces.OnClickListener
import com.android.cryptoapp.data.State
import com.android.cryptoapp.data.model.Crypto
import com.android.cryptoapp.data.model.DataCrypto
import com.android.cryptoapp.data.request.ApiClient
import com.android.cryptoapp.databinding.FragmentListItemsBinding
import com.android.cryptoapp.utils.navigateTo
import com.android.cryptoapp.ui.adapters.CryptoAdapter
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListItemsFragment : BaseFragment<FragmentListItemsBinding>(), OnClickListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListItemsBinding =
        FragmentListItemsBinding::inflate

    private val apiClient by lazy { ApiClient() }
    private lateinit var adapter: CryptoAdapter
    private var cryptoList = ArrayList<DataCrypto>()
    override fun setup() {
        setupRecyclerView()
        lifecycleScope.launch(Dispatchers.IO) {
            flowCrypto().collect {
                when (it) {
                    is State.Fail -> {
                        Log.i("Loading", "Fail is Ok")
                    }
                    is State.Success -> {
                        binding.progressBarLoading.visibility = View.INVISIBLE
                        adapter.setData(it.data.data)
                    }
                    is State.Loading -> {
                       loading()
                    }
                }
            }
            withContext(Dispatchers.Main) {
                binding.recyclerViewCrypto.adapter = adapter
            }
        }
    }

    private fun flowCrypto(): Flow<State<Crypto>> = flow {
        emit(State.Loading)
        emit(getCryptoList())
    }

    private fun getCryptoList(): State<Crypto> {
        val response = apiClient.makeApiRequest().execute()
        Log.i("Loading", response.toString())
        return if (response.isSuccessful) {
            Gson().fromJson(response.body?.string(), Crypto::class.java).run {
                State.Success(this)
            }
        } else {
            State.Fail(response.message)
        }
    }

    private fun setupRecyclerView() {
        adapter = CryptoAdapter(cryptoList, this)
        binding.recyclerViewCrypto.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCrypto.hasFixedSize()
    }

    override fun onClick(crypto: DataCrypto) {
        val bundle = Bundle()
        bundle.putParcelable(Constant.KEY_SEND_DATA, crypto)
        requireActivity().navigateTo(CryptoDetailsFragment(), bundle)
    }

    private fun loading() {
        binding.progressBarLoading.visibility = View.VISIBLE
    }
}