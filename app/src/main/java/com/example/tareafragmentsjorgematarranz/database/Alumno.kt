package com.example.tareafragments.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Alumno(
    @PrimaryKey val idAlumno: Int,
    val nombre: String?,
    val apellido: String?
)