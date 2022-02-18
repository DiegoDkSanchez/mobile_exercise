package com.mobileexercise.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @BindingAdapter("app:visibility")
    @JvmStatic fun visibility(loading: ProgressBar, visible: Boolean) {
        if (visible) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }
}