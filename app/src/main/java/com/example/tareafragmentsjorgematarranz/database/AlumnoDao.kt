package com.example.tareafragments.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlumnoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg alumno: Alumno)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(alumno: List<Alumno>)
    @Query("SELECT * FROM alumno")
    fun getAlumnos(): List<Alumno>
}