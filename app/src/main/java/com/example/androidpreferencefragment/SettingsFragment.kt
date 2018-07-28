package com.example.androidpreferencefragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.preference.ListPreference
import android.preference.PreferenceManager
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.view.View

class SettingsFragment: PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide the divider
        setDivider(ColorDrawable(Color.TRANSPARENT))
        setDividerHeight(0)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_settings)

        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_key_zipcode)))
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_key_unit)))

    }

    private fun bindPreferenceSummaryToValue(preference: Preference) {
        preference.onPreferenceChangeListener = this

        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.context)
                        .getString(preference.key, ""))
    }


    override fun onPreferenceChange(preference: Preference?, value: Any?): Boolean {
        val stringValue = value.toString()

        if (preference is ListPreference) {
            val listPreference = preference
            val prefIndex = listPreference.findIndexOfValue(stringValue)
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.entries[prefIndex])
            }
        } else {
            preference?.summary = stringValue
        }
        return true
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        return when (preference.key) {
            getString(R.string.pref_key_allow_notification) -> {
                true
            }
            getString(R.string.pref_key_zipcode) -> {
                true
            }
            getString(R.string.pref_key_unit) -> {
                true
            }
            else -> {
                super.onPreferenceTreeClick(preference)
            }
        }
    }

}