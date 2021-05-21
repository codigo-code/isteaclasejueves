package com.istea.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.istea.R
import com.istea.dto.Persona


class PersonaAdapter(val dataSet:ArrayList<Persona>): RecyclerView.Adapter<PersonaAdapter.ViewHolder>() {


    // vamos a mapear el layout persona_layout
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val nombre: EditText
        val dni:EditText
        val id:TextView
        val editar:Button
        val aceptar:Button
        val borrar: Button

        init {
            nombre = view.findViewById(R.id.p_nombre)
            dni = view.findViewById(R.id.p_dni)
            id = view.findViewById(R.id.p_id)
            editar = view.findViewById(R.id.p_editar)
            borrar = view.findViewById(R.id.p_borrar)
            aceptar = view.findViewById(R.id.p_aceptar)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.persona_layout,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.nombre.setText(dataSet[position].nombre)
        holder.dni.setText(dataSet[position].dni.toString())
        holder.id.setText(dataSet[position].id.toString())


        // editar CONTINUAR ESCALANDO EL RECYCLE VIEW
        holder.editar.setOnClickListener(View.OnClickListener {

            Toast.makeText(it.context, dataSet[position].nombre + " editado", Toast.LENGTH_LONG).show()
        })


    }
}