package com.example.androidpreferencefragment

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(app_toolbar)
    }

    override fun onResume() {
        super.onResume()

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        et_allow_notification.setText(sharedPref.getBoolean(getString(R.string.pref_key_allow_notification), false).toString())
        et_zipcode.setText(sharedPref.getString(getString(R.string.pref_key_zipcode), ""))
        et_unit.setText(sharedPref.getString(getString(R.string.pref_key_unit), ""))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_setting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
