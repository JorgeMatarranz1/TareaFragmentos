package com.example.tareafragmentsjorgematarranz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tareafragments.database.Alumno
import com.example.tareafragmentsjorgematarranz.R


/**
 * A simple [Fragment] subclass.
 * Use the [FichaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ficha_fragment : Fragment() {

    var textViewNombre: TextView? = null
    var textViewApellido: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.ficha_fragment, container, false)

        textViewNombre = v.findViewById<View>(R.id.textViewNombre) as TextView
        textViewApellido = v.findViewById<View>(R.id.textViewApellido) as TextView
        return v
    }

    fun updateData(item: Alumno?) {
        if (item!=null) {
            textViewNombre!!.text = item.nombre
            textViewApellido!!.text = item.apellido
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ficha_fragment().apply {
                }
    }
}