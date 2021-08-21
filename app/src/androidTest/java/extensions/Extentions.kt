package extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.getLastFragment(): Fragment {
	return supportFragmentManager.fragments.last()
}