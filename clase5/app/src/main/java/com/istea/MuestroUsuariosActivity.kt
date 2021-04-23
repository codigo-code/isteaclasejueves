package com.istea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.istea.modelo.User
import org.w3c.dom.Text
import java.lang.Exception

class MuestroUsuariosActivity : AppCompatActivity() {
    lateinit var nombre:TextView
    lateinit var email:TextView
    lateinit var rol:TextView
    lateinit var sexo:TextView
    lateinit var buscar:Button
    lateinit var id:EditText
    lateinit var userList :ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_muestro_usuarios)

        inicilizarElementos()

        buscar.setOnClickListener(View.OnClickListener {
            // cuando se activa esta actividad en el intent viene un objeto ( mapa) -> listaUsuario ---> lista

            try {
                userList = intent.getSerializableExtra("listaUsuario") as ArrayList<User>

                // una lista con 3 elementos ---> 0, 1,2 --->
                // caso que el usuario escriba 100 ---> el indice 100 no existe ---> IndexOutOfBoundException
                mapping(userList.get(id.text.toString().toInt()))


            }catch (e: Exception){
                Toast.makeText(this,"El indice no es correcto hay problemas con la lista",Toast.LENGTH_LONG).show()
                Log.e("ErrorListaUsuarios", e.message)
            }



        })

    }


    private fun mapping(user: User){
        nombre.setText("Nombre: " + user.nombre)
        sexo.setText("Sexo: " +user.sexo)
        rol.setText("Rol: " +user.rol)
        email.setText("Email: " +user.email)
    }

    private fun inicilizarElementos(){
        nombre=findViewById(R.id.mu_nombre)
        email=findViewById(R.id.mu_emial)
        rol=findViewById(R.id.mu_rol)
        sexo=findViewById(R.id.mu_sexo)
        buscar=findViewById(R.id.mu_buscar)
        id=findViewById(R.id.mu_id)

    }
}