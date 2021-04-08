package com.istea.clase3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.istea.clase3.modelo.User

class RegistrarActivity : AppCompatActivity() {

    lateinit var user: EditText
    lateinit var pass: EditText
    lateinit var rePass:EditText
    lateinit var aceptar:Button
    lateinit var clear:Button
    lateinit var userObj:User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resgistrar)

        initilizarElemento()

        clear.setOnClickListener(View.OnClickListener { limpiarElementos() })

        aceptar.setOnClickListener(
            View.OnClickListener {
                if(pass.text.toString().equals(rePass.text.toString())){
                    // puedo guardar el usuario

                    // populo el objeto User que pronto va a ser transportado a la actividad Main
                    userObj= User(user.text.toString(), pass.text.toString())
                    var intento: Intent= Intent(this,MainActivity::class.java)
                    intento.putExtra("objUser",userObj)

                    // map<k,v> ---> mapa.put("nombre","dante") ---->   mapa.get("nombre") ---> "dante" ---->  mapa.putExtra("sarasa",obj) ---> mapa.get("sarasa") ---> obj

                    startActivity(intento)

                }else{
                    Toast.makeText(this,"El password no coincide vuelva a intentar!",Toast.LENGTH_LONG).show()
                }
            }
        )
    }


    fun limpiarElementos(){
        user.setText("")
        pass.setText("")
        rePass.setText("")

    }

    fun initilizarElemento(){
        user=findViewById(R.id.r_e_user)
        pass=findViewById(R.id.r_e_password)
        rePass=findViewById(R.id.r_e_repassword)
        aceptar=findViewById(R.id.r_b_aceptar)
        clear=findViewById(R.id.r_b_clear)

    }
}