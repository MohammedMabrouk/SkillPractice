package com.mohamedmabrouk.skillpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohamedmabrouk.skillpractice.R
import com.mohamedmabrouk.skillpractice.utils.ScrapAsync

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ScrapAsync().execute()
    }
}