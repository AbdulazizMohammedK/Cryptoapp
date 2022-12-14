package com.android.cryptoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.android.cryptoapp.ui.fragments.ListItemsFragment
import com.android.cryptoapp.interfaces.OnClickListener
import com.android.cryptoapp.R
import com.android.cryptoapp.data.model.DataCrypto
import com.android.cryptoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(ListItemsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }

    override fun onClick(crypto: DataCrypto) {
        TODO("Not yet implemented")
    }
}