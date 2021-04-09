package com.istea.clase3

import android.icu.util.IslamicCalendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class InicioActivity : AppCompatActivity() {


    lateinit var peso:EditText
    lateinit var altura:EditText
    lateinit var calcular:Button
    lateinit var foto:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        inicializar();

        calcular.setOnClickListener(View.OnClickListener {
            foto.setImageResource(calculoIMC(peso.text.toString().toDouble(),altura.text.toString().toDouble()))
        })

    }


    fun calculoIMC(peso:Double, altura:Double):Int{

        var res = peso /(altura*altura)

        if(res <= 18.4){
            return R.mipmap.flaco
        }else if(res >= 18.5 && res <= 24.9){
            return R.mipmap.normal
        }else{
            return R.mipmap.gordo
        }


    }

    fun inicializar(){
        peso= findViewById(R.id.i_e_peso)
        altura=findViewById(R.id.i_e_altura)
        calcular=findViewById(R.id.i_b_calcular)
        foto=findViewById(R.id.i_foto)
    }

}