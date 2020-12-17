package com.example.tareafragmentsjorgematarranz.database

import android.content.Context
import android.os.AsyncTask
import com.example.tareafragments.database.*

class DataRepository(context: Context) {
    private val alumnoDao: AlumnoDao? = AppDatabase.getInstance(context)?.alumnoDao()
    private val asignaturaDao: AsignaturaDao? = AppDatabase.getInstance(context)?.asignaturaDao()
    private val profesorDao: ProfesorDao? = AppDatabase.getInstance(context)?.profesorDao()
    private val asignaturaProfesorDao: AsignaturaProfesorDao? = AppDatabase.getInstance(context)?.asignaturaProfesorDao()
    private val asignaturaAlumnoDao: AsignaturaAlumnoDao? = AppDatabase.getInstance(context)?.asignaturaAlumnoDao()

    fun insert(alumnoAsignatura: AsignaturaAlumno):Int{
        if (asignaturaDao != null && alumnoDao !=null && asignaturaAlumnoDao!= null) {
            return InsertAsignaturaAlumnoAsyncTask(asignaturaDao, alumnoDao, asignaturaAlumnoDao).execute(alumnoAsignatura).get()
        }
        return -1
    }

    fun insert(asignaturaProfesor: AsignaturaProfesor):Int{
        if (asignaturaDao != null && profesorDao !=null && asignaturaProfesorDao!= null) {
            return InsertAsignaturaProfesorAsyncTask(asignaturaDao, profesorDao, asignaturaProfesorDao).execute(asignaturaProfesor).get()
        }
        return -1
    }
    fun getCount():Int{
        var getCount = GetCount(profesorDao!!).execute().get()
        return getCount
    }
    fun getAsignaturas():List<Asignatura>{
        return GetAsignaturas(asignaturaDao!!).execute().get()
    }

    fun getAlumnos(nombre: String): List<AsignaturaAlumno>{
        return GetAlumnos(asignaturaAlumnoDao!!, nombre).execute().get()
    }

    fun getProfesores(nombre:String): List<AsignaturaProfesor>{
        return GetProfesores(asignaturaProfesorDao!!, nombre).execute().get()
    }

    private class InsertAsignaturaAlumnoAsyncTask(private val asignaturaDao: AsignaturaDao, private val alumnoDao: AlumnoDao, private val asignaturaAlumnoDao: AsignaturaAlumnoDao): AsyncTask<AsignaturaAlumno, Void, Int>(){
        override fun doInBackground(vararg asignaturaAlumno: AsignaturaAlumno?): Int {
            try{
                for (asignaturaAlumno in asignaturaAlumno){
                    if (asignaturaAlumno !=null){
                        asignaturaDao.insertAll(asignaturaAlumno.asignatura)
                        alumnoDao.insertAll(asignaturaAlumno.alumnos)
                        for (alumno in asignaturaAlumno.alumnos){
                            asignaturaAlumnoDao.insert(AlumnoAsignaturaCrossRef(asignaturaAlumno.asignatura.nombreAsig, alumno.idAlumno))
                        }
                    }
                }

                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    private class InsertAsignaturaProfesorAsyncTask(private val asignaturaDao: AsignaturaDao, private val profesorDao: ProfesorDao, private val asignaturaProfesorDao: AsignaturaProfesorDao): AsyncTask<AsignaturaProfesor, Void, Int>(){

        override fun doInBackground(vararg asignaturaProfesor: AsignaturaProfesor?): Int {
            try{
                for (asignaturaProfesor in asignaturaProfesor){
                    if (asignaturaProfesor !=null){
                        asignaturaDao.insertAll(asignaturaProfesor.asignatura)
                        profesorDao.insertAll(asignaturaProfesor.profesores)
                        for (profesor in asignaturaProfesor.profesores){
                            asignaturaProfesorDao.insert(ProfesorAsignaturaCrossRef(asignaturaProfesor.asignatura.nombreAsig, profesor.idProfesor))
                        }
                    }
                }

                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }


    private class GetCount(private val profesorDao: ProfesorDao) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg p0: Void?): Int {
            return profesorDao.getCount()
        }
    }

    private class GetAlumnos(private val asignaturaAlumnoDao: AsignaturaAlumnoDao, var nombre: String) :AsyncTask<Void, Void, List<AsignaturaAlumno>>(){
        override fun doInBackground(vararg params: Void?): List<AsignaturaAlumno> {
            return asignaturaAlumnoDao.getAsignatura(nombre)
        }
    }

    private class GetAsignaturas(private val asignaturaDao: AsignaturaDao) :AsyncTask<Void, Void, List<Asignatura>>(){
        override fun doInBackground(vararg params: Void?): List<Asignatura> {
            return asignaturaDao.getAsignatura()
        }
    }

    private class GetProfesores(private val asignaturaProfesor: AsignaturaProfesorDao, var nombre:String) :AsyncTask<Void, Void, List<AsignaturaProfesor>>(){
        override fun doInBackground(vararg params: Void?): List<AsignaturaProfesor> {
            return asignaturaProfesor.getProfesor(nombre)
        }
    }

}