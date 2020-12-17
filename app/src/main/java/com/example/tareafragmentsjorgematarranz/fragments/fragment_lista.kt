package com.example.tareafragmentsjorgematarranz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tareafragments.database.Alumno
import com.example.tareafragmentsjorgematarranz.R
import com.example.tareafragmentsjorgematarranz.adapters.ItemAdapter
import com.example.tareafragmentsjorgematarranz.database.DataRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_lista.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_lista : Fragment() {
    var recyclerView: RecyclerView?=null
    var activityListener: View.OnClickListener? = null
    var itemSeleccionado: Alumno? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_lista, container, false)
        recyclerView = v.findViewById(R.id.recycler)
        return v
    }
    fun cargar(nombre:String){

        var appContext = context!!.applicationContext
        var arrayAlumnos = ArrayList<Alumno>()
        var dataRepository = DataRepository(appContext)
        var alumnos = dataRepository.getAlumnos(nombre)
        val count = alumnos.component1().alumnos.size.toString()
        for (i in 0..count.toInt() - 1) {
            val nom = alumnos.component1().alumnos[i].nombre.toString()
            val ape = alumnos.component1().alumnos[i].apellido.toString()
            arrayAlumnos.add(Alumno(0, nom, ape))
        }

        var adapter = ItemAdapter(arrayAlumnos) { item ->
            itemSeleccionado = item
            if (activityListener != null) {
                activityListener!!.onClick(view)
            }
        }
        recyclerView!!.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
        recyclerView!!.setAdapter(adapter)

    }

    companion object {
        @JvmStatic
        fun newInstance() =
                fragment_lista().apply {}
    }
}