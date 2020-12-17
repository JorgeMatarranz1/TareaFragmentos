package com.example.tareafragments.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Profesor (
        @PrimaryKey val idProfesor: Int,
        @ColumnInfo val nombre: String?,
        @ColumnInfo val apellido: String?
    )
