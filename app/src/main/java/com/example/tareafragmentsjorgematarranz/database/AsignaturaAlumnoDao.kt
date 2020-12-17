package com.example.tareafragments.database
import androidx.room.*
import com.example.tareafragmentsjorgematarranz.database.AlumnoAsignaturaCrossRef
import com.example.tareafragmentsjorgematarranz.database.AsignaturaAlumno

@Dao
interface AsignaturaAlumnoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: AlumnoAsignaturaCrossRef)

    @Transaction
    @Query("SELECT * FROM Asignatura")
    fun getAsignaturas():List<AsignaturaAlumno>
    @Transaction
    @Query("SELECT * FROM Asignatura where nombreAsig = :nombre")
    fun getAsignatura(nombre: String) : List<AsignaturaAlumno>
}