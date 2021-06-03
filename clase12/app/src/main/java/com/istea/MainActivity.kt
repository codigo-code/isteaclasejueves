package com.istea

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.istea.api.IApiRMHardCode
import com.istea.api.implementation.ApiRickMorty
import com.istea.modelo.Personaje
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {

    lateinit var  nombre: TextView
    lateinit var genero:TextView
    lateinit var especie:TextView
    lateinit var status:TextView
    lateinit var foto: ImageView
    lateinit var id: EditText
    lateinit var buscar :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeElements()

        //val api = IApiRMHardCode.create().getPersonaje()

        buscar.setOnClickListener(View.OnClickListener {


            val api = ApiRickMorty().getPersonajeById(id.text.toString().toInt())

            api.enqueue(object : Callback<Personaje>{
                override fun onFailure(call: Call<Personaje>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Personaje>, response: Response<Personaje>) {

                    if(response.body() != null){
                        val data = response.body()

                        nombre.text= data?.name
                        genero.text=data?.gender
                        especie.text=data?.species
                        status.text=data?.status



                        Glide.with(applicationContext).load(data?.image).into(foto)
                        
                    }
                }

            })
        })



    }

    private fun initializeElements(){
        nombre = findViewById(R.id.name)
        genero=findViewById(R.id.genero)
        especie=findViewById(R.id.especie)
        status=findViewById(R.id.status)
        foto=findViewById(R.id.foto)
        id= findViewById(R.id.id_buscar)
        buscar=findViewById(R.id.buscar)
    }
}