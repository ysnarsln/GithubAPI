package me.yasinarslan.githubapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import me.yasinarslan.githubapi.R
import me.yasinarslan.githubapi.presentation.list.ListFragment

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)
		toolbar.setNavigationOnClickListener {
			onBackPressed()
		}

		if (savedInstanceState == null) {
			val fragment = supportFragmentManager.findFragmentByTag(ListFragment::class.java.simpleName)
				?: ListFragment()

			supportFragmentManager.beginTransaction()
				.add(R.id.container, fragment, ListFragment::class.java.simpleName)
				.commit()
		}
	}

	override fun onBackPressed() {
		if (supportFragmentManager.backStackEntryCount == 0) {
			super.onBackPressed()
		} else {
			supportFragmentManager.popBackStack()
		}
	}
}