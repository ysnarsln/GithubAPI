package me.yasinarslan.githubapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import me.yasinarslan.githubapi.R

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val toolbar = findViewById<Toolbar>(R.id.toolbar)
		setSupportActionBar(toolbar)

		if (savedInstanceState == null) {
			val fragment = supportFragmentManager.findFragmentByTag(ListFragment::class.java.simpleName) ?: ListFragment()
			supportFragmentManager.beginTransaction().add(R.id.container, fragment, ListFragment::class.java.simpleName).commit()
		}
	}
}