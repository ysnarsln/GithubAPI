package me.yasinarslan.githubapi.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
	view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
	if (!url.isNullOrEmpty()) {
		view.load(url)
	}
}