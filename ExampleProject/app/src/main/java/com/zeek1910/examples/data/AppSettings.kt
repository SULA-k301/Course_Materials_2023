package com.zeek1910.examples.data

import android.content.Context

class AppSettings(context: Context) {

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var isShowOnboarding: Boolean
        get() = sharedPref.getBoolean(KEY_IS_SHOW_ONBOARDING, true)
        set(value) {
            sharedPref.edit().putBoolean(KEY_IS_SHOW_ONBOARDING, value).apply()
        }

    companion object {
        private const val PREF_NAME = "settings"
        private const val KEY_IS_SHOW_ONBOARDING = "key_is_show_onboarding"
    }
}