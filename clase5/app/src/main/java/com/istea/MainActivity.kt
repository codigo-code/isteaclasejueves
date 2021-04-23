package com.istea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.istea.modelo.User

class MainActivity : AppCompatActivity() {

    lateinit var nombre:EditText
    lateinit var email: EditText
    lateinit var celular:EditText
    lateinit var sexo:RadioGroup
    lateinit var sexo_seleccionado:RadioButton
    lateinit var roles: Spinner
    lateinit var aceptar:Switch
    lateinit var guardar:Button
    lateinit var verListaUsuarios:Button
    var listaUsuario: ArrayList<User> =ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicilizar()

        guardar.setOnClickListener(View.OnClickListener {
            if(aceptar.isChecked){
               val u:User =  guardarDatos()
                Toast.makeText(this, "Usuario Guardado!",Toast.LENGTH_LONG).show()
                Log.i("nombre",u.nombre )
                Log.i("email",u.email )
                Log.i("celular",u.celular )
                Log.i("sexo",u.sexo )
                Log.i("rol",u.rol )
                // guardo en una lista de usuarios cada usuario nuevo
                listaUsuario.add(u)
                clearElement()
            }else{
                Toast.makeText(this, "NO FUERON ACEPTADOS LOS TERMINOS Y CONDICIONES",Toast.LENGTH_LONG).show()
            }
        })


        verListaUsuarios.setOnClickListener(View.OnClickListener {

            if(listaUsuario.size > 0){
                val intent:Intent = Intent(this,MuestroUsuariosActivity::class.java)
                intent.putExtra("listaUsuario",listaUsuario) // mapa / diccionario
                startActivity(intent)
            }else{
                Toast.makeText(this,"NO HAY ELEMENTOS PARA PASAR DE ACTIVIDAD",Toast.LENGTH_LONG).show()
            }
        })

    }


    private fun clearElement(){
        nombre.setText("")
        email.setText("")
        celular.setText("")
        aceptar.isChecked=false
    }

    private fun guardarDatos(): User{

        //  new User --->
        return User(nombre.text.toString(),email.text.toString(),celular.text.toString(),getSexo().text.toString(),roles.selectedItem.toString())
    }

    private fun getSexo(): RadioButton{
        sexo_seleccionado = findViewById(sexo.checkedRadioButtonId)
        return sexo_seleccionado
    }

    private fun inicializarSpinner(){
       // definir los datos de mi combo

       // creamos un arrayList de String donde definimos de forma finita los roles
       var listRoles = arrayOf("admin","blogger","editor","anonymous")

       // nos generamos un adaptador, donde le pasamos el contexto + el layout propio de android, por ultimo el ArrayList
       var adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listRoles)

       // generamos la vista donde se van a estar desplegando las opciones
       adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

       // al spinner que tenemos mapeado en el layout le tenemos que dar el comportamiento del adaptador
       roles.adapter=adaptador
   }

    private fun inicilizar(){
        nombre=findViewById(R.id.e_user)
        email=findViewById(R.id.e_mail)
        celular=findViewById(R.id.e_numero)
        sexo=findViewById(R.id.rg_sexo)
        roles=findViewById(R.id.sp_rol)
        inicializarSpinner() // le doy comportamiento al spinner
        guardar=findViewById(R.id.b_guardar)
        aceptar=findViewById(R.id.s_aceptar)
        verListaUsuarios=findViewById(R.id.b_ver_usuarios)


    }
}