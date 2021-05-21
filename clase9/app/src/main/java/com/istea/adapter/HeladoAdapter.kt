package com.istea.adapter

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.istea.HeladoActivity
import com.istea.R
import com.istea.modelo.Helado

class HeladoAdapter(private val dataSet:ArrayList<Helado>): RecyclerView.Adapter<HeladoAdapter.ViewHolder>()
{



    // Mapeamos los elementos a la vista
    // clases anidadas / inner class
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        // tengo que mapear los elementos del layout helado_item
        val desc: TextView
        val precio:TextView
        val foto:ImageView
        val comprar:Button

        init {
            desc = view.findViewById(R.id.hi_desc)
            precio = view.findViewById(R.id.hi_precio)
            foto = view.findViewById(R.id.hi_foto)
            comprar=view.findViewById(R.id.hi_comprar)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.helado_item,parent,false)


        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
      return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // agrego la funcionalidad de mi vista
        val precio = dataSet[position].precio.toString()
        val descripcion = dataSet[position].descripcion
        holder.precio.text = "PRECIO: " +  precio
        holder.desc.text= "Tipo helado:  " + descripcion
        holder.foto.setImageResource(dataSet[position].foto)
        holder.comprar.setOnClickListener(View.OnClickListener {
        var comprar = false
            // me creo un dialgo en
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Oferta Istea Helados!")
            builder.setMessage("Quiere comprar " + descripcion + ", tiene un 30% de descuento! lo paga " + (precio.toInt() * 0.7))
            builder.setIcon(android.R.drawable.ic_dialog_info)

            // agrego las opciones disponibles ( si o no )


            // deberian ir a una nueva actividad con helado seleccionado ;)
            builder.setPositiveButton("Lo quiero!"){dialogInterface, i ->

                var intent:Intent= Intent(it.context, HeladoActivity::class.java)
                intent.putExtra("helado",dataSet[position])
                it.context.startActivity(intent)


            }

            builder.setNeutralButton("No gracias!"){dialogInterface, i -> Toast.makeText(it.context, "no lo quiero!", Toast.LENGTH_SHORT).show()  }

            // creo la alerta con los datos que defini previamente
            val alerta:AlertDialog =builder.create()
            alerta.show()


        })

    }
}