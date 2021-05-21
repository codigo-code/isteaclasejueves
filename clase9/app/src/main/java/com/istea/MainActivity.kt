package com.istea

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.istea.adapter.HeladoAdapter
import com.istea.modelo.Helado

class MainActivity : AppCompatActivity() {

    lateinit var rvHelado:RecyclerView
    lateinit var listaHelado:ArrayList<Helado>
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHelado= findViewById(R.id.recycleHelado)
        inicializoListaHelados()


        rvHelado.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        // vincular mi contenedor recycleview al adaptador
        rvHelado.adapter=HeladoAdapter(listaHelado)



    }


    private fun inicializoListaHelados(){
        listaHelado = ArrayList<Helado>()

        listaHelado.add(Helado("Cono",150,R.mipmap.cono))
        listaHelado.add(Helado("Kilo",950,R.mipmap.kilo))
        listaHelado.add(Helado("Cuarto",350,R.mipmap.cuarto))

    }
}