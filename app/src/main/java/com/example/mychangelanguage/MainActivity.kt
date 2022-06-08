package com.example.mychangelanguage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    lateinit var languageRadioGroup: RadioGroup
    lateinit var nextBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prefManager: SharedPreferences = this.getSharedPreferences("selectedLang", Context.MODE_PRIVATE)
        languageRadioGroup = findViewById(R.id.language_RadioGroup)

        nextBtn = findViewById(R.id.nextBtn)

        nextBtn.setOnClickListener {
            val radioButton = findViewById<RadioButton>(languageRadioGroup.checkedRadioButtonId)
            val editPref = prefManager.edit()


            when (radioButton.text) {
                "hindi" -> {
                    editPref.putString("lang", "hi")
                    LocaleHelper.override(this)

                }
                else -> {
                    editPref.putString("lang", "en")
                    LocaleHelper.override(this)
                }
            }
            editPref.commit()
            startActivity(Intent(this, SettingsActivity::class.java))
            this.finish()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { LocaleHelper.wrap(it) })
    }
}