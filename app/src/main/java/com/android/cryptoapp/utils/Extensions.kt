package com.android.cryptoapp.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.android.cryptoapp.R
import com.android.cryptoapp.data.NavigationType

fun FragmentActivity.navigateTo(fragment: Fragment, bundle: Bundle? = null) {
    fragment.arguments = bundle
    changeNavigation(this, NavigationType.Add, fragment)
}

fun FragmentActivity.replaceFragment(toFragment: Fragment, bundle: Bundle? = null) {
    toFragment.arguments = bundle
    changeNavigation(this, NavigationType.Replace, toFragment)
}

fun FragmentActivity.back(fragment: Fragment) {
    changeNavigation(this, NavigationType.Remove, fragment)
}

private fun changeNavigation(
    activity: FragmentActivity,
    state: NavigationType,
    fragment: Fragment
) {
    val transaction = activity.supportFragmentManager.beginTransaction()
    when (state) {
        NavigationType.Add -> transaction.add(R.id.nav_host_fragment, fragment)
        NavigationType.Remove -> transaction.remove(fragment)
        NavigationType.Replace -> transaction.replace(R.id.nav_host_fragment, fragment)
    }
    transaction.addToBackStack(null).commit()
}