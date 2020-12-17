package com.example.tareafragments.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsignaturaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg asignatura: Asignatura)
    @Query("SELECT * FROM asignatura")
    fun getAsignatura(): List<Asignatura>
}