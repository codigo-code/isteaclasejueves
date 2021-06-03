package com.istea

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.stetho.Stetho
import com.istea.adapter.PersonaAdapter
import com.istea.dao.DBHelper
import com.istea.dto.Persona

class MainActivity : AppCompatActivity() {
    lateinit var nombre :EditText
    lateinit var dni :EditText
    lateinit var guardar:Button
    lateinit var ver:Button
    lateinit var personaReciclada:RecyclerView
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initilize()
        val db: DBHelper= DBHelper(this,null)
        Stetho.initializeWithDefaults(this);


        guardar.setOnClickListener(View.OnClickListener {


        })
        guardar.setOnClickListener(View.OnClickListener {

            if(db.savePersona(Persona(nombre.text.toString(),dni.text.toString().toInt()))){
                Toast.makeText(it.context,"Persona gurdada",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(it.context,"Ver log errores!",Toast.LENGTH_LONG).show()
            }
        })

        personaReciclada.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL,false)

        ver.setOnClickListener(View.OnClickListener {

            val personaAdapter:PersonaAdapter = PersonaAdapter(db.getAllPersonas())


            personaReciclada.adapter= personaAdapter
        })


    }

    private fun initilize(){
        nombre= findViewById(R.id.nombre)
        dni=findViewById(R.id.dni)
        guardar=findViewById(R.id.guardar)
        ver=findViewById(R.id.ver)
        personaReciclada = findViewById(R.id.persona_recyclada)
    }


    //

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){

            R.id.m_opcion_avazada ->{

                val intent = Intent(this,SettingsActivity::class.java)
                startActivity(intent)

                true
            }
            R.id.m_silencio->{
                Toast.makeText(this, "en silencio",Toast.LENGTH_LONG).show()
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_settings,menu)
        return true
    }
}