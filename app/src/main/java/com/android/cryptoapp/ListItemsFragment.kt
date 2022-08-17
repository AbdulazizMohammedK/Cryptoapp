package com.android.cryptoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.android.cryptoapp.databinding.FragmentListItemsBinding

class ListItemsFragment : BaseFragment<FragmentListItemsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListItemsBinding
            = FragmentListItemsBinding::inflate

    override fun setup() {
         binding.frg.text = "sjs"
    }
}