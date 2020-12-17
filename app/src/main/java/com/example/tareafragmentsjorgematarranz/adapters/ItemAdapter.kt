package com.example.tareafragmentsjorgematarranz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tareafragments.database.Alumno
import com.example.tareafragmentsjorgematarranz.R

class ItemAdapter(var items: ArrayList<Alumno>, private val listener: (Alumno) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_list, parent, false)
        val viewHolder = ViewHolder(v)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
        holder.itemView.setOnClickListener {listener(items[position]) }
    }

    override fun getItemCount(): Int {
        if (items != null){
            return items.size
        }else{
            return 0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre : TextView
        val textViewApe : TextView
        init {
            textViewNombre = itemView.findViewById<TextView>(R.id.textViewNomRe)
            textViewApe = itemView.findViewById<TextView>(R.id.textViewApeRe)
        }

        fun bindItems(alumno: Alumno) {
            textViewNombre.text = alumno.nombre
            textViewApe.text = alumno.apellido
        }

    }

}