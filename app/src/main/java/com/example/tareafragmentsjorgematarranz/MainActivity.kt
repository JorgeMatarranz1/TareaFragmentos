package com.example.tareafragmentsjorgematarranz

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Spinner
import com.example.tareafragments.database.*
import com.example.tareafragmentsjorgematarranz.database.AsignaturaAlumno
import com.example.tareafragmentsjorgematarranz.database.AsignaturaProfesor
import com.example.tareafragmentsjorgematarranz.database.DataRepository
import com.example.tareafragmentsjorgematarranz.fragments.ficha_fragment
import com.example.tareafragmentsjorgematarranz.fragments.fragment_lista
import com.example.tareafragmentsjorgematarranz.fragments.profesor_fragment
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var frameLayoutFragment: FrameLayout? = null
    var frameLayoutLista: FrameLayout? = null
    var frameLayoutFicha: FrameLayout? = null
    var frameLayoutProfesor: FrameLayout? = null
    var listaFragment: fragment_lista? = null
    var fichaFragment: ficha_fragment? = null
    var profesorFragment: profesor_fragment? = null
    lateinit var nomAsSelect: String
    var segundoFragmentActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frameLayoutFragment = findViewById(R.id.frameLayoutFragment)
        frameLayoutLista = findViewById(R.id.frameLayoutLista)
        frameLayoutFicha = findViewById(R.id.frameLayoutFicha)
        frameLayoutProfesor = findViewById(R.id.frameLayoutProfesor)

        listaFragment = fragment_lista.newInstance()
        listaFragment!!.activityListener = activityListener

        profesorFragment = profesor_fragment.newInstance()

        fichaFragment = ficha_fragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (frameLayoutFragment ==null){
            // HORIZONTAL
            fragmentTransaction.add(R.id.frameLayoutLista, listaFragment!!)
            fragmentTransaction.add(R.id.frameLayoutFicha, fichaFragment!!)
            fragmentTransaction.add(R.id.frameLayoutProfesor, profesorFragment!!)
        }
        else {
            fragmentTransaction.add(R.id.frameLayoutFragment, listaFragment!!)
            fragmentTransaction.add(R.id.frameLayoutProfesor, profesorFragment!!)
        }
        fragmentTransaction.commit()

        var dataRepository: DataRepository = DataRepository(this)

        if (dataRepository.getCount() == 0) {
            cargarJson()
        }
        var objetos = dataRepository.getAsignaturas()
        var asign = ArrayList<String>()
        val bbdd = objetos.component1().nombreAsig
        val prog = objetos.component2().nombreAsig
        asign.add(bbdd)
        asign.add(prog)
        var spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, asign)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                nomAsSelect = parent.getItemAtPosition(position).toString()
                profesorFragment!!.cargar(nomAsSelect)
                listaFragment!!.cargar(nomAsSelect)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                profesorFragment!!.cargar("programacion")
            }
        }

    }

    fun cargarJson() {
        var bufferedReader = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.datos)))
        var bufferedvuelta = bufferedReader.readLine()
        var json = JSONObject(bufferedvuelta)
        var bbdd: Asignatura? = null
        var prog: Asignatura? = null
        var asignatura = json.getJSONArray("asignaturas")
        for (i in 0 until asignatura.length()) {
            if (asignatura[i].toString().equals("BBDD")) {
                bbdd = Asignatura(asignatura[i].toString())
            } else {
                prog = Asignatura(asignatura[i].toString())
            }
        }

        var profs = json.getJSONArray("profesores")
        var profprog = ArrayList<Profesor>()
        var profbbdd = ArrayList<Profesor>()
        for (i in 0 until profs.length()) {
            var prof = profs.getJSONObject(i)
            if (prof.getString("nombre").equals("Antonio")) {
                val profprogg = Profesor(prof.getInt("codigo"), prof.getString("nombre"), prof.getString("apellido"))
                profprog.add(profprogg)
            } else {
                val profprobb = Profesor(prof.getInt("codigo"), prof.getString("nombre"), prof.getString("apellido"))
                profbbdd.add(profprobb)
            }
        }

        var alumnos = json.getJSONArray("alumnos")
        var alumnosbbdd = ArrayList<Alumno>()
        var alumnosprog = ArrayList<Alumno>()
        for (i in 0 until alumnos.length()) {
            var alum = alumnos.getJSONObject(i)
            var asig = alum.getJSONArray("Asignaturas")
            for (j in 0 until asig.length()) {
                if (asig[j].toString().equals("BBDD")) {
                    val al = Alumno(alum.getInt("codigo"), alum.getString("nombre"), alum.getString("apellido"))
                    alumnosbbdd.add(al)
                } else {
                    val al = Alumno(alum.getInt("codigo"), alum.getString("nombre"), alum.getString("apellido"))
                    alumnosprog.add(al)
                }
            }
            var bases = AsignaturaProfesor(bbdd!!, profbbdd)
            var alumnosBases = AsignaturaAlumno(bbdd!!, alumnosbbdd)
            var programacion = AsignaturaProfesor(prog!!, profprog)
            var alumnosProg = AsignaturaAlumno(prog!!, alumnosprog)
            var dataRepository: DataRepository = DataRepository(this)
            dataRepository.insert(alumnosBases)
            dataRepository.insert(alumnosProg)
            dataRepository.insert(bases)
            dataRepository.insert(programacion)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("select", nomAsSelect)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        nomAsSelect = savedInstanceState.getString("select").toString()

    }

    var activityListener = View.OnClickListener {
        if (frameLayoutFragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutFragment, fichaFragment!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = true
        }
        fichaFragment!!.updateData(listaFragment!!.itemSeleccionado)
    }

    override fun onBackPressed() {
        if (segundoFragmentActivo && frameLayoutFragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutFragment, listaFragment!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = false

        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Atención: ")
            builder.setMessage("¿Desea salir de la aplicación?")
            builder.setNegativeButton("NO"){dialog, which ->  }
            builder.setPositiveButton("SI"){dialog, _ -> finish()}
            builder.show()
        }
    }

}
