package com.example.tareafragments.database

import androidx.room.*
import com.example.tareafragmentsjorgematarranz.database.AsignaturaProfesor
import com.example.tareafragmentsjorgematarranz.database.ProfesorAsignaturaCrossRef

@Dao
interface AsignaturaProfesorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: ProfesorAsignaturaCrossRef)

    @Transaction
    @Query("SELECT * FROM asignatura")
    fun getAsignaturas():List<AsignaturaProfesor>
    @Transaction
    @Query("SELECT * FROM asignatura where nombreAsig = :nombre")
    fun getProfesor(nombre: String) : List<AsignaturaProfesor>
}