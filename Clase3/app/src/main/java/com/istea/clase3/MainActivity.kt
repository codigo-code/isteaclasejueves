package com.istea.clase3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.istea.clase3.modelo.User

class MainActivity : AppCompatActivity() {



    lateinit var user: EditText
    lateinit var pass: EditText
    lateinit var ingresar: Button
    lateinit var registrar: Button

    // nos creamos el objet userObj harcode para testear la aplicacion de login
    //var userObject = User("admin","admin")
    lateinit var userObject: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarElementos()


        // cuando presione al boton ingresar

        ingresar.setOnClickListener(View.OnClickListener {

            if(validarUsuario(userObject.name,userObject.pass)){
                // deberia ir a mi actividad de inicio
                Toast.makeText(this,"Tiene acceso a la aplicacion",Toast.LENGTH_LONG).show()
                goToActity(this,InicioActivity::class.java)

            }else{
                // deberia ir a registrar usuario
                Toast.makeText(this,"NO Tiene acceso!!!!",Toast.LENGTH_LONG).show()
            }
        })

        registrar.setOnClickListener(View.OnClickListener {
            // invico a la actividad RegistrarActivity
            // Intent(Donde Estoy Parado, A donde queior ir ::class.java)
            // Ej Intent(this,Algo::class.java)

            goToActity(this,RegistrarActivity::class.java)
        })

    }

    // nos creamos un metodo generico donde le pasamos el contexto y la Actividad que queremos ir
    fun <T>goToActity(context: Context,nuevaVista: Class<T>){
        val intento: Intent = Intent(context,nuevaVista)
        startActivity(intento)
    }


    // tengo que generar un validador de usuario
    fun validarUsuario(userParam:String, passParam :String ):Boolean{

        return user.text.toString().equals(userParam) && pass.text.toString().equals(passParam)
    }



    fun inicializarElementos(){
        user=findViewById(R.id.e_user)
        pass=findViewById(R.id.e_password)
        ingresar=findViewById(R.id.b_ingresar)
        registrar= findViewById(R.id.b_registrar)

        if(intent.getSerializableExtra("objUser")!= null){
            userObject = intent.getSerializableExtra("objUser") as User
        }else{
            userObject = User("admin","admin")
        }


    }



}