package com.istea.clase3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SpashActivity : AppCompatActivity() {


    private var TIME_OUT:Long=3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)

        loadSplash()


    }

    private fun loadSplash(){
        Handler().postDelayed({
            var i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        },TIME_OUT)
    }
}