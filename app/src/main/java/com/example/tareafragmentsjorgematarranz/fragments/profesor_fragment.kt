package com.example.tareafragmentsjorgematarranz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tareafragmentsjorgematarranz.R
import com.example.tareafragmentsjorgematarranz.database.DataRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profesor_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class profesor_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    var textViewNombre: TextView? = null
    var textViewApellido: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.profesor_fragment, container, false)
        textViewNombre = v.findViewById<View>(R.id.textViewNombre) as TextView
        textViewApellido = v.findViewById<View>(R.id.textViewApellido) as TextView
        return v
    }
    fun cargar(asignatura: String){
        var appContext = context!!.applicationContext
        var dataRepository:DataRepository = DataRepository(appContext)
        var profesores = dataRepository.getProfesores(asignatura)
        val count = profesores.component1().profesores.size.toString()
        for (i in 0..count.toInt()-1){
            var nombreProf = profesores.component1().profesores[i].nombre.toString()
            var apeProf = profesores.component1().profesores[i].apellido.toString()
            textViewNombre!!.text=nombreProf
            textViewApellido!!.text=apeProf
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment profesor_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            profesor_fragment().apply {
            }
    }
}