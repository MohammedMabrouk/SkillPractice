package com.mohamedmabrouk.skillpractice.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mohamedmabrouk.skillpractice.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_login -> startActivity(Intent(this, LoginActivity::class.java))
            R.id.btn_register -> startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}