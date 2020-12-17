package com.example.tareafragments.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfesorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg profesor: Profesor)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(profesor: List<Profesor>)
    @Query("SELECT COUNT(*) FROM profesor")
    fun getCount(): Int
    @Query("SELECT * FROM profesor")
    fun getProfesor(): List<Profesor>
}