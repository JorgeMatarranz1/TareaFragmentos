package com.example.tareafragments.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tareafragmentsjorgematarranz.database.AlumnoAsignaturaCrossRef
import com.example.tareafragmentsjorgematarranz.database.ProfesorAsignaturaCrossRef

@Database(entities = [Alumno::class, Profesor::class, Asignatura::class, AlumnoAsignaturaCrossRef::class, ProfesorAsignaturaCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alumnoDao(): AlumnoDao
    abstract fun profesorDao(): ProfesorDao
    abstract fun asignaturaDao(): AsignaturaDao
    abstract fun asignaturaAlumnoDao(): AsignaturaAlumnoDao
    abstract fun asignaturaProfesorDao(): AsignaturaProfesorDao

    companion object {
        private const val DATABASE_NAME = "customer_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}