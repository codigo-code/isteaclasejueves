package com.istea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.istea.modelo.Helado

class HeladoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helado)

        val desc:TextView = findViewById(R.id.ha_desc)
        val precio:TextView=findViewById(R.id.ha_precio)
        val foto : ImageView = findViewById(R.id.ha_foto)

        val helado:Helado = intent.getSerializableExtra("helado") as Helado

        desc.text=helado.descripcion
        precio.text=helado.precio.toString()
        foto.setImageResource(helado.foto)

    }
}