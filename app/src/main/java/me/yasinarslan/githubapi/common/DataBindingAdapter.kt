package me.yasinarslan.githubapi.common

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
	view.visibility = if (value) View.VISIBLE else View.GONE
}