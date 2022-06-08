package com.example.mychangelanguage

import android.content.Context
import android.content.res.Configuration
import android.text.TextUtils
import java.util.*

object LocaleHelper {
    fun override(context: Context) {
        val prefs = context.getSharedPreferences("selectedLang", Context.MODE_PRIVATE)
        val custom = prefs.getString("lang", null)
        if (TextUtils.isEmpty(custom)) {
            return
        }
        val changed = Locale(custom)
        Locale.setDefault(changed)
        val configuration = Configuration()
        configuration.setLocale(changed)
        configuration.setLayoutDirection(changed)
        val resources1 = context.resources
        resources1.updateConfiguration(configuration, resources1.displayMetrics)
        if (context !== context.applicationContext) {
            val resources2 = context.applicationContext.resources
            resources2.updateConfiguration(configuration, resources2.displayMetrics)
        }
    }

    fun wrap(context: Context): Context {
        val prefs = context.getSharedPreferences("selectedLang", Context.MODE_PRIVATE)
        val custom = prefs.getString("lang", null)
        if (TextUtils.isEmpty(custom)) {
            return context
        }
        val changed = Locale(custom)
        Locale.setDefault(changed)
        val configuration = Configuration()
        configuration.setLocale(changed)
        configuration.setLayoutDirection(changed)
        return context.createConfigurationContext(configuration)
    }

}