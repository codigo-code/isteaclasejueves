package com.istea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class InicioActivity : AppCompatActivity() {

    private val TIME_OUT: Long =5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        loadResources()
    }

    private fun loadResources(){
        // llamaremos a API's , BDE , recursos X
        Handler().postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish() // esto viene del ciclo de vida de las actividades
        },TIME_OUT)
    }

    override fun onStart() {
        super.onStart()

        // comportamiento a los objetos que se hayan mapeado en el onCreate
    }

    override fun onResume() {
        super.onResume()

        //se vuelve a levantar la aplicacion

    }

    override fun onPause() {
        super.onPause()

        // la aplicacion se pauso por un evento agendo
    }

    override fun onStop() {
        super.onStop()
        // frenamos la actividad
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}