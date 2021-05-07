package com.istea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.istea.modelo.Cono
import com.istea.modelo.Cuarto
import com.istea.modelo.Helado
import com.istea.modelo.Kilo
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var rb_helado:RadioGroup
    lateinit var rb_option:RadioButton
    lateinit var layoutMostrar:LinearLayout
    lateinit var bVerHelado:Button
    lateinit var bcomprar:Button
    lateinit var helado:Helado
    var listaHelados:ArrayList<Helado> = ArrayList<Helado>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeElements()

        bVerHelado.setOnClickListener(  View.OnClickListener {
            rb_option = findViewById(rb_helado.checkedRadioButtonId)

            // traemos en contexto el layout muestro_helado_layout
            var view = LayoutInflater.from(this).inflate(R.layout.muestro_helado_layout,null)
            val foto: ImageView = view.findViewById(R.id.mh_foto)
            val desc:TextView = view.findViewById(R.id.mh_descripcion)
            val aceptar:Button = view.findViewById(R.id.mh_aceptar)

            when(rb_option.text.toString().toLowerCase()){
                "cono"->{
                    foto.setImageResource(R.mipmap.cono)
                    desc.setText("Cono de dos gustos")

                    helado = Cono(arrayListOf("vinilla","chocolate"),100.0,R.mipmap.cono)
                }
                "kilo"->{
                    foto.setImageResource(R.mipmap.kilo)
                    desc.setText("Kilo de 4 gustos")
                    helado = Kilo(arrayListOf("vinilla","chocolate","dulce de leche","frutilla"),700.0,R.mipmap.kilo)

                }
                "cuarto"->{
                    foto.setImageResource(R.mipmap.cuarto)
                    desc.setText("Cuarto de 3 gustos")
                    helado = Cuarto(arrayListOf("chocolate","dulce de leche","frutilla"),400.0,R.mipmap.cuarto)

                }
            }
            aceptar.setOnClickListener(View.OnClickListener {
                bcomprar.isEnabled=true
                listaHelados.add(helado)
                Toast.makeText(this,"agregado al carrito",Toast.LENGTH_LONG).show()
            })

            layoutMostrar.removeAllViews()
            layoutMostrar.addView(view)
        })


        bcomprar.setOnClickListener(View.OnClickListener {
            layoutMostrar.removeAllViews()
            Toast.makeText(this,"finalizar compra",Toast.LENGTH_LONG).show()
            var view = LayoutInflater.from(this).inflate(R.layout.mostrar_pedido_layout,null)

            val detalle: TextView = view.findViewById(R.id.mp_detalle_pedido)
            val precio:TextView = view.findViewById(R.id.mp_valor)
            val foto:ImageView=view.findViewById(R.id.mp_foto_helado)

            listaHelados.forEach { item ->
                    Toast.makeText(this,item.javaClass.simpleName,Toast.LENGTH_LONG).show()
                    var gustos: String =""
                        item.gustos.forEach{g -> gustos = gustos + g +"\n"}

                    detalle.setText(gustos)
                    precio.setText(item.precio.toString())
                    foto.setImageResource(item.foto)


            }
            layoutMostrar.addView(view)
            bcomprar.isEnabled=false

        })
    }

    private fun initializeElements(){
        rb_helado= findViewById(R.id.rg_tipo_helado)
        layoutMostrar = findViewById(R.id.ly_muestro_pedido)
        bVerHelado=findViewById(R.id.b_ver)
        bcomprar=findViewById(R.id.b_comprar)
    }

    /*private fun creoHelado(gustos:ArrayList<String>, precio:Double):Helado{
        // new del objeto
        return Kilo(gustos,precio)
    }*/
}